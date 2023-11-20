SELECT * FROM dbestudantes.tb_estudante;

create database dbestudantes;

create table tb_estudante (
id int primary key,
nome varchar(60),
cpf varchar(14),
email varchar(40),
telefone varchar(20),
instituicao varchar(60),
curso varchar(60),
periodo varchar(20)
);

insert into tb_estudante 
(id, nome, cpf, email, telefone, instituicao, curso, periodo) 
values
(7, 'Mariana', '345.456.567-76', 'mariana@gmail.com', '(34) 99787-9999', 'UFU', 'Medicina', '10º');