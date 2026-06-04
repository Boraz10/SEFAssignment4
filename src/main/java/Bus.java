import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Bus {
    private final String busID;
    private Driver driver;
    private int capacity;
    private double fuelLevel;
    private String fuelType; // Diesel, Hybrid, Electricity


    public Bus(String busID, String driverID, int capacity, double fuelLevel, String fuelType) {
        this.busID = busID;
        this.driver = DriverRepository.retrieve(driverID);
        this.capacity = capacity;
        this.fuelLevel = fuelLevel;
        this.fuelType = fuelType;
    }

    public String getBusID() { return busID; }

    public Driver getDriver() { return driver; }

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

    public boolean updateDriver(String driverID) {
        Driver newDriver = DriverRepository.retrieve(driverID);

        // Calculate driver age
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        assert newDriver != null;
        LocalDate birthdate = LocalDate.parse(newDriver.getBirthdate(), formatter);

        long ageInYears = ChronoUnit.YEARS.between(birthdate, today);

        System.out.println(ageInYears);

        // B3: Validate driver age
        if (!validateDriverAge(ageInYears, capacity)) {
            return false;
        }

        return validateDriver(driverID);

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


    //
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

    // B2: Checking bus capacity
    public boolean validCapacity(double updated) {
        if (updated > capacity) {
            System.out.println("Capacity cannot increase when updated");
            return false;
        }
        return true;
    }


    // B3: Validating driver age
    public boolean validateDriverAge(long age, int capacity) {
        if (age > 50 && capacity > 50) {
            System.out.println("Drivers older than 50 cannot drive buses with a capacity of 50 or more.");
            return false;
        }
        return true;
    }

    public boolean validateDriver(String driverID) {
        Driver newDriver = DriverRepository.retrieve(driverID);

        // B4: Validate experience
        if (fuelType.equals("Electricity")) {
            assert newDriver != null;
            if (newDriver.getExperienceYears() < 5) {
                System.out.println("A driver needs 5+ years of experience to drive an Electric vehicle");
                return false;
            }
        }


        // B5: Validate license type
        if ( (newDriver.getLicenseType().equals("Heavy") || newDriver.getLicenseType().equals("PublicTransport") ) &&
                !(fuelType.equals("Electricity") || fuelType.equals("Hybrid"))
        ) {
            System.out.println("Only heavy or public transport lincenced drivers can drive an electric of hybrid vehicle");
            return false;
        }


        return true;
    }



}
