
package org.libcg.models;

import org.libcg.core.Model;

public class Livro extends Model<Livro> {
    private Integer id;
    private String titulo;
    private String descricao;
    private Boolean emprestado;
    private String autor;
    
    public Livro() { super(); }
    public Livro(String titulo, String descricao, String autor) {
        super();
        
        this.titulo = titulo;
        this.descricao = descricao;
        this.emprestado = false;
        this.autor = autor;
    }
    
    public void emprestar() {
        emprestado = true;
    }
    
    public void devolver() {
        emprestado = false;
    }
    
    public boolean estaEmprestado() {
        return emprestado;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

}
