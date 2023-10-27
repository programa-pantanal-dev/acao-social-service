package br.com.b3social.acaosocialservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.b3social.acaosocialservice.api.dtos.AcaoSocialMapper;
import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.repositories.AcaoSocialRepository;
import br.com.b3social.acaosocialservice.domain.services.AcaoSocialService;
import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AcaoSocialServicesTests {
    @Mock
    private AcaoSocialRepository mockRepository;

    @Mock
    private AcaoSocialMapper mapper;

    @InjectMocks
    private AcaoSocialService mockService;
    
    AcaoSocial acaoSocial;
    
    @Test
    @DisplayName("Cria e retorna uma ação social quando tudo está OK")
    public void deveCriarUmaAcaoSocial() {
        AcaoSocial acaoSocial = new AcaoSocial();

        when(mockRepository.save(any(AcaoSocial.class)))
                .thenAnswer(invocation -> invocation.getArgument(0, AcaoSocial.class));

        AcaoSocial acaoSocialCriada = this.mockService.criarAcaoSocial(acaoSocial, anyString());

        assertNotNull(acaoSocialCriada);
        assertNotNull(acaoSocialCriada.getCoordenadorId());
        assertNotNull(acaoSocialCriada.getCreatedBy());
    }  
    
    @Test
    @DisplayName("Retorna todas as ações sociais cadastradas quando tudo está OK")
    void deveBuscarTodasAsAcoesSociais(){
        when(mockRepository.findAllByDeletedAtNull()).thenReturn(List.of(new AcaoSocial(), new AcaoSocial(), new AcaoSocial()));
        
        List<AcaoSocial> acoesSociaisDto = this.mockService.buscarTodasAcaoSocial();

        assertFalse(acoesSociaisDto.isEmpty());
    }

    @Test
    @DisplayName("Retorna uma ação social quando tudo está OK")
    void deveBuscarUmaAcaoSocialPorIdComSucesso() throws Exception{
        Optional<AcaoSocial> acaoSocialOptional = Optional.of(new AcaoSocial());
        
        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(acaoSocialOptional);

        AcaoSocial acaoSocial = this.mockService.buscarAcaoSocialPorId(anyString());

        assertNotNull(acaoSocial);
    }

    @Test
    @DisplayName("Retorna uma exceção EntityNotFoundException quando tudo está OK")
    void deveBuscarUmaAcaoSocialPorIdSemSucesso() throws Exception{
        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> this.mockService.buscarAcaoSocialPorId(anyString()));
    }

    @Test
    @DisplayName("Atualiza e retorna uma ação social quando tudo está OK")
    void deveAtualizarAcaoSocialPorIdComSucesso() throws Exception{
        Optional<AcaoSocial> acaoSocialOptional = Optional.of(new AcaoSocial());

        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(acaoSocialOptional);

        AcaoSocial acaoSocial = this.mockService.atualizarAcaoSocial(anyString(), new AcaoSocial());

        assertNotNull(acaoSocial);
    }

    @Test
    @DisplayName("Retorna uma exceção EntityNotFoundException quando tudo está OK")
    void deveAtualizarAcaoSocialPorIdSemSucesso() throws Exception{
        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> this.mockService.atualizarAcaoSocial(anyString(), new AcaoSocial()));
    }

    @Test
    @DisplayName("Retorna uma ação social quando tudo está OK")
    void deveDeletarUmaAcaoSocialPorIdComSucesso() throws Exception{
        Optional<AcaoSocial> acaoSocialOptional = Optional.of(new AcaoSocial());
        
        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(acaoSocialOptional);

        this.mockService.deletarAcaoSocialPorId(anyString());

        verify(mockRepository, times(1)).save(any(AcaoSocial.class));
    }

    @Test
    @DisplayName("Retorna uma exceção EntityNotFoundException quando tudo está OK")
    void deveDeletarUmaAcaoSocialPorIdSemSucesso() throws Exception{
        when(mockRepository.findByIdAndDeletedAtIsNull(anyString())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, ()-> this.mockService.deletarAcaoSocialPorId(anyString()));
    }
}
