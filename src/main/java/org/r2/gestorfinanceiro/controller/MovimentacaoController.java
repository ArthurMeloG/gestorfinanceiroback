package org.r2.gestorfinanceiro.controller;

import org.r2.gestorfinanceiro.entity.Movimentacao;
import org.r2.gestorfinanceiro.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @GetMapping
    public ResponseEntity<List<Movimentacao>> find(@RequestParam(required = false) Long id) {
        List<Movimentacao> movimentacoes;

        if (id == null) {
            movimentacoes = movimentacaoService.findAll();
        } else {
            Movimentacao movimentacao = movimentacaoService.findById(id);
            movimentacoes = movimentacao != null ? List.of(movimentacao) : new ArrayList<>();
        }

        return ResponseEntity.ok(movimentacoes);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Movimentacao addNewMovimentacao(@RequestBody Movimentacao movimentacao) {
        return movimentacaoService.save(movimentacao);
    }

    @PostMapping("/upload")
    public String uploadExcelFile(@RequestParam("file") MultipartFile file) {
        return movimentacaoService.saveMovimentacoesFromExcel(file);
    }

}
