package com.sgb.biblioteca.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "livro")
public class Livro {
    @Id
    private Long id;
    private String titulo;
    private Long editoraId;
    private Long generoId;
    private Long autorId;
    private Integer ano;
    private String edicao;
    private Integer quantidade;

    public static Livro empty() {
        return new Livro(null, "", null, null, null, null, "", null);
    }
}

