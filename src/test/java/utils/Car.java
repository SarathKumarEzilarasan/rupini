package utils;

public class Car {

    static Car car;

    private Car() {

    }

    public static Car getCar() {
        if (car == null) {
            car = new Car();
        }
        return car;
    }

}

class demo {
    public static void main(String[] args) {
        Car car = Car.getCar();

    }
}