import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class Driver_Test {

    @Test
    void updateDetails() {
    }

    @Test
    void shouldAcceptValidDriverID() {
        assertTrue(Driver.isValidDriverID("38s%$dseSE"));
    }

    @Test
    void shouldRejectInvalidDriverIDWithWrongLength() {
        assertFalse(Driver.isValidDriverID("38s%$dSE"));
    }

    @Test
    void shouldRejectInvalidDriverWithoutTwoSpecialCharacters() {
        assertFalse(Driver.isValidDriverID("38s%ldseSE"));
    }
}