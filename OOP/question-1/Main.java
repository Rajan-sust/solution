import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;


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
        this(modelNumber, enginePower, tireSize);    
        this.engineType = engineType;
    }
    @Override
    public String toString() {
        return  "ModelNumber: " + this.modelNumber
                + "\nEngineType: " + this.engineType
                + "\nEnginePower: " + this.enginePower
                + "\nTireSize: " + this.tireSize;
    }

}

class SportsVehicle extends Vehicle {
    // final static boolean turbo = true;
    SportsVehicle(String modelNumber, double enginePower, int tireSize) {
        super(modelNumber, enginePower, tireSize);
        super.engineType = "oil";
    }
    @Override
    public String toString() {
        return super.toString() + "\nAdditional Feature: Turbo";    
    }

}

class HeavyVehicle extends Vehicle {
    int weight;
    HeavyVehicle(String modelNumber, double enginePower, int tireSize, int weight) {
        super(modelNumber, enginePower, tireSize);
        super.engineType = "diesel";
        this.weight = weight;
    }
    @Override
    public String toString() {
        return super.toString() + "\nWeight: " + this.weight;
    }
}

// Note that downcast is not type safe, and it may throw runtime exceptions.

class VehicleShowRoom {
    LinkedList<Vehicle> nv;
    LinkedList<SportsVehicle> sv;
    LinkedList<HeavyVehicle> hv;
    VehicleShowRoom() {
        nv = new LinkedList<Vehicle>();
        sv = new LinkedList<SportsVehicle>();
        hv = new LinkedList<HeavyVehicle>();
    }
    void addVehicle() {
        HashMap<String, String> mp = new HashMap<String, String>() {{
            put("a", "normal");
            put("b", "sports");
            put("c", "heavy");
        }};
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Choose vehicle Type\n(a) Normal\n(b) Sports\n(c) Heavy\nEnter (a/b/c):");
        String vehicleType = mp.get(scanner.next());
        
        System.out.print("Enter Model Number:");
        String modelNumber = scanner.next();
        
        System.out.print("Enter Engine Power (e.g. 7.5):");
        double enginePower = scanner.nextDouble();
        
        System.out.print("Enter Tire Size (e.g. 36):");
        int tireSize = scanner.nextInt();
        
        if(vehicleType == "normal") {
            System.out.print("Enter Engine Type (oil/gas/diesel):");
            String engineType = scanner.next();
            nv.add(new Vehicle(modelNumber, engineType, enginePower, tireSize));
        } else if(vehicleType == "sports") {
            sv.add(new SportsVehicle(modelNumber, enginePower, tireSize));
        } else {
            System.out.print("Enter Weight (e.g. 1000):");
            int weight = scanner.nextInt();
            hv.add(new HeavyVehicle(modelNumber, enginePower, tireSize, weight));
        }
        scanner.close();
    }

}

public class Main {
    public static void main(String[] args) {
        VehicleShowRoom vsr = new VehicleShowRoom();
        vsr.addVehicle();

          
    }
}


/*
Vehicle vehicle = new Vehicle("123456", "oil", 5.4, 16);
System.out.println(vehicle);
SportsVehicle sv = new  SportsVehicle("45678", 7.8, 21);
System.out.println(sv);
HeavyVehicle hv = new HeavyVehicle("98489", 7.1, 36, 100);
System.out.println(hv);
*/        