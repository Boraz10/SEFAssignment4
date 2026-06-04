import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



class Bus_Test {

    // Tests for Bus ID rules
    // Testing with id with fewer characters
    @Test
    void B1_1() {
        assertFalse(Bus.validateBusID("123245"));
    }

    // Testing with id with too many characters
    @Test
    void B1_2() {
        assertFalse(Bus.validateBusID("1232456789"));
    }

    // Testing with id with no characters
    @Test
    void B1_3() {
        assertFalse(Bus.validateBusID(""));
    }

    // Testing with id with letters in them
    @Test
    void B1_4() {
        assertFalse(Bus.validateBusID("1234r6y8"));
    }

    // Testing with valid id
    @Test
    void B1_5() {
        assertTrue(Bus.validateBusID("12345678"));
    }

    // Tests for capacity update restricitions
    // Testing where fuel level increases
    @Test
    void B2_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validCapacity(20));
    }

    // Testing where fuel level is set to zero
    @Test
    void B2_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(0));
    }

    // Testing where fuel level decreases
    @Test
    void B2_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(5));
    }

    // Testing where fuel level remains the same
    @Test
    void B2_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(10));
    }

    // Testing where fuel level becomes negative
    @Test
    void B2_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validCapacity(-1));
    }

    // Tests for driver age restiction
    // testing where age is low and capacity is high
    @Test
    void B3_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(25, 70));
    }

    // testing where age is high and capacity is high
    @Test
    void B3_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validateDriverAge(70, 70));
    }

    // testing where age is low and capacity is low
    @Test
    void B3_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(25, 25));
    }

    // testing where age is high and capacity is low
    @Test
    void B3_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(70, 25));
    }

    // testing where age and capacity are both 50
    @Test
    void B3_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(50, 50));
    }

    // Tests for electric bus restrictions
    // Testing where experience is high for electric fuel type
    @Test
    void B4_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverExperience(10));
    }

    // Testing where experience is low for electric fuel type
    @Test
    void B4_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverExperience(2));
    }

    // Testing where experience is exactly 5 for electric fuel type
    @Test
    void B4_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverExperience(5));
    }

    // Testing where experience is high for diesel fuel type
    @Test
    void B4_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverExperience(10));
    }

    // Testing where experience is zerp for electric fuel type
    @Test
    void B4_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverExperience(0));
    }

    // Driver license restriction
    // testing where license tpye is public transport for electricity, valid
    @Test
    void B5_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverLicense("PublicTransport"));
    }

    // testing where license type is heavy for hybrid, valid
    @Test
    void B5_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Hybrid");
        assertTrue(bus.validateDriverLicense("Heavy"));
    }

    // testing where license type is light for electric, invalid
    @Test
    void B5_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverLicense("Light"));
    }

    // testing where license type is light for hybrid, invalid
    @Test
    void B5_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Hybrid");
        assertFalse(bus.validateDriverLicense("Light"));
    }

    // testing where license type is heavy for diesel, valid
    @Test
    void B5_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverLicense("Heavy"));
    }
}