public class Main {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        Livro l1 = new Livro("Dom Casmurro", "Machado de Assis");
        Livro l2 = new Livro("O Hobbit", "J.R.R. Tolkien");
        Livro l3 = new Livro("Olhos D'Agua", "Conceição Evaristo");

        biblioteca.addLivro(l1);
        biblioteca.addLivro(l2);
        biblioteca.addLivro(l3);

        biblioteca.lsLivro();

        biblioteca.rmLivro(l2);
        biblioteca.lsLivro();

    }
}
