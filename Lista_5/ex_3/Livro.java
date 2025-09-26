// package Estudos_Java.Lista_5.ex_3;

public class Livro {
    private final String titulo;
    private final String autor;

    public Livro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo(){
        return titulo;
    }

    public String getAutor(){
        return autor;
    }

    @Override
    public String toString() {
        return "\"" + titulo + "\" de " + autor;
    }
}
