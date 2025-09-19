public class Square extends Shape {

    private final double side1;

    public Square(double side1) {
        this.side1 = side1;
    }

    @Override
    public double getArea() {
        return side1 * side1;
    }
}
