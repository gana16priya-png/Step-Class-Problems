// Abstract Class
abstract class Shape {
    protected double area;
    protected double perimeter;

    // Abstract methods
    public abstract void calculateArea();
    public abstract void calculatePerimeter();
}

// Interface
interface Drawable {
    void draw();
}

// Concrete Class
class Circle extends Shape implements Drawable {
    private double radius;

    // Constructor
    public Circle(double radius) {
        this.radius = radius;
    }

    // Implement abstract methods
    @Override
    public void calculateArea() {
        area = Math.PI * radius * radius;
    }

    @Override
    public void calculatePerimeter() {
        perimeter = 2 * Math.PI * radius;
    }

    // Implement interface method
    @Override
    public void draw() {
        System.out.println("Drawing a Circle with radius: " + radius);
    }

    // Show details
    public void showDetails() {
        System.out.println("Circle Area: " + area);
        System.out.println("Circle Perimeter: " + perimeter);
    }
}

// Main Class
public class ShapeDemo {
    public static void main(String[] args) {
        Circle c = new Circle(5.0);
        c.calculateArea();
        c.calculatePerimeter();
        c.draw();
        c.showDetails();
    }
}
