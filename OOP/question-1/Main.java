class Vehicle {
    String modelNumber;
    String engineType;
    double enginePower;
    int tireSize;
    Vehicle() {}
    Vehicle(String modelNumber, double enginePower, int tireSize) {
        this.modelNumber = modelNumber;
        this.enginePower = enginePower;
        this.tireSize = tireSize;
    }
    Vehicle(String modelNumber, String engineType, double enginePower, int tireSize) {
        Vehicle(modelNumber, enginePower, tireSize);    
        this.engineType = engineType;
    }

}

class SportsVehicle extends Vehicle {
    // final static boolean turbo = true;
    SportsVehicle(String modelNumber, double enginePower, int tireSize) {
        super(modelNumber, enginePower, tireSize);
        this.engineType = "oil";
    }

}

class HeavyVehicle extends Vehicle {
    int weight;
    HeavyVehicle(String modelNumber, double enginePower, int tireSize, int weight) {
        super(modelNumber, enginePower, tireSize);
        this.weight = weight;
        this.engineType = "diesel";
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");    
    }
}