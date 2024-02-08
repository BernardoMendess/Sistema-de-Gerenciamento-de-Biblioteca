package com.sgb.biblioteca.controller.normal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import com.sgb.biblioteca.model.Funcionario;
import com.sgb.biblioteca.model.select.Sexo;
import com.sgb.biblioteca.service.FuncionarioService;

@Controller
@AllArgsConstructor
@RequestMapping("/funcionario")
public class FuncionarioController {

    private FuncionarioService funcionarioService;
    
    @GetMapping("/new")
    public ModelAndView novo(){
        return novoEdit(Funcionario.empty());
    }
    
    @GetMapping("/{id}/edit")
    public ModelAndView edit(@PathVariable Long id) {
        val funcionario = funcionarioService.findFuncionarioById(id);
        return novoEdit(funcionario);
    }

    private ModelAndView novoEdit(Funcionario funcionario){
        return new ModelAndView("biblioteca/funcionario/edit")
            .addObject("funcionario", funcionario)
            .addObject("sexos", Sexo.values());
    }

    @PostMapping("/new")
    public String post(Funcionario funcionario){
        funcionarioService.save(funcionario);;
        
        return "livro/list";
    }
    
}