package com.sgb.biblioteca.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sgb.biblioteca.dao.EditoraDAO;
import com.sgb.biblioteca.model.Editora;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EditoraService {
    
    private EditoraDAO editoraDAO;

    public List<Editora> findEditoraByQuery(String nome){
        List<Editora> editoras = editoraDAO.editoraQuery(nome);
        return editoras;
    }

    public List<Editora> listagemEditoras(){
        return editoraDAO.listagemEditora();
    }
}
