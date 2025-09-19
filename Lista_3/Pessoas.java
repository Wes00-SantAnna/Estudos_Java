public class Pessoas {
    private String nome;
    private int idade;

    Pessoas(String nome, int idade){
        this.nome = nome;
        this.idade= idade;
    }


    public String getNome(){
        return nome;
    } 

    public int getIdade(){
        return idade;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + "\nidade: " + idade;
    }
}
