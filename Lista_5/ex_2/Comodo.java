package Estudos_Java.Lista_5.ex_2;

public class Comodo {
    private String nome;
    private float tamanho;
    private String materialDoPiso;

    // Construtor do cômodo
    public Comodo(String nome, float tamanho, String materialDoPiso) {
        this.nome = nome;
        this.tamanho = tamanho;
        this.materialDoPiso = materialDoPiso;
    }

    // Sobrescrevendo o método toString() para exibir as informações do cômodo
    @Override
    public String toString() {
        return "Cômodo: " + nome + ", Tamanho: " + tamanho + "m², Material do Piso: " + materialDoPiso;
    }
}
