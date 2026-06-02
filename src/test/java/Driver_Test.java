import org.junit.jupiter.api.Test;


public class Driver_Test {

    @Test
    void updateDetails() {
    }

    @Test
    void shouldAcceptValidDriverID() {
        assertTrue(Driver.isValidDriverID())
    }

    @Test
    void shouldRejectInvalidDriverIDWithWrongLength() {
        
    }

    @Test
    void shouldRejectInvalidDriverWithoutTwoSpecialCharacters() {
        
    }
}