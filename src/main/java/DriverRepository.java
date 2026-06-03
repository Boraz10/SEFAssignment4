import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class DriverRepository {
    public static void add(Driver driver) {
        if(getDriver(driver.getDriverID()) != null){
            System.out.println("Driver with same ID already exists");
            return;
        }
        Path dbPath = getDBPath();
        String driverString = driverToString(driver) +"\n";

        try{
            Files.writeString(dbPath, driverString, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e){
            System.out.println(e.toString());
            return;
        }
        return;
    }

    // Update by driver ID
    public static void update(String driverID, String name, int experienceYears, 
                                String licenseType, String address, String birthdate) {
        if(getDriver(driverID) == null){
            System.out.printf("Could not find driver with id: %s\n", driverID);
            return;
        }
        delete(driverID);
        Driver driver = new Driver(driverID, name, experienceYears, licenseType, address, birthdate);
        add(driver);
    }
    public static void delete(String driverID){
        Path dbPath = getDBPath();

        List<String> drivers;
        try {
            drivers = Files.readAllLines(dbPath);
        } catch (IOException e) {
            System.out.println(e.toString());
            return;
        }

        for(int i = 0; i < drivers.size(); i++){
            String driver = drivers.get(i);
            if(driver.split("\\s+")[0].equals(driverID)){
                drivers.remove(i);
                try{
                    Files.write(dbPath, drivers);
                } catch(IOException e){
                    System.out.println(e.toString());
                }
                return;
            }
        }

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
        Path dbPath = getDBPath();
        try{
            int count = Files.readAllLines(dbPath).size();
            return count;
        } catch(IOException e){
            System.out.println(e.toString());
            return -1;
        }
    }

    private static Driver getDriver(String driverID){
        Path dbPath = getDBPath();
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
        String id = driverArr[0];
        String name = driverArr[1];
        int experience = Integer.parseInt(driverArr[2]);
        String license = driverArr[3];
        String endLicense = driverArr[driverArr.length -1];
        
        String address = "";
        for(int i = 4; i < driverArr.length -1; i++){
            address += driverArr[i];

        }
        Driver driver = new Driver(
                id, 
                name, 
                experience, 
                license, 
                address, 
                endLicense
        );
        
        return driver;
    }
    
    private static String driverToString(Driver driver){

    //public Driver(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate) {
        return 
            driver.getDriverID() + " " 
            + driver.getName() + " "
            + driver.getExperienceYears() + " "
            + driver.getLicenseType() + " "
            + driver.getAddress() + " "
            + driver.getBirthdate();

    }

    public static void clearDB(){
        Path dbPath = getDBPath();
        try{
            Files.writeString(dbPath, "");
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

    private static Path getDBPath(){
        String path = "./db/driver.txt";
        Path dbPath = Path.of(path);
        if (!Files.exists(dbPath)) {
            System.out.println("Error: Could not find " + path);
            return null;
        }
        return dbPath;
    }
    

}


