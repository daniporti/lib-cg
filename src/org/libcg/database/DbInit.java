package org.libcg.database;

public class DbInit {
    public static String[] run() {
        String[] queries = {
            "CREATE TABLE IF NOT EXISTS Livro(id INT PRIMARY KEY AUTO_INCREMENT, titulo VARCHAR(255) NOT NULL, descricao VARCHAR(255) NOT NULL, emprestado BOOLEAN DEFAULT false)",
            "DELETE FROM Livro WHERE 1=1",
            "INSERT INTO Livro(titulo, descricao) VALUES('Engenharia de Software Moderna', 'Engenharia de Software Moderna eh um livro-texto destinado a alunos de cursos de graduação em Computacao')",
            "INSERT INTO Livro(titulo, descricao) VALUES('O Pequeno Principe', 'Eh uma novela do escritor, aviador aristocrata frances Antoine de Saint-Exupery')",
            "INSERT INTO Livro(titulo, descricao) VALUES('Romeu e Julieta', 'Eh uma novela do escritor, aviador aristocrata frances Antoine de Saint-Exupery')"
        };
        
        return queries;
    }
}
