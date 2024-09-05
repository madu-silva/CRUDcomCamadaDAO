package br.edu.iftm.estudante.projetomvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.iftm.estudante.projetomvc.dao.CadastroDAO;
import br.edu.iftm.estudante.projetomvc.domain.Cadastro;

@Controller
public class CadastroController {
    
    private final CadastroDAO dao;
    private static final String ESTUDANTE = "estudante";
    private static final String ESTUDANTES = "estudantes";

    public CadastroController(CadastroDAO dao) {
        this.dao = dao;
    }

    @RequestMapping("estudantes")
    public String getEstudantes(Model model) {
        model.addAttribute(ESTUDANTE, new Cadastro());
        model.addAttribute(ESTUDANTES, dao.getEstudantes());
        return "estudantesList";
    }

    @PostMapping("estudantes")
    public String inserirEstudantes(Cadastro estudante, Model model) {
        Cadastro estudanteDb = dao.getEstudante(estudante.getId());
        if (estudanteDb == null) {
            dao.inserirEstudante(estudante);
        } else {
            dao.updateEstudante(estudante);
        }
        
        return getEstudantes(model);
    }

    @RequestMapping("estudantesParametro")
    public String getEstudantes(@RequestParam(value = "instituicao", required = true) String instituicao, Model model) {
        model.addAttribute(ESTUDANTES, dao.getEstudantes(instituicao));
        model.addAttribute(ESTUDANTE, new Cadastro());
        return "estudantesList";
    }

    @RequestMapping("excluirEstudante")
    public String deleteEstudante(@RequestParam(value = "id", required = true) int id, Model model) {
        dao.deleteEstudante(id);
        return getEstudantes(model);
    }

    @RequestMapping("editarEstudante")
    public String editarContato(@RequestParam(value = "id", required = true) int id, Model model) {
        Cadastro estudante = dao.getEstudante(id);
        model.addAttribute(ESTUDANTE, estudante);
        model.addAttribute(ESTUDANTES, dao.getEstudantes());
        return "estudantesList";
    }
}
