package Estudos_Java.Lista_5.ex_1;

public class Main {
    public static void main(String[] args) {
                Motor motor1 = new Motor(120);
                Motor motor2 = new Motor(200);  
        
                Carro carro1 = new Carro(motor1); 
                Carro carro2 = new Carro(motor2); 
        
                System.out.println(carro1);  
                System.out.println(carro2);
    }
}
