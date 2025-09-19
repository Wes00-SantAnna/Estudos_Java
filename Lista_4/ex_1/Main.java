
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ArrayList<Shape> shapes = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            if (random.nextBoolean()) {
                shapes.add(new Circle(1 + random.nextInt(10)));
            } else {
                shapes.add(new Square(1 + random.nextInt(10)));
            }
        }

        int index = 1;
        for (Shape s : shapes) {
            System.out.println("Forma " + index + " area = " + s.getArea());
            index++;
        }
    }
}
