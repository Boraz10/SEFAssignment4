import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;


public class Driver_Test {

    @Test
    void updateDetails() {
    }

    //D1: DOULTON
    //THINK ABOUT DOING DUPLICATE HERE BUT MAY NEED TO DO THAT IN REPOSITORY
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


    //D2: DOULTON WILL DO TOMMOROW

    //D3: DOULTON WILL DO TOMMOROW

    //D4: DOULTON WILL DO TOMMOROW

    //D4: DOULTON WILL DO TOMMOROW

}