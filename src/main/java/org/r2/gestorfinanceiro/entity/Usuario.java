package org.r2.gestorfinanceiro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {


    @Id
    private Long id;

    @Column
    private String nome;

    @Column
    private String email;

    @Column
    private String senha;

}
