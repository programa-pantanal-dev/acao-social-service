package br.com.b3social.acaosocialservice.api.dtos;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import br.com.b3social.acaosocialservice.domain.models.AcaoSocial;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
componentModel = "spring")
public interface AcaoSocialMapper {
    AcaoSocial DtoToAcaoSocial(CriarAcaoSocialDTO criarAcaosocialDTO);
    AcaoSocial DtoToAcaoSocial(AtualizarAcaoSocialDTO atualizarAcaosocialDTO);
    RetornarAcaoSocialDTO AcaoSocialToDto(AcaoSocial acaosocial);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void merge(@MappingTarget AcaoSocial acaoSocial, AcaoSocial acaoSocialAtualizada);
}