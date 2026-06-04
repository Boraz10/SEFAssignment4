import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class BusRepositoryIT {
    
    // Test 1: 
    @Test
    void busIsWhereSheIsMeantToBe() {

        int prev = BusRepository.count();
        Bus bus = new Bus("10000001", "45mn!@opNO", 40, 30.0, "Diesel");
        BusRepository.add(bus);
        Bus stored = BusRepository.retrieve("10000001");
        assertNotNull(stored);
        assertEquals("10000001", stored.getBusID());
        assertEquals(40, stored.getCapacity());
        assertEquals(prev + 1, BusRepository.count());
    }

    // Test 2:
    @Test
    void repeatId() {

        int prev = BusRepository.count();
        Bus buss = new Bus("88888888", "45mn!@opNO", 40, 30.0, "Diesel"); //driver id repeat
        BusRepository.add(buss);
        Bus stored = BusRepository.retrieve("88888888");
        assertNull(stored);
        assertEquals(prev, BusRepository.count());
    }

    // Test 3:
    @Test
    void updateBusCheck() throws IOException {
    
        Bus bus = new Bus("99999999", "99me$(miLF", 40, 30.0, "Diesel");
        BusRepository.add(bus);
        BusRepository.update("99999999", "99me$(miLF", 20, 30.0, "Diesel");
        assertEquals(20, BusRepository.retrieve("99999999").getCapacity());

    }

    // Test 4:
    @Test
    void busAccountedFor() {
        int prev = BusRepository.count();
        Bus bus = new Bus("66666666", "67le^^fiWB", 40, 30.0, "Diesel");
        assertEquals(prev + 1,  BusRepository.count());

    }
}
