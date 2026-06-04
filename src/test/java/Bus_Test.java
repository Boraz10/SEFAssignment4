import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;



class Bus_Test {

    // Tests for Bus ID rules
    @Test
    void B1_1() {
        assertFalse(Bus.validateBusID("123245"));
    }

    @Test
    void B1_2() {
        assertFalse(Bus.validateBusID("1232456789"));
    }

    @Test
    void B1_3() {
        assertFalse(Bus.validateBusID(""));
    }

    @Test
    void B1_4() {
        assertFalse(Bus.validateBusID("1234r6y8"));
    }

    @Test
    void B1_5() {
        assertTrue(Bus.validateBusID("12345678"));
    }

    // Tests for capacity update restricitions
    @Test
    void B2_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validCapacity(20));
    }

    @Test
    void B2_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(0));
    }

    @Test
    void B2_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(5));
    }

    @Test
    void B2_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validCapacity(10));
    }

    @Test
    void B2_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validCapacity(-1));
    }

    // Tests for driver age restiction
    @Test
    void B3_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(25, 70));
    }

    @Test
    void B3_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertFalse(bus.validateDriverAge(70, 70));
    }

    @Test
    void B3_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(25, 25));
    }

    @Test
    void B3_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(70, 25));
    }

    @Test
    void B3_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverAge(50, 50));
    }

    // Tests for electric bus restrictions
    @Test
    void B4_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverExperience(10));
    }

    @Test
    void B4_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverExperience(2));
    }

    @Test
    void B4_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverExperience(5));
    }

    @Test
    void B4_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverExperience(10));
    }

    @Test
    void B4_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverExperience(0));
    }

    // Driver license restriction
    @Test
    void B5_1() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertTrue(bus.validateDriverLicense("publicTransport"));
    }

    @Test
    void B5_2() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Hybrid");
        assertTrue(bus.validateDriverLicense("Heavy"));
    }

    @Test
    void B5_3() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Electricity");
        assertFalse(bus.validateDriverLicense("Light"));
    }

    @Test
    void B5_4() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Hybrid");
        assertFalse(bus.validateDriverLicense("Light"));
    }

    @Test
    void B5_5() {
        Bus bus = new Bus("12345678", "abc", 20, 10, "Diesel");
        assertTrue(bus.validateDriverLicense("Heavy"));
    }
}