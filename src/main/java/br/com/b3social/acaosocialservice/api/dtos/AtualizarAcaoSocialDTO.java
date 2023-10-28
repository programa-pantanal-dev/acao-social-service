package br.com.b3social.acaosocialservice.api.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.b3social.acaosocialservice.annotations.validators.nullOrNotBlank.NullOrNotBlank;
import br.com.b3social.acaosocialservice.domain.models.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizarAcaoSocialDTO{
    @NullOrNotBlank()
    private String titulo;
    
    @NullOrNotBlank()
    private String resumo;

    @NullOrNotBlank()
    private String descricao;

    @Min(value = 1)
    @Max(value = 5)
    private Integer nivel;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicio;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataTermino;

    @NullOrNotBlank()
    private String banner;

    @NullOrNotBlank()
    private String coordenadorNome;

    @Email
    @NullOrNotBlank()
    private String coordenadorEmail;

    private Status status;

    @JsonIgnore
    private Date updatedAt = new Date();
}
