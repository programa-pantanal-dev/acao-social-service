package br.com.b3social.acaosocialservice.domain.models;

import java.time.LocalDate;
import java.util.Date;

import br.com.b3social.acaosocialservice.domain.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="AcaoSocial")
@Table(name="acaosocial")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class AcaoSocial{
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String titulo;
    
    private String resumo;

    private String descricao;

    private Integer nivel;
    
    private LocalDate dataInicio;
    
    private LocalDate dataTermino;

    private String banner;
    
    private String coordenadorId;

    private String coordenadorNome;

    private String coordenadorEmail;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String createdBy;

    private Date createdAt;

    private Date deletedAt;

    private Date updatedAt;
}