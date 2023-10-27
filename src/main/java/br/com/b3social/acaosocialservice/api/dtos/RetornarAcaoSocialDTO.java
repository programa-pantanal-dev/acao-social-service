package br.com.b3social.acaosocialservice.api.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.b3social.acaosocialservice.domain.models.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RetornarAcaoSocialDTO {
    String id;
    String titulo;
    String resumo;
    String descricao;
    Integer nivel;
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate dataInicio;
    @JsonFormat(pattern="dd-MM-yyyy")
    LocalDate dataTermino;
    String banner;
    String coordenadorId;
    String coordenadorNome;
    String coordenadorEmail;
    Status status;
    String createdBy;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    Date createdAt;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    Date deletedAt;
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    Date updatedAt;
}