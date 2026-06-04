import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BusRepository {

    // Get path to database.
    static Path path = getDBPath();

    // Read the file and store it in an arraylist.
    private static ArrayList<Bus> busList = readFileToArrayList();

    // Add bus to database
    public static void add(Bus bus) {
        // If path is null, bus cannot be added to database.
        if (path == null) {
            System.out.println("path is null.");
            return;
        }

        if (!bus.updateDriver(bus.getDriver().getDriverID())) {
            return;
        }

        // Validate bus details
        if (!validateBus(bus)) {
            return;
        }

        // B1: Check if bus of this ID is already present
        for (Bus other : busList) {
            String thisBusID = bus.getBusID();
            if (other.getBusID().equals(thisBusID)) {
                System.out.println("Bus with ID " + thisBusID + " already exists at index " + busList.indexOf(other));
                return;
            }
        }

        // Usung bufferedWriter to append a string containing bus information to the txt file
        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
            writer.write(busToString(bus));
            writer.newLine();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // Also add the bus to the arraylist
        busList.add(bus);
    }

    // Update bus details based on the busID
    public static void update(String busID, String driverID, int capacity, double fuelLevel, String fuelType) throws IOException {

        // Iterate through list of busses to try to find the bus in question
        for (int i = 0; i < busList.size(); ++i) {
            Bus b = busList.get(i);

            if (b.getBusID().equals(busID)) {
                // validate new bus details
                if (!Bus.validateFuelType(fuelType)) { return; }

                // validate fuel change
                if (!b.validCapacity(capacity)) { return; }

                if (!b.updateDriver(driverID)) {
                    return;
                }
                // Update bus details (in the arraylist, using the buses update function)
                b.updateDetails(capacity, fuelLevel, fuelType);

                // Update file
                updateFile(i, busToString(b), path);
                return;
            }
        }
        System.out.printf("Could not find bus with id: %s\n", busID);
    }

    // Update bus details based on the index (starting at 0)
    public static void update(int index, String driverID, int capacity, double fuelLevel, String fuelType) throws IOException {

        // Check range of index
        if (index >= busList.size() || index < 0) {
            System.out.printf("Index %d out of range\n", index);
            return;
        }

        // Get bus at that index
        Bus b = busList.get(index);

        // validate new bus details
        if (!Bus.validateFuelType(fuelType)) { return; }

        // validate fuel change
        if (!b.validCapacity(capacity)) { return; }

        if (!b.updateDriver(driverID)) {
            return;
        }

        // Update that buses details
        b.updateDetails(capacity, fuelLevel, fuelType);

        // Update the txt file, changing the line (which is index + 1) to then updated bus
        updateFile(index, busToString(b), path);
    }

    // Retrieve bus information based on bus ID (get bus at index)
    public static Bus retrieve(String busID) {
        for (Bus b : busList) {
            if (b.getBusID().equals(busID)) {
                return b;
            }
        }
        System.out.printf("Could not find bus with id: %s\n", busID);
        return null;
    }

    // Return number of buses in database
    public static int count() {
        return busList.size();
    }

    // Function to read file to the arraylist.
    public static ArrayList<Bus> readFileToArrayList() {
        // Temp arraylist
        ArrayList<Bus> list = new ArrayList<>();

        // Checking if path is null. If it is, it indicates the file doesn't exist
        if (path == null) {
            System.out.println("path is null. Creating file...");

            try {
                // Create a new file if bus.txt doesn't already exist
                File busFile = new File("./db/bus.txt");
                if (busFile.createNewFile()) {
                    System.out.println("Created new file successfully at: " + busFile.getName());
                    path = getDBPath();
                }
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }

        // Read aach line using a buffered reader
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split each line based on spaces. Each line would be exaclty 6 elements long.
                // Element[0] = bus ID
                // Element[1] = driver ID
                // Element[2] = fuel Capacity
                // Element[3] = fuel Level
                // Element[4] = fuel type
                String[] elements = line.split("\\s+");

                // Create new bus object based on info from the database
                Bus bus = new Bus(elements[0], elements[1], Integer.parseInt(elements[2]), Double.parseDouble(elements[3]), elements[4]);

                // add bus to the temp arraylist
                list.add(bus);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // Return arraylist of buses based on info from database
        return list;
    }

    // Helper function which creates string from bus info, useful for writing to the database
    private static String busToString(Bus bus) {
        return    bus.getBusID() + " "
                + bus.getDriver().getDriverID() + " "
                + bus.getCapacity() + " "
                + bus.getFuelLevel() + " "
                + bus.getFuelType();

    }

    // Try to get the path to the database, setting it to null if it can't find it
    private static Path getDBPath() {
        String path = "./db/bus.txt";
        Path dbPath = Path.of(path);
        if (!Files.exists(dbPath)) {
            System.out.println("Error: Could not find " + path);
            return null;
        }
        return dbPath;
    }

    // Update a specified line in the database.
    private static void updateFile(int index, String content, Path p) throws IOException {
        // Since line numbers start with 1, we need to add 1 to the index of the line we are updating
        int lineNumber = index + 1;

        // List of lines
        List<String> lines = Files.readAllLines(p, StandardCharsets.UTF_8);

        // Check bounds
        if (lineNumber > 0 && lineNumber <= lines.size()) {
            lines.set(lineNumber - 1, content);
        } else {
            System.out.println("Line number out of bounds");
            return;
        }

        // Write changes to file
        Files.write(path, lines, StandardCharsets.UTF_8);
    }


    // Validate bus
    private static boolean validateBus(Bus bus) {
        boolean a = Bus.validateBusID(bus.getBusID());
        boolean b = Bus.validateFuelType(bus.getFuelType());

        return (a && b);
    }

    public static void clearDB(){
        Path dbPath = getDBPath();
        try{
            Files.writeString(dbPath, "");
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }

}



