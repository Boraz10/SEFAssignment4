public class Driver {
    private String driverID;
    private String name;
    private int experienceYears;
    private String licenseType; // Light, Medium, Heavy, PublicTransport
    private String address;
    private String birthdate;

    public Driver(String driverID, String name, int experienceYears, String licenseType, String address, String birthdate) {
        this.driverID = driverID;
        this.name = name;
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
    }

    public String getDriverID() {
        return driverID;
    }

    public String getName() {
        return name;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public static boolean isValidDriverID(String driverID) {
        //ID must meet the following requirements: 
        //1. Must not be null
        //2.Must be 10 characters or more in length
        if(driverID == null || driverID.length() != 10) {
            return false;
        }  

        char firstNumber = driverID.charAt(0);
        char secondNumber = driverID.charAt(1);

        if (firstNumber < '2' || secondNumber < '2' ||
            firstNumber > '9' || secondNumber > '9') {
            return false;
        }

        
    }


    public void updateDetails(String name, int experienceYears, String licenseType, String address, String birthdate) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
    }
}
