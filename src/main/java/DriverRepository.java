package app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DriverRepository {

    public static void add(Driver driver) {
        return;
    }

    // Update by driver ID
    public static void update(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate) {
        //for (Driver d : driverList) {
        //    if (d.getDriverID().equals(driverID)) {
        //        d.updateDetails(name, experienceYears, licenseType, address, birthdate);
        //        return;
        //    }
        //}
        System.out.printf("Could not find driver with id: %s\n", driverID);
    }

    // Update by list index
    public static void update(int index, String name, int experienceYears, String licenseType, String address, String birthdate) {
        return;
    }

    // Retrieve information based on
    public static Driver retrieve(String driverID) {
        Driver driver = getDriver(driverID);
        if(driver == null){

            System.out.printf("Could not find driver with id: %s\n", driverID);
            return null;
        }
        return driver;
    }

    public static int count() {
        return -1;
    }


    private static Driver getDriver(String driverID){
        Path dbPath = Path.of("./app/db/driver.txt");

        if (!Files.exists(dbPath)) {
            System.out.println("could not find ./db/driver.txt");
            return null;
        }

        List<String> drivers;
        try {
                    
            drivers = Files.readAllLines(dbPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }        
        
        for(int i = 0; i < drivers.size(); i++){
            String driver = drivers.get(i);
            if(driver.split("\\s+")[0].equals(driverID)){
                return stringToDriver(driver);
            }
        }
        
        return null;
    }
    private static Driver stringToDriver(String driverString){
        String[] driverArr = driverString.split("\\s+");
        
        Driver driver = new Driver(
                driverArr[0], 
                driverArr[1], 
                Integer.parseInt(driverArr[2]), 
                driverArr[3], 
                driverArr[4], 
                driverArr[5]
        );
        
        return driver;
    }
    

}
