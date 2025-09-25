package Estudos_Java.Lista_5.ex_2;

import java.util.ArrayList;

public class Casa {
    private ArrayList<Comodo> comodos;  // Lista de cômodos

    // Construtor da Casa, inicializa a lista de cômodos
    public Casa() {
        this.comodos = new ArrayList<>();
    }

    // Método para adicionar um cômodo à casa
    public void adicionaComodo(Comodo comodo) {
        this.comodos.add(comodo);
    }

    // Sobrescrevendo o método toString() para exibir os cômodos da casa
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Casa tem os seguintes cômodos:\n");
        for (Comodo comodo : comodos) {
            sb.append(comodo).append("\n");
        }
        return sb.toString();
    }
}
