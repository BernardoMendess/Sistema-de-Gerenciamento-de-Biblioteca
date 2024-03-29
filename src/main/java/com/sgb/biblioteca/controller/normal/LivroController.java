package com.sgb.biblioteca.controller.normal;

import com.sgb.biblioteca.model.Autor;
import com.sgb.biblioteca.model.Editora;
import com.sgb.biblioteca.model.Livro;
import com.sgb.biblioteca.service.AutorService;
import com.sgb.biblioteca.service.EditoraService;
import com.sgb.biblioteca.service.GeneroService;
import com.sgb.biblioteca.service.LivroService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller 
@AllArgsConstructor
@RequestMapping("/livro")
public class LivroController {
    
    private LivroService livroService;
    
    private GeneroService generoService;

    private AutorService autorService;

    private EditoraService editoraService;
    
    @GetMapping()
    public ModelAndView list(){
        val livros = livroService.listagemLivros();
    
        return new ModelAndView("biblioteca/livro/list")
            .addObject("livros", livros);
    }
    
    
    @GetMapping("/{id}")
    public ModelAndView get(@PathVariable Long id){
        val livro = livroService.findLivroComDependenciaById(id);

        return new ModelAndView("biblioteca/livro/get")
            .addObject("livro", livro);
    }


    @GetMapping("/new")
    public ModelAndView novo(){
        return novoEdit(Livro.empty());
    }
    
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        val livro = livroService.findLivroById(id);
        return novoEdit(livro);
    }
    
    private ModelAndView novoEdit(Livro livro){

        Autor autor = null;
        Editora editora = null;
        
        if (livro.getId() != null){
            autor = autorService.findById(livro.getAutorId());
            editora = editoraService.findByIdCamposFormatados(livro.getEditoraId());
        }
        
        return new ModelAndView("biblioteca/livro/edit")
            .addObject("livro", livro)
            .addObject("generos", generoService.findAllGeneros())
            .addObject("autor", autor)
            .addObject("editora", editora);
    }


    @PostMapping("/new")
    public String post(Livro livro, RedirectAttributes redirectAttributes){
        livroService.save(livro);
        
        redirectAttributes.addAttribute("id", livro.getId());
        
        return "redirect:/livro/{id}";
    }

}
