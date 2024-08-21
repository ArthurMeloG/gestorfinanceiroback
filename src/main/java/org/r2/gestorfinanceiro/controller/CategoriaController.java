package org.r2.gestorfinanceiro.controller;

import org.r2.gestorfinanceiro.dto.CategoriaDTO;
import org.r2.gestorfinanceiro.entity.Categoria;
import org.r2.gestorfinanceiro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    private CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        List<Categoria> categorias = categoriaService.findAll();
        return ResponseEntity.ok(categorias);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Categoria> add(@RequestBody CategoriaDTO categoriaDTO) {
        return ResponseEntity.ok(categoriaService.save(categoriaDTO));
    }
}
