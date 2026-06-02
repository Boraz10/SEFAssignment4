public class Bus {
    private String busID;
    private int capacity;
    private double fuelLevel;
    private String fuelType; // Diesel, Hybrid, Electricity

    public Bus(String busID, int capacity, double fuelLevel, String fuelType) {
        this.busID = busID;
        this.capacity = capacity;
        this.fuelLevel = fuelLevel;
        this.fuelType = fuelType;
    }

    public String getBusID() { return busID; }

    public int getCapacity() {
        return capacity;
    }

    public double getFuelLevel() {
        return fuelLevel;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void updateDetails(int capacity, double fuelLevel, String fuelType) {
        this.capacity = capacity;
        this.fuelLevel = fuelLevel;
        this.fuelType = fuelType;
    }

    // B1: Bus ID Rules
    public static boolean validateBusID(String id) {
        boolean validID = true;
        if (id.length() != 8) {
            System.out.println("ID must be 8 characters long.");
            validID = false;
        }

        if (id.chars().anyMatch(ch -> !Character.isDigit(ch))) {
            System.out.println("ID must only contain digits");
            validID = false;
        }
        return validID;
    }

    public static boolean validateFuelType(String type) {
        String[] validTypes = {"Diesel", "Hybrid", "Electricity"};

        for (String s : validTypes) {
            if (type.equals(s)) {
               return true;
            }
        }
        System.out.println("Fuel type must be: Diesel, Hybrid or Electricity");
        return false;
    }

}
