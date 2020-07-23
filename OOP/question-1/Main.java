import java.util.LinkedList;
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
    private LinkedList<Vehicle> nv;
    private LinkedList<SportsVehicle> sv;
    private LinkedList<HeavyVehicle> hv;
    VehicleShowRoom() {
        nv = new LinkedList<Vehicle>();
        sv = new LinkedList<SportsVehicle>();
        hv = new LinkedList<HeavyVehicle>();
    }
    private void addNormal(String modelNumber, String engineType, double enginePower, int tireSize) {
        nv.add(new Vehicle(modelNumber, engineType, enginePower, tireSize));
        System.out.println("Successful addition of a normal vehicle.");
    }
    private void addSports(String modelNumber, double enginePower, int tireSize) {
        sv.add(new SportsVehicle(modelNumber, enginePower, tireSize));
        System.out.println("Successful addition of a sports vehicle.")
    }
    private void addHeavy(String modelNumber, double enginePower, int tireSize, int weight) {
        hv.add(new HeavyVehicle(modelNumber, enginePower, tireSize, weight));
        System.out.println("Successful addition of a heavy vehicle.")
    }
    void addVehicle() {
       
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Possible vehicle type\n(a) normal\n(b) sports\n(c) heavy\nEnter vehicle type: ");
        String vehicleType = scanner.next();
        
        System.out.print("Enter Model Number: ");
        String modelNumber = scanner.next();
        
        System.out.print("Enter Engine Power (e.g. 7.5): ");
        double enginePower = scanner.nextDouble();
        
        System.out.print("Enter Tire Size (e.g. 36): ");
        int tireSize = scanner.nextInt();
        
        if(vehicleType == "normal") {
            System.out.print("Possible engine type\n(a) oil\n(b) gas\n(c) diesel\nEnter engine type: ");
            String engineType = scanner.next();
            aaddNormal(modelNumber, engineType, enginePower, tireSize);
        } else if(vehicleType == "sports") {
            addSports(modelNumber, enginePower, tireSize);
        } else {
            System.out.print("Enter Weight (e.g. 1000): ");
            int weight = scanner.nextInt();
            addHeavy(modelNumber, enginePower, tireSize, weight);
        }
        scanner.close();
    }
    private void removeNormal(String modelNumber) {
        for(Vehicle v: nv) {
            if(v.modelNumber == modelNumber) {
                nv.remove(v);
                System.out.println("Successful removal of a normal car.");
                return;
            }
        }
        System.out.println("Sorry! There is no such car.");
    }
    private void removeSports(String modelNumber) {
        for(SportsVehicle v: sv) {
            if(v.modelNumber == modelNumber) {
                sv.remove(v);
                System.out.println("Succesful removal of a sports car.");
                return;
            }
        }
        System.out.println("Sorry! There is no such car.");
    }

    private void removeHeavy(String modelNumber) {
        for(HeavyVehicle v: hv) {
            if(v.modelNumber == modelNumber) {
                hv.remove(v);
                System.out.println("Successfully removal of a heavy car.");
                return;
            }
        }
        System.out.println("Sorry! There is no such car.");
    }

    void showVehicle() {
        System.out.println("1. Normal Vehicles:");
        for(Vehicle v: nv) {
            System.out.println(v);
        }
        System.out.prinln("2. Sports Vehicle:");
        for(SportsVehicle v: sv) {
            System.out.println(v);
        }
        System.out.println("3. Heavy Vehicle:");
        for(HeavyVehicle v: hv) {
            System.out.println(v);
        }
    }
    //Assuming model number is qunique identity
    void removeVehicle() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("What type of vehicle you want to remove?\n(a) normal\n(b) sports\n(c) heavy\nEnter vehicle type: ");
        String vehicleType = scanner.next();
        System.out.print("Please, enter model number: ");
        String modelNumber = scanner.next();
        if(vehicleType == "normal") {
            removeNormal(modelNumber);
        } else if(vehicleType == "sports") {
            removeSports(modelNumber);
        } else {
            removeHeavy(modelNumber);
        }
        scanner.close();
    }

}

public class Main {
    public static void help() {
        System.out.println("(1) add: For adding a vehicle.\n(2) remove: For removing a vehicle.\n(3) show: Display all vehicles.\n(4) exit: quit.");
    }
    public static void main(String[] args) {
        VehicleShowRoom vsr = new VehicleShowRoom();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter `help` for any support");
        while (true) {
            System.out.print(">>> ");
            String command = scanner.next();
            if(command == "help") {
                help();
            } else if(command == "add") {
                vsr.addVehicle(); 
            } else if(command == "remove") {
                vsr.removeVehicle();
            } else if(command == "show") {
                vsr.showVehicle();
            } else if(command == "exit") {
                break;
            } else {
                System.out.println("Wrong command. Enter `help` for any support.");
            }
            
        }
        scanner.close();          
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