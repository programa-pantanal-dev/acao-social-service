package br.com.b3social.acaosocialservice.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;

@Repository
public interface AcaoSocialRepository extends JpaRepository<AcaoSocial, String>{
    List<AcaoSocial> findByCoordenadorId(String coordenadorId);    
    Optional<AcaoSocial> findByIdAndDeletedAtIsNull(String id);
    List<AcaoSocial> findAllByDeletedAtNull();
}
