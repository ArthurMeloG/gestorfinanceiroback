package org.r2.gestorfinanceiro.service;

import org.r2.gestorfinanceiro.dto.CategoriaDTO;
import org.r2.gestorfinanceiro.entity.Categoria;
import org.r2.gestorfinanceiro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<CategoriaDTO> findAll() {
        return categoriaRepository.findAll().stream().map(this::categoriaToDTO).toList();
    }

    public CategoriaDTO findById(Long id) {
        var response = categoriaRepository.findById(id);
        return response.map(this::categoriaToDTO).orElse(null);
    }

    public CategoriaDTO save(CategoriaDTO categoriaDTO) {
        var categoria = categoriaDTOToCategoria(categoriaDTO);
        var response = categoriaRepository.save(categoria);
        return this.categoriaToDTO(response);
    }

    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }

    private CategoriaDTO categoriaToDTO(Categoria categoria) {
        return new CategoriaDTO(categoria.getId(), categoria.getNome(), categoria.isAtivo());
    }

    private Categoria categoriaDTOToCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria = new Categoria();
        categoria.setId(categoriaDTO.getId());
        categoria.setNome(categoriaDTO.getNome());
        categoria.setAtivo(categoriaDTO.isAtivo());
        return categoria;
    }
}
