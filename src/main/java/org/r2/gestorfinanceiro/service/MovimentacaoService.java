package org.r2.gestorfinanceiro.service;


import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.r2.gestorfinanceiro.entity.Categoria;
import org.r2.gestorfinanceiro.entity.Movimentacao;
import org.r2.gestorfinanceiro.exception.InvalidExcelFormatException;
import org.r2.gestorfinanceiro.repository.CategoriaRepository;
import org.r2.gestorfinanceiro.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovimentacaoService {

    private final MovimentacaoRepository movimentacaoRepository;

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository, CategoriaRepository categoriaRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Movimentacao save(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public void delete(Movimentacao movimentacao) {
        movimentacaoRepository.delete(movimentacao);
    }

    public Movimentacao update(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    public Movimentacao findById(Long id) {
         return movimentacaoRepository.findById(id).orElse(null);
    }

    public String saveMovimentacoesFromExcel(MultipartFile file) {
        try {
            List<Movimentacao> movimentacoes = convertExcelToMovimentacoes(file);
            movimentacaoRepository.saveAll(movimentacoes);
            return "Arquivo carregado e movimentações salvas com sucesso!";
        } catch (IOException e) {
            e.printStackTrace();
            return "Erro ao processar o arquivo: " + e.getMessage();
        }
    }

    private List<Movimentacao> convertExcelToMovimentacoes(MultipartFile file) throws IOException {
        List<Movimentacao> movimentacoes = new ArrayList<>();
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {
            if (row.getRowNum() == 0) {
                continue;
            }
            Movimentacao movimentacao = parseMovimentacaoRow(row);
            movimentacoes.add(movimentacao);
        }
        workbook.close();
        return movimentacoes;
    }

    private Movimentacao parseMovimentacaoRow(Row row) {
        Movimentacao movimentacao = new Movimentacao();

        try {
            movimentacao.setData(row.getCell(0).getDateCellValue());

            String categoriaNome = row.getCell(1).getStringCellValue();
            Categoria categoria = categoriaRepository.findByNome(categoriaNome);
            movimentacao.setCategoria(categoria);

            movimentacao.setDescricao(row.getCell(2).getStringCellValue());
            movimentacao.setValor((float) row.getCell(3).getNumericCellValue());

        } catch (Exception e) {
            throw new InvalidExcelFormatException("Colunas do arrquivo não correspondem a movimentação");
        }

        return movimentacao;
    }
}
