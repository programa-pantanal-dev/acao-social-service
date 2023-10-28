package br.com.b3social.acaosocialservice.api.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.b3social.acaosocialservice.api.dtos.RetornarAcaoSocialDTO;
import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.services.AcaoSocialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.b3social.acaosocialservice.api.dtos.AcaoSocialMapper;
import br.com.b3social.acaosocialservice.api.dtos.AtualizarAcaoSocialDTO;
import br.com.b3social.acaosocialservice.api.dtos.CriarAcaoSocialDTO;
import jakarta.validation.Valid;

@RestController()
@RequestMapping(value = "/acaosocial", produces = {"application/json"})
@Tag(name = "Ação Social - API")
@SecurityRequirement(name = "Keycloak")
public class AcaoSocialController {
    @Autowired
    private AcaoSocialService acaoSocialService;

    @Autowired
    AcaoSocialMapper mapper;

    @PostMapping
    @PreAuthorize("hasRole('ACAO_SOCIAL_CRIAR')")
    @Operation(
        summary = "Realiza o cadastro de ação social",
        method = "POST"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Sucesso ao cadastrar ação social"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado",
        content = @Content()
    )
    public ResponseEntity<RetornarAcaoSocialDTO> criarAcaoSocial(@Valid @RequestBody CriarAcaoSocialDTO criarAcaoSocialDTO, @AuthenticationPrincipal Jwt principal){
        AcaoSocial acaoSocial = mapper.DtoToAcaoSocial(criarAcaoSocialDTO);
        
        RetornarAcaoSocialDTO retornarAcaoSocialDTO = mapper.AcaoSocialToDto(
            this.acaoSocialService.criarAcaoSocial(acaoSocial, principal.getSubject())
        );

        return new ResponseEntity<>(retornarAcaoSocialDTO, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
        summary = "Retorna todas as ações sociais",
        method = "GET"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Sucesso ao buscar todas as ações sociais"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado",
        content = @Content()
    )
    public ResponseEntity<List<RetornarAcaoSocialDTO>> buscarTodasAcaoSocial(){
        List<AcaoSocial> acoesSociais = this.acaoSocialService.buscarTodasAcaoSocial();
        
        List<RetornarAcaoSocialDTO> acoesSociaisDTO = acoesSociais.stream().map(
            acaosocial -> mapper.AcaoSocialToDto(acaosocial)).collect(Collectors.toList()
        );
        
        return new ResponseEntity<List<RetornarAcaoSocialDTO>>(acoesSociaisDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Retorna uma ação social pelo ID",
        method = "GET"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Sucesso ao buscar ação social por id"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado",
        content = @Content()
    )
    public ResponseEntity<RetornarAcaoSocialDTO> buscarAcaoSocialPorId(@PathVariable String id) throws Exception{
        AcaoSocial sc = this.acaoSocialService.buscarAcaoSocialPorId(id);
        RetornarAcaoSocialDTO retornarAcaoSocialDTO = mapper.AcaoSocialToDto(sc);

        return new ResponseEntity<>(retornarAcaoSocialDTO, HttpStatus.OK);
    }

    @GetMapping("coordenador/{id}")
    @Operation(
        summary = "Retorna uma ação social pelo CordenadorID",
        method = "GET"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Sucesso ao buscar ação social por coordenador"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado",
        content = @Content()
    )
    public ResponseEntity<List<RetornarAcaoSocialDTO>> buscarAcaoSocialPorCoordenador(@PathVariable String id){
        List<AcaoSocial> acoesSociais = this.acaoSocialService.buscarAcaoSocialPorCoordenador(id);
        
        List<RetornarAcaoSocialDTO> acoesSociaisDTO = acoesSociais.stream().map(
            acaosocial -> mapper.AcaoSocialToDto(acaosocial)).collect(Collectors.toList()
        );
        
        return new ResponseEntity<List<RetornarAcaoSocialDTO>>(acoesSociaisDTO, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') || @resourceAccessService.isOwner(#id)")
    @Operation(
        summary = "Atualiza uma ação social pelo ID",
        method = "PUT"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Sucesso ao atualizar ação social"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado",
        content = @Content()
    )
    public ResponseEntity<RetornarAcaoSocialDTO> atualizarAcaoSocial(@PathVariable String id, @Valid @RequestBody AtualizarAcaoSocialDTO atualizarAcaoSocialDTO) throws Exception{
        AcaoSocial acaoSocialAtualizada = mapper.DtoToAcaoSocial(atualizarAcaoSocialDTO);
        
        RetornarAcaoSocialDTO retornarAcaoSocialDTO = mapper.AcaoSocialToDto(
            this.acaoSocialService.atualizarAcaoSocial(id, acaoSocialAtualizada)
        );


        return new ResponseEntity<>(retornarAcaoSocialDTO, HttpStatus.OK);
    } 

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(
        summary = "Deleta uma ação social pelo ID",
        method = "DELETE"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Sucesso ao deletar ação social"
    )
    @ApiResponse(
        responseCode = "401",
        description = "Não autorizado"
    )  
    public ResponseEntity<Void> deletarAcaoSocial(@PathVariable String id){
        this.acaoSocialService.deletarAcaoSocialPorId(id);
        return ResponseEntity.noContent().build();
    }
}
