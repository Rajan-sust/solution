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
        return  "{\"model\" : " + this.modelNumber
                + ", \"engine\" : " + this.engineType
                + ", \"power\" : " + this.enginePower
                + ", \"tire size\" : " + this.tireSize + "}";
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
        return super.toString().replace('}', ',') + " \"additional\" : Turbo}";    
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
        return super.toString().replace('}', ',') + " \"weight\" : " + this.weight + "}";
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
        System.out.println("Successful addition of a sports vehicle.");
    }
    private void addHeavy(String modelNumber, double enginePower, int tireSize, int weight) {
        hv.add(new HeavyVehicle(modelNumber, enginePower, tireSize, weight));
        System.out.println("Successful addition of a heavy vehicle.");
    }
    void addVehicle(Scanner scanner) {
        System.out.print("Possible vehicle type\n(a) normal\n(b) sports\n(c) heavy\nChoose a vehicle type from above.\n(add) >>> ");
        String vehicleType = scanner.next();
        
        System.out.print("Enter Model Number (e.g. AB12#xy).\n(add) >>> ");
        String modelNumber = scanner.next();
        
        System.out.print("Enter Engine Power (e.g. 7.5).\n(add) >>> ");
        double enginePower = scanner.nextDouble();
        
        System.out.print("Enter Tire Size (e.g. 36).\n(add) >>> ");
        int tireSize = scanner.nextInt();
        
        if(vehicleType.equals("normal")) {
            System.out.print("Possible engine type\n(a) oil\n(b) gas\n(c) diesel\nEnter engine type.\n(add) >>> ");
            String engineType = scanner.next();
            addNormal(modelNumber, engineType, enginePower, tireSize);
        } else if(vehicleType.equals("sports")) {
            addSports(modelNumber, enginePower, tireSize);
        } else {
            System.out.print("Enter Weight (e.g. 1000).\n(add) >>> ");
            int weight = scanner.nextInt();
            addHeavy(modelNumber, enginePower, tireSize, weight);
        }
    }
    private void removeNormal(String modelNumber) {
        for(Vehicle v: nv) {
            if(v.modelNumber.equals(modelNumber)) {
                nv.remove(v);
                System.out.println("Successful removal of a normal vehicle.");
                return;
            }
        }
        System.out.println("Sorry! There is no such vehicle.");
    }
    private void removeSports(String modelNumber) {
        for(SportsVehicle v: sv) {
            if(v.modelNumber.equals(modelNumber)) {
                sv.remove(v);
                System.out.println("Succesful removal of a sports vehicle.");
                return;
            }
        }
        System.out.println("Sorry! There is no such vehicle.");
    }
    private void removeHeavy(String modelNumber) {
        for(HeavyVehicle v: hv) {
            if(v.modelNumber.equals(modelNumber)) {
                hv.remove(v);
                System.out.println("Successfully removal of a heavy vehicle.");
                return;
            }
        }
        System.out.println("Sorry! There is no such vehicle.");
    }

    
    //Assuming model number is qunique identity
    void removeVehicle(Scanner scanner) {
        System.out.print("What type of vehicle you want to remove?\n(a) normal\n(b) sports\n(c) heavy\nChoose a vehicle type.\n(rm) >>> ");
        String vehicleType = scanner.next();
        System.out.print("Enter Model Number (e.g. AB12#xy).\n(rm) >>> ");
        String modelNumber = scanner.next();
        if(vehicleType.equals("normal")) {
            removeNormal(modelNumber);
        } else if(vehicleType.equals("sports")) {
            removeSports(modelNumber);
        } else {
            removeHeavy(modelNumber);
        }
    }

    void showVehicle() {
        System.out.println("Normal:");
        for(Vehicle v: nv) {
            System.out.println(v);
        }
        System.out.println("Sports:");
        for(SportsVehicle v: sv) {
            System.out.println(v);
        }
        System.out.println("Heavy:");
        for(HeavyVehicle v: hv) {
            System.out.println(v);
        }
    }
}

public class Main {
    public static void help() {
        System.out.println("Available commands:\n(1) add: Requests for adding a vehicle.\n(2) rm: Requests for removing a vehicle.\n(3) show: Display all vehicles.\n(4) exit: Quit.");
    }
    public static void main(String[] args) {
        VehicleShowRoom vsr = new VehicleShowRoom();
        Scanner scanner = new Scanner(System.in);
        System.out.println("A Vehicle Showroom System. Enter \"help\" for any support.");
        while (true) {
            System.out.print(">>> ");
            String command = scanner.next();
            if(command.equals("help")) {
                help();
            } else if(command.equals("add")) {
                vsr.addVehicle(scanner); 
            } else if(command.equals("rm")) {
                vsr.removeVehicle(scanner);
            } else if(command.equals("show")) {
                vsr.showVehicle();
            } else if(command.equals("exit")) {
                break;
            } else {
                System.out.println("Wrong command. Enter \"help\" for any support.");
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

// For console apps, use a single Scanner to read from System.in. You close the second Scanner which closes the underlying InputStream, therefore the first Scanner can no longer read from the same InputStream and a NoSuchElementException results.


//List of normal vehicles:
