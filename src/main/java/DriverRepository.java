import java.util.ArrayList;

public class DriverRepository {

    // TODO: Instead of arraylist, i think we need to save the data to a txt file
    private ArrayList<Driver> driverList = new ArrayList<>();

    public void add(Driver driver) {
        driverList.add(driver);
    }

    // Update by driver ID
    public void update(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate) {
        for (Driver d : driverList) {
            if (d.getDriverID().equals(driverID)) {
                d.updateDetails(name, experienceYears, licenseType, address, birthdate);
                return;
            }
        }
        System.out.printf("Could not find driver with id: %s\n", driverID);
    }

    // Update by list index
    public void update(int index, String name, int experienceYears, String licenseType, String address, String birthdate) {
        if (index >= driverList.size()) {
            System.out.printf("Index %d out of range\n", index);
            return;
        }

        driverList.get(index).updateDetails(name, experienceYears, licenseType, address, birthdate);
    }

    // Retrieve information based on
    public Driver retrieve(String driverID) {
        for (Driver d : driverList) {
            if (d.getDriverID().equals(driverID)) {
                return d;
            }
        }
        System.out.printf("Could not find driver with id: %s\n", driverID);
        return null;
    }

    public int count() {
        return driverList.size();
    }
}
