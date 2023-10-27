package br.com.b3social.acaosocialservice;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.services.AcaoSocialService;
import br.com.b3social.acaosocialservice.security.expressions.ResourceAccessService;

@SpringBootTest
public class SecurityExpressionsTests {
    @Mock
    Authentication authentication;
    
    @Mock
    SecurityContext securityContext;

    @Mock
    AcaoSocialService mockService;

    @InjectMocks
    ResourceAccessService accessService;

    @Test
    @DisplayName("Retorna true quando tudo está OK")
    void deveRetornarTrueQuandoUsuarioAutorizado() throws Exception{
        AcaoSocial acaoSocial = new AcaoSocial();
        acaoSocial.setCoordenadorId("Teste");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        
        when(this.mockService.buscarAcaoSocialPorId(anyString())).thenReturn(acaoSocial);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("Teste");

        assertTrue(this.accessService.isOwner("Teste"));
    }

    @Test
    @DisplayName("Retorna false quando tudo está OK")
    void deveRetornarFalseQuandoUsuarioNaoAutorizado() throws Exception{
        AcaoSocial acaoSocial = new AcaoSocial();
        acaoSocial.setCoordenadorId("Teste");

        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
        
        when(this.mockService.buscarAcaoSocialPorId(anyString())).thenReturn(acaoSocial);
        when(SecurityContextHolder.getContext().getAuthentication().getName()).thenReturn("Teste1");

        assertFalse(this.accessService.isOwner("Teste"));
    }
}
