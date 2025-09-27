import java.util.ArrayList;


public class Biblioteca {
    private final ArrayList<Livro> livros;


    public Biblioteca(){
        this.livros = new ArrayList<>();
    }

    public void addLivro(Livro livro){
        livros.add(livro);
        System.out.println("Livro Adiciona: " + livro);   
    }

    public void rmLivro(Livro livro){
        if(livros.remove(livro)) {
            System.out.println("Livro removido: " + livro);
        }
        else {
            System.out.println("Livro não encontrado!");
        }
    }

    public void lsLivro(){
        if(livros.isEmpty()) {
            System.out.println("Bibioteca está vazia!");
        } 
        else {
            System.out.println("Livros encontrado na Biblioteca:");
            for(Livro livro : livros){
                System.out.println(" - " + livro);
            }
        }
    }
}
