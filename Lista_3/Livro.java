
public class Livro {
    private String titulo;
    private String autor;
    private float preco;

    // Construtor
    public Livro(String titulo, String autor, float preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    // Getters
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public float getPreco() {
        return preco;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public void setPreco(float preco) {
        if (preco > 0.0f) {
            this.preco = preco;
        } else {
            throw new IllegalArgumentException("O preço não pode ser negativo ou zero!");
        }
    }


    // Método para exibir informações
    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", preco=" + preco +
                '}';
    }
}
