package br.com.b3social.acaosocialservice;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.b3social.acaosocialservice.api.dtos.AcaoSocialMapper;
import br.com.b3social.acaosocialservice.api.dtos.AtualizarAcaoSocialDTO;
import br.com.b3social.acaosocialservice.api.dtos.CriarAcaoSocialDTO;
import br.com.b3social.acaosocialservice.api.dtos.RetornarAcaoSocialDTO;
import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.models.enums.Status;
import br.com.b3social.acaosocialservice.domain.services.AcaoSocialService;
import br.com.b3social.acaosocialservice.security.expressions.ResourceAccessService;

@SpringBootTest
@AutoConfigureMockMvc
public class AcaoSocialControllersTests {
    @MockBean
    AcaoSocialService mockService;
    
    @MockBean
    AcaoSocialMapper mockMapper;

    @MockBean(name = "resourceAccessService")
    public ResourceAccessService resourceAccessService;

    @Autowired
    private MockMvc mockMvc;

    public static final String BASE_URI = "/acaosocial";
    public static final String URI_ID = BASE_URI + "/{id}";
    public static final String RANDOM_UIID = "dc7a2924-736b-11ee-b962-0242ac120002";

    @Test
    public void retornaStatusCodeCreatedQuandoUsuarioAutorizado() throws Exception{
        CriarAcaoSocialDTO acaoSocialDTO = new CriarAcaoSocialDTO();
        acaoSocialDTO.setTitulo("Test");
        acaoSocialDTO.setResumo("Test");
        acaoSocialDTO.setDescricao("Test");
        acaoSocialDTO.setNivel(1);
        acaoSocialDTO.setDataInicio(null);
        acaoSocialDTO.setDataTermino(null);
        acaoSocialDTO.setBanner(null);
        acaoSocialDTO.setCoordenadorNome("Test");
        acaoSocialDTO.setCoordenadorEmail("test.ovelar@gmail.com");  
        acaoSocialDTO.setStatus(Status.ABERTA);

        AcaoSocial acaoSocial = new AcaoSocial();
        RetornarAcaoSocialDTO acaoSocialDTO2 = new RetornarAcaoSocialDTO();

        when(mockMapper.DtoToAcaoSocial(any(CriarAcaoSocialDTO.class))).thenReturn(acaoSocial);

        when(mockService.criarAcaoSocial(any(AcaoSocial.class), anyString())).thenReturn(acaoSocial);

        when(mockMapper.AcaoSocialToDto(any(AcaoSocial.class))).thenReturn(acaoSocialDTO2);

        this.mockMvc
            .perform(post(BASE_URI)
                .contentType("application/json")
                .content(toJson(acaoSocialDTO))
                .with(SecurityMockMvcRequestPostProcessors.jwt()
                    .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                    .authorities(List.of(new SimpleGrantedAuthority("ROLE_ACAO_SOCIAL_CRIAR")))))
            .andExpect(status().isCreated());
    }

    @Test
    public void retornaStatusCodeUnauthorizedQuandoUsuarioNaoAutorizadoParaCriar() throws Exception{
        CriarAcaoSocialDTO acaoSocialDTO = new CriarAcaoSocialDTO();
        acaoSocialDTO.setTitulo("Test");
        acaoSocialDTO.setResumo("Test");
        acaoSocialDTO.setDescricao("Test");
        acaoSocialDTO.setNivel(1);
        acaoSocialDTO.setDataInicio(null);
        acaoSocialDTO.setDataTermino(null);
        acaoSocialDTO.setBanner(null);
        acaoSocialDTO.setCoordenadorNome("Test");
        acaoSocialDTO.setCoordenadorEmail("test.ovelar@gmail.com");  
        acaoSocialDTO.setStatus(Status.ABERTA);

        when(mockMapper.DtoToAcaoSocial(any(CriarAcaoSocialDTO.class))).thenReturn(new AcaoSocial());

        when(mockService.criarAcaoSocial(any(AcaoSocial.class), anyString())).thenReturn(new AcaoSocial());

        when(mockMapper.AcaoSocialToDto(any(AcaoSocial.class))).thenReturn(new RetornarAcaoSocialDTO());

        this.mockMvc
            .perform(post(BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(acaoSocialDTO))
                .with(SecurityMockMvcRequestPostProcessors.jwt()
                    .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                    .authorities(List.of(new SimpleGrantedAuthority("default")))))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    void deveBuscarTodasAsAcoesSociais() throws Exception{
        when(mockService.buscarTodasAcaoSocial()).thenReturn(List.of(new AcaoSocial(), new AcaoSocial()));
        when(mockMapper.AcaoSocialToDto(any(AcaoSocial.class))).thenReturn(new RetornarAcaoSocialDTO());

        this.mockMvc.perform(get(BASE_URI))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockUser
    void deveBuscarUmaAcaoSocialPorIdComSucesso() throws Exception{   
        when(mockService.buscarAcaoSocialPorId(anyString())).thenReturn(new AcaoSocial());
        when(mockMapper.AcaoSocialToDto(any())).thenReturn(new RetornarAcaoSocialDTO());

        this.mockMvc.perform(get(URI_ID, RANDOM_UIID))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void retornaStatusCodeOkQuandoUsuarioAutorizado() throws Exception{
        AtualizarAcaoSocialDTO acaoSocialDTO = new AtualizarAcaoSocialDTO();
        when(resourceAccessService.isOwner(anyString())).thenReturn(true);
        when(mockMapper.DtoToAcaoSocial(any(AtualizarAcaoSocialDTO.class))).thenReturn(new AcaoSocial());
        when(mockService.atualizarAcaoSocial(anyString(), any(AcaoSocial.class))).thenReturn(new AcaoSocial());
        when(mockMapper.AcaoSocialToDto(any())).thenReturn(new RetornarAcaoSocialDTO());

        this.mockMvc.perform(put(URI_ID, RANDOM_UIID)
                        .contentType("application/json")
                        .content(toJson(acaoSocialDTO))
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                            .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                            .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))))
                    .andExpect(status().isOk());
    }

    @Test
    void retornaStatusCodeUnauthorizedQuandoUsuarioNaoAutorizadoParaAtualizar() throws Exception{
        AtualizarAcaoSocialDTO acaoSocialDTO = new AtualizarAcaoSocialDTO();
        when(resourceAccessService.isOwner(anyString())).thenReturn(false);
        when(mockMapper.DtoToAcaoSocial(any(AtualizarAcaoSocialDTO.class))).thenReturn(new AcaoSocial());
        when(mockService.atualizarAcaoSocial(anyString(), any(AcaoSocial.class))).thenReturn(new AcaoSocial());
        when(mockMapper.AcaoSocialToDto(any())).thenReturn(new RetornarAcaoSocialDTO());

        this.mockMvc.perform(put(URI_ID, RANDOM_UIID)
                        .contentType("application/json")
                        .content(toJson(acaoSocialDTO))
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                            .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                            .authorities(List.of(new SimpleGrantedAuthority("default")))))
                    .andExpect(status().isUnauthorized());
    }

    @Test
    void retornaStatusCodeNoContentQuandoUsuarioAutorizado() throws Exception{
        this.mockMvc.perform(delete(URI_ID, RANDOM_UIID)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                            .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                            .authorities(List.of(new SimpleGrantedAuthority("ROLE_ADMIN")))))
                    .andExpect(status().isNoContent());
    }

    @Test
    void retornaStatusCodeUnauthorizedQuandoUsuarioNaoAutorizadoParaDeletar() throws Exception{
        when(resourceAccessService.isOwner(anyString())).thenReturn(false);
        this.mockMvc.perform(delete(URI_ID, RANDOM_UIID)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()
                            .jwt(jwt -> jwt.claim(StandardClaimNames.SUB, "default"))
                            .authorities(List.of(new SimpleGrantedAuthority("default")))))
                    .andExpect(status().isUnauthorized());
    }

    private String toJson(final Object object) throws Exception {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (final Exception e) {
            throw new Exception("Error to convert object to json", e);
        }
    }
}
