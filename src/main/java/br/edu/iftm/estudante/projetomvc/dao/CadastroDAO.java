package br.edu.iftm.estudante.projetomvc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import br.edu.iftm.estudante.projetomvc.domain.Cadastro;

import java.util.List;

@Component
public class CadastroDAO {

    @Autowired
    JdbcTemplate db;

    public List<Cadastro> getEstudantes() {
        String sql = "select * from tb_estudante";

        return db.query(sql, (res, rowNum) -> {
            return new Cadastro(
                res.getInt("id"),
                res.getString("nome"),
                res.getString("cpf"),
                res.getString("email"),
                res.getString("telefone"),
                res.getString("instituicao"),
                res.getString("curso"),
                res.getString("periodo")
            );
        });
    }
    

    public List<Cadastro> getEstudantes(String instituicao){
        String sql = "select * from tb_estudante where instituicao like ?";

        return db.query(sql,
                        new BeanPropertyRowMapper<>(Cadastro.class),
                        new Object[]{"%" + instituicao + "%"}
        );
    }


    public Cadastro getEstudante(int id){
        String sql = "select * from tb_estudante where id = ?";

        List<Cadastro> estudantes = db.query(sql,
            new BeanPropertyRowMapper<>(Cadastro.class),
            new Object[]{id});
        
        if(!estudantes.isEmpty())
            return estudantes.get(0);
        else
            return null;
    }

    public void inserirEstudante(Cadastro estudante) {
        String sql = "insert into tb_estudante(id, nome, cpf, email, telefone, instituicao, curso, periodo) values(?,?,?,?,?,?,?,?)";
    
        db.update(sql, new Object[]{estudante.getId(), estudante.getNome(), estudante.getCpf(), estudante.getEmail(),
            estudante.getTelefone(), estudante.getInstituicao(), estudante.getCurso(), estudante.getPeriodo()});
    }


    public void updateEstudante(Cadastro estudante) {
        String sql = "update tb_estudante set nome = ?, cpf = ?, email = ?, telefone = ?, instituicao = ?, curso = ?, periodo = ? where id = ?";

        db.update(sql, new Object[]{estudante.getNome(), estudante.getCpf(), estudante.getEmail(),
            estudante.getTelefone(), estudante.getInstituicao(), estudante.getCurso(), estudante.getPeriodo(), estudante.getId()});
    }

    public void deleteEstudante(int id) {
        String sql = "delete from tb_estudante where id = ?";

        db.update(sql,new Object[]{id});
    }

}
