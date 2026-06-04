import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class BusIT {
    
    // Test 1: 
    @Test
    void busIsWhereSheIsMeantToBe() {
        
        BusRepository.clearDB();
        Bus bus = new Bus("22222222", "45mn!@opNO", 40, 30.0, "Diesel");
        BusRepository.add(bus);
        Bus stored = BusRepository.retrieve("22222222");
        assertNotNull(stored);
        assertEquals("22222222", stored.getBusID());
        assertEquals(40, stored.getCapacity());
        BusRepository.clearDB();
    }

    // Test 2:
    @Test
    void badBusId() {

        int prev = BusRepository.count();
        Bus buss = new Bus("8888888", "45mn!@opNO", 40, 30.0, "Diesel"); //driver id repeat
        BusRepository.add(buss);
        Bus stored = BusRepository.retrieve("8888888");
        assertNull(stored);
        assertEquals(prev, BusRepository.count());
        BusRepository.clearDB();
    }

    // Test 3:
    @Test
    void updateBusCheck() throws IOException {
    
        Bus bus = new Bus("99999999", "45mn!@opNO", 40, 30.0, "Diesel");
        BusRepository.add(bus);
        BusRepository.update("99999999", "45mn!@opNO", 20, 30.0, "Diesel");
        assertEquals(20, BusRepository.retrieve("99999999").getCapacity());
        BusRepository.clearDB();

    }

    // Test 4:
    @Test
    void busAccountedFor() {
        int prev = BusRepository.count();
        Bus bus = new Bus("66666666", "45mn!@opNO", 40, 30.0, "Diesel");
        BusRepository.add(bus);
        assertEquals(prev + 1,  BusRepository.count());
        BusRepository.clearDB();

    }
}
