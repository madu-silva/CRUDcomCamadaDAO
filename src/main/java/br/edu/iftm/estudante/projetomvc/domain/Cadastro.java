package br.edu.iftm.estudante.projetomvc.domain;

import lombok.Data;

@Data
public class Cadastro {
    
    private int id;
    private String nome, cpf, email, telefone, instituicao, curso, periodo;
    
    public Cadastro() {
    }

    public Cadastro(int id, String nome, String cpf, String email, String telefone, String instituicao, String curso,
            String periodo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.instituicao = instituicao;
        this.curso = curso;
        this.periodo = periodo;
    }

    
}
