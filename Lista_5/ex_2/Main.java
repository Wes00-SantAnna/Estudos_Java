package Estudos_Java.Lista_5.ex_2;

public class Main {
    public static void main(String[] args) {
        
        Comodo comodo1 = new Comodo("Sala", 20.5f, "Cerâmica");
        Comodo comodo2 = new Comodo("Cozinha", 15.0f, "Porcelanato");
        Comodo comodo3 = new Comodo("Quarto", 12.3f, "Madeira");

        Casa casa = new Casa();
        casa.adicionaComodo(comodo1);
        casa.adicionaComodo(comodo2);
        casa.adicionaComodo(comodo3);

        System.out.println(casa);
    }
}
