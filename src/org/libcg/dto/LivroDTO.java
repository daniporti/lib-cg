package org.libcg.dto;

public class LivroDTO {
    private final Integer id;
    private final String titulo;
    private final String descricao;
    private final Boolean emprestado;
    private final String autor;
    
    
    public LivroDTO(String titulo, String descricao, String autor) {
        this.id = 0;
        this.titulo = titulo;
        this.descricao = descricao;
        this.emprestado = true;
        this.autor = autor;
    }
    
    public LivroDTO(Integer id, String titulo, String descricao, Boolean emprestado, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.emprestado = emprestado;
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Boolean estaEmprestado() {
        return emprestado;
    }

    public String getAutor(){
        return autor;
    }
}
