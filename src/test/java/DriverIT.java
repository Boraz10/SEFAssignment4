import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.AfterAll;

class DriverIT {
    
    @AfterAll
    static void finish(){
        populateDB();
    }

    @Test
    void add() {
        DriverRepository.clearDB();
        Driver driver = new Driver(
                "38s%$dseSE",
                "Tim",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );
        DriverRepository.add(driver);

        String expected = "38s%$dseSE Tim 4 Heavy 10|Swanston Street|Melbourne|VIC|Australia 22-11-1990\n";
        String db = getDBString();
        assertTrue(db.equals(expected),"does not match expected string in DB. diff: " + diffCharIndex(expected, db));


    }

    @Test
    void addDuplicate(){
        DriverRepository.clearDB();
        Driver driver = new Driver(
                "38s%$dseSE",
                "Tim",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );
        DriverRepository.add(driver);
        DriverRepository.add(driver);
        //only one instance of tim in the db
        String expected = "38s%$dseSE Tim 4 Heavy 10|Swanston Street|Melbourne|VIC|Australia 22-11-1990\n";
        String db = getDBString();
        assertTrue(db.equals(expected),"expected: " + expected + " but got " + db);
    }

    @Test
    void update() {
        DriverRepository.clearDB();
        Driver driver = new Driver(
                "38s%$dseSE",
                "Tim",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );
        DriverRepository.add(driver);

        DriverRepository.update(
                "38s%$dseSE",
                "Adam",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );

        String expected = "38s%$dseSE Adam 4 Heavy 10|Swanston Street|Melbourne|VIC|Australia 22-11-1990\n";
        String db = getDBString();
        assertTrue(db.equals(expected),"expected: " + expected + " but got " + db);

    }

    @Test
    void delete() {
        DriverRepository.clearDB();
        populateDB();
        DriverRepository.delete("68pq#&rsLM");//deleting lucas

        String expected = 
"""
38s%$dseSE Tim 4 Heavy 10|Swanston Street|Melbourne|VIC|Australia 22-11-1990
73ab$%cdGH Ellie 10 Light 120|Swanston Street|Melbourne|VIC|Australia 20-06-2000
""";
        String db = getDBString();
        int diffIndex = diffCharIndex(expected, db);
        assertTrue(db.equals(expected),"expected: " + expected + " but got " + db + " diffIndex = " + diffIndex);
    }

    @Test
    void retrieve() {
        DriverRepository.clearDB();
        Driver driver = new Driver(
                "38s%$dseSE",
                "Tim",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );
        DriverRepository.add(driver);
        DriverRepository.clearDB();

        Driver driver2 = DriverRepository.retrieve("38s%$dseSE");
        assertTrue(driver2 == null, "could not retrieve driver");

    }

    @Test
    void count() {
        DriverRepository.clearDB();
        assertTrue(DriverRepository.count() == 0);
        populateDB();
        assertTrue(DriverRepository.count() == 3);
        Driver driver = new Driver(
            "45mn!@opNO",
            "Jade",
            20,
            "Heavy",
            "24|street way|Melbourne|VIC|Australia",
            "01-04-2006"
        );
        DriverRepository.add(driver);
        assertTrue(DriverRepository.count() == 4);
        DriverRepository.delete("38s%$dseSE"); //delete tim
        assertTrue(DriverRepository.count() == 3);
    }
    
    boolean dbEquals(String string){
        return getDBString().equals(string);
    }

    String getDBString(){
        String path = "./db/driver.txt";
        Path dbPath = Path.of(path);

        if (!Files.exists(dbPath)) {
            System.out.println("Error: Could not find " + path);
            return null;
        }

        try{
            return Files.readString(dbPath);
        } catch(IOException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    static void populateDB(){
        Driver driver = new Driver(
                "38s%$dseSE",
                "Tim",
                4,
                "Heavy",
                "10|Swanston Street|Melbourne|VIC|Australia",
                "22-11-1990"
                );

        Driver driver2 = new Driver(
            "68pq#&rsLM",
            "Lucas",
            1,
            "Medium",
            "124|La Trobe Street|Melbourne|VIC|Australia",
            "24-04-1995"
        );

        Driver driver3 = new Driver(
            "73ab$%cdGH",
            "Ellie",
            10,
            "Light",
            "120|Swanston Street|Melbourne|VIC|Australia",
            "20-06-2000"
        );
        DriverRepository.add(driver);
        DriverRepository.add(driver2);
        DriverRepository.add(driver3);

    }
    //get index of first char that is different between two strings. returns -1 if same;
    int diffCharIndex(String string1, String string2){
        int maxLength = Integer.min(string1.length(), string2.length());
        for(int i = 0; i < maxLength; i++){
            if(string1.charAt(i)!= string2.charAt(i)){
                return i;
            }
        }

        if(string1.length() < string2.length()){
                return string1.length();
        }
        else if(string1.length() > string2.length()){
            return string2.length();
        }
        return -1;
    }
}
