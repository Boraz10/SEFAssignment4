public class Driver {
    final private String driverID;
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

    // D1: Complete 
    public static boolean isValidDriverID(String driverID) {
        //ID must meet the following requirements: 
        //1. Must not be null
        //2.Must be 10 characters in length
        if(driverID == null || driverID.length() != 10) {
            return false;
        }  

        //First two characters must be digits between 2 and 9
        char firstNumber = driverID.charAt(0);
        char secondNumber = driverID.charAt(1);

        if (firstNumber < '2' || secondNumber < '2' ||
            firstNumber > '9' || secondNumber > '9') {
            return false;
        }

        
        String middlePortion =  driverID.substring(2, 8);

        int specialCharCount = 0;


        for (char sc : middlePortion.toCharArray()) {
            if (!Character.isLetterOrDigit(sc)) {
                specialCharCount++;
            }
        }

        // Middle portion needs 2 special characters between 3 and 8 
        if (specialCharCount < 2) {
            return false;
        }

        char secondLastLetter = driverID.charAt(8);
        char lastLetter = driverID.charAt(9);

        //Last two characters must be uppercase letters
        if (secondLastLetter < 'A' || secondLastLetter > 'Z' ||
            lastLetter < 'A' || lastLetter > 'Z') {
            return false;
        }

        return true;

    }

    //D2: Complete
    public static boolean isValidAddress(String address) {
        
        if(address == null) {
            return false;
        }  

        String[] addressSection = address.split("\\|");

        if (addressSection.length != 5) {
            return false;
        }

        for (String section : addressSection) {
            if (section.trim().isEmpty()) {
                return false;
            }
        }

        //Inspecting the first section to see if it only contains digits
        for (int i = 0; i < addressSection[0].length(); i++) {
            if (!Character.isDigit(addressSection[0].charAt(i))) {
                return false;
            }
        }

        return true;

    }

    //INCOMPLETE
    public static boolean isValidBirthDate(String birthDate) {
        

        return true;

    }

    

    //INCOMPLETE
    public static boolean isValidLicenseUpdateRestriction(String driverID) {
        

        return true;

    }

    //INCOMPLETE
    public static boolean isValidImmutableFields(String driverID) {
        

        return true;

    }



    public void updateDetails(String name, int experienceYears, String licenseType, String address, String birthdate) {
        this.name = name;
        this.experienceYears = experienceYears;
        this.licenseType = licenseType;
        this.address = address;
        this.birthdate = birthdate;
    }
}
