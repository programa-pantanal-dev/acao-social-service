package br.com.b3social.acaosocialservice.api.dtos;

import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.b3social.acaosocialservice.domain.models.enums.Status;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarAcaoSocialDTO {    
    @NotBlank()
    private String titulo;
    
    @NotBlank()
    private String resumo;

    @NotBlank()
    private String descricao;

    @NotNull()
    @Min(value = 1)
    @Max(value = 5)
    private Integer nivel;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataInicio;
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dataTermino;

    private String banner;

    @NotBlank()
    private String coordenadorNome;

    @Email
    private String coordenadorEmail;

    @NotNull
    private Status status;

    @JsonIgnore
    private Date createdAt = new Date();
}