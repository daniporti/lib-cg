package org.libcg.dto;

public class LivroDTO {
    private final Integer id;
    private final String titulo;
    private final String descricao;
    private final Boolean emprestado;
    
    public LivroDTO(String titulo, String descricao) {
        this.id = 0;
        this.titulo = titulo;
        this.descricao = descricao;
        this.emprestado = true;
    }
    
    public LivroDTO(Integer id, String titulo, String descricao, Boolean emprestado) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.emprestado = emprestado;
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
}
