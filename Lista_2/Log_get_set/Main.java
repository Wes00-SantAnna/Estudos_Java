package Log_get_set;

public class Main {
    public static void main(String[] args) {
        Temperatura t = new Temperatura(300f);
        System.out.println("Kelvin: " + t.getKelvin());
        System.out.println("Celsius: " + t.getCelsius());
        System.out.println("Fahrenheit: " + t.getFahrenheit());
    }
}