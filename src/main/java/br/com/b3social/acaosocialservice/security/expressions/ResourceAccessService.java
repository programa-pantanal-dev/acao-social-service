package br.com.b3social.acaosocialservice.security.expressions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.services.AcaoSocialService;


@Service("resourceAccessService")
public class ResourceAccessService {

    @Autowired
    AcaoSocialService acaoSocialService;
    Authentication authentication;
    
    public boolean isOwner(String resouceId) throws Exception{
        AcaoSocial acaoSocial = this.acaoSocialService.buscarAcaoSocialPorId(resouceId);
        String nome = SecurityContextHolder.getContext().getAuthentication().getName();
        
        if(nome.equals(acaoSocial.getCoordenadorId())) return true;

        return false;
    }
}
