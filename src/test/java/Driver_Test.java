import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class Driver_Test {

    //DOULTON
    
    @Test
    void shouldAcceptValidDriverID() {
        assertTrue(Driver.isValidDriverID("38s%$dseSE"));
    }

    @Test
    void shouldRejectInvalidDriverIDWithWrongLength() {
        assertFalse(Driver.isValidDriverID("38s%$dSE"));
    }

    @Test
    void shouldRejectInvalidDriverIDWithoutTwoSpecialCharacters() {
        assertFalse(Driver.isValidDriverID("38s%ldseSE"));
    }


    // DOULTON

    @Test
    void shouldAcceptValidAddress() {
        assertTrue(Driver.isValidAddress("10|Swanston Street|Melbourne|VIC|Australia"));
    }

    @Test
    void shouldRejectInvalidAddressWithMissingSections() {
        assertFalse(Driver.isValidAddress("10|Swanston Street|VIC|Australia"));
    }

    @Test
    void shouldRejectInvalidAddressWithNoDigitStreetNumbers() {
        assertFalse(Driver.isValidAddress("Ten|Swanston Street|Melbourne|VIC|Australia"));
    }

    //DOULTON 

    @Test
    void shouldAcceptValidBirthDate() {
        assertTrue(Driver.isValidBirthDate("22-11-1990"));
    }

    @Test
    void shouldRejectBirthDateWithWrongSeperator() {
        assertFalse(Driver.isValidBirthDate("22/11/1990"));
    }

    @Test
    void shouldRejectBirthDateWithWrongOrder() {
        assertFalse(Driver.isValidBirthDate("1990-11-22"));
    }

    //DOULTON 

    @Test
    void shouldRejectLicenceChangeWhenExperienceIsGreaterTenYears() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        15,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(15, "Light", "10|Swanston Street|Melbourne|VIC|Australia", "22-11-1990");

       assertEquals("Heavy", driver.getLicenseType());
    }

    @Test
    void shouldAcceptLicenceChangeWhenExperienceIsLessThanOrEqualToTenYears() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        10,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(10, "Light", "10|Swanston Street|Melbourne|VIC|Australia", "22-11-1990");

       assertEquals("Light", driver.getLicenseType());
    }

    @Test
    void shouldUpdateDetailsWhenExperienceIsGreaterTenYearsButLicenseTypeIsTheSame() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        15,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(15, "Heavy", "20|Windsor Street|Melbourne|VIC|Australia", "22-11-1990");

       assertEquals("Heavy", driver.getLicenseType());
       assertEquals("20|Windsor Street|Melbourne|VIC|Australia", driver.getAddress());
    }

    

    //DOULTON 

   @Test
    void shouldOnlyUpdateAuthorizedFields() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        4,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(5, "Medium", "20|Windsor Street|Melbourne|VIC|Australia", "23-11-1990");

       assertEquals("38s%$dseSE", driver.getDriverID());
       assertEquals("Tim", driver.getName());
       assertEquals(5, driver.getExperienceYears());
       assertEquals("Medium", driver.getLicenseType());
       assertEquals("20|Windsor Street|Melbourne|VIC|Australia", driver.getAddress());
       assertEquals("23-11-1990", driver.getBirthdate());
    }

    @Test
    void shouldNotChangeNameAfterUpdate() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        4,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(5, "Medium", "20|Windsor Street|Melbourne|VIC|Australia", "23-11-1990");

       assertEquals("Tim", driver.getName());
    }

    @Test
    void shouldRetainOriginalDriverIDAfterUpdatingDetails() {
       Driver driver = new Driver(
        "38s%$dseSE",
        "Tim",
        4,
        "Heavy",
        "10|Swanston Street|Melbourne|VIC|Australia",
        "22-11-1990"
       );

       driver.updateDetails(5, "Medium", "20|Windsor Street|Melbourne|VIC|Australia", "23-11-1990");

       assertEquals("38s%$dseSE", driver.getDriverID());
       
    }

}