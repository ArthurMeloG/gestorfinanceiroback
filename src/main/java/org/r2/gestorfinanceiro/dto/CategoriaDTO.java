package org.r2.gestorfinanceiro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoriaDTO {

    private Long id;
    private String nome;
    private boolean ativo;
}
