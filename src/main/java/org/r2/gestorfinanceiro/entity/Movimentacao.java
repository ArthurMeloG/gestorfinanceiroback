package org.r2.gestorfinanceiro.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date data;

    @ManyToOne
    private Categoria categoria;

    @Column
    private String descricao;

    @Column
    private Float valor;

    @Column
    private byte[] anexoComprovante;

    @ManyToMany
    private List<Usuario> usuario;
}
