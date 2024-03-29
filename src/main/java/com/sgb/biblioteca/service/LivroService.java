package com.sgb.biblioteca.service;

import com.sgb.biblioteca.dao.LivroDAO;
import com.sgb.biblioteca.model.Livro;
import com.sgb.biblioteca.model.DTOs.LivroAutorDTO;
import com.sgb.biblioteca.model.comDependencias.LivroComDependencia;
import lombok.AllArgsConstructor;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LivroService {

    private LivroDAO livroDAO;

    private AutorService autorService;
    
    private EditoraService editoraService;

    private GeneroService generoService;

    public void save(Livro livro){
        livroDAO.save(livro);
    }

    public Livro findLivroById(Long id){
        return livroDAO.findById(id).orElse(null);
    }

    public List<Livro> findLivroByQuery(String titulo){
        List<Livro> livros = livroDAO.livroQuery(titulo);
        return livros;
    }

    public List<LivroAutorDTO> listagemLivros(){
        return livroDAO.listagemLivros();
    }
    
    // Método para a classe LivroComDependencias
    public LivroComDependencia findLivroComDependenciaById(Long id) {
        return livroDAO.findById(id)
            .map(livro -> {
                try {
                    return new LivroComDependencia(
                        livro.getId(),
                        livro.getTitulo(),
                        editoraService.findByIdCamposFormatados(livro.getEditoraId()),
                        generoService.findById(livro.getGeneroId()),
                        autorService.findById(livro.getAutorId()),
                        livro.getAno(),
                        livro.getEdicao(),
                        livro.getQuantidade()
                    );
                } catch (Exception e) {
                    throw new RuntimeException("Erro ao obter dependências do livro " + e);
                }
            })
            .orElse(null);
    }   
}
