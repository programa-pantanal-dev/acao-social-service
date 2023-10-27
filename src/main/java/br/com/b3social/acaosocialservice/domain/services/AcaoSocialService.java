package br.com.b3social.acaosocialservice.domain.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.b3social.acaosocialservice.api.dtos.AcaoSocialMapper;
import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;
import br.com.b3social.acaosocialservice.domain.repositories.AcaoSocialRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class AcaoSocialService {
    @Autowired
    private AcaoSocialRepository repository;
    
    @Autowired
    AcaoSocialMapper mapper;

    public AcaoSocial criarAcaoSocial(AcaoSocial acaoSocial, String usuarioId){
        acaoSocial.setCreatedBy(usuarioId);
        acaoSocial.setCoordenadorId(usuarioId);

        return this.salvarAcaoSocial(acaoSocial);
    }

    public List<AcaoSocial> buscarTodasAcaoSocial(){
        return this.repository.findAllByDeletedAtNull();
    }

    public AcaoSocial buscarAcaoSocialPorId(String id) throws Exception{
        Optional<AcaoSocial> acaoSocialOptional = this.repository.findByIdAndDeletedAtIsNull(id);

        if(acaoSocialOptional.isPresent()){
            return acaoSocialOptional.get();     
        }
        
        throw new EntityNotFoundException();
    }

    public List<AcaoSocial> buscarAcaoSocialPorCoordenador(String coordenadorId){
        return this.repository.findByCoordenadorId(coordenadorId);
    }
    
    public AcaoSocial atualizarAcaoSocial(String id, AcaoSocial acaoSocialAtualizada) throws Exception{
        Optional<AcaoSocial> acaoSocialOptional = this.repository.findByIdAndDeletedAtIsNull(id);

        if(acaoSocialOptional.isPresent()){
            AcaoSocial acaoSocial = acaoSocialOptional.get();
            acaoSocial.setUpdatedAt(new Date());
            mapper.merge(acaoSocial, acaoSocialAtualizada);
            salvarAcaoSocial(acaoSocial);
            
            return acaoSocial; 
        }
        throw new EntityNotFoundException();
    }
    
    public void deletarAcaoSocialPorId(String id){
        Optional<AcaoSocial> acaoSocialOptional = this.repository.findByIdAndDeletedAtIsNull(id);

        if(acaoSocialOptional.isPresent()){
            AcaoSocial acaoSocial = acaoSocialOptional.get();
            acaoSocial.setDeletedAt(new Date());
            salvarAcaoSocial(acaoSocial);
            
            return;
        }

        throw new EntityNotFoundException();
    }

    public AcaoSocial salvarAcaoSocial(AcaoSocial acaosocial){
        return this.repository.save(acaosocial);
    }
}
