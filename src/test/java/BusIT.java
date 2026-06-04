import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class BusIT {
    
    // Test 1: 
    @Test
    void busIsWhereSheIsMeantToBe() {
        
        BusRepository.clearDB(); // clear db
        // create new bus
        Bus bus = new Bus("22222222", "68pq#&rsLM", 40, 30.0, "Diesel");
        BusRepository.add(bus); // add bus to repo
        // store bus object by busID search
        Bus stored = BusRepository.retrieve("22222222");
        assertNotNull(stored); //checks it isnt Null
        assertEquals("22222222", stored.getBusID()); //Checks Id is same as searched for
        assertEquals(40, stored.getCapacity()); //ensure capacity is also the same
        BusRepository.clearDB(); // clear db
    }

    // Test 2:
    @Test
    void badBusId() {

        int prev = BusRepository.count();       //store previous bus count
        // Create 2 new busses with invalid bud id (7 , 9). in length
        Bus buss = new Bus("8888888", "68pq#&rsLM", 40, 30.0, "Diesel");
        Bus busses = new Bus("888888888", "68pq#&rsLM", 40, 30.0, "Diesel");
        // Add busses to repo
        BusRepository.add(buss);
        BusRepository.add(busses);
        //Attempt to reteive bus from ID
        Bus stored = BusRepository.retrieve("8888888");
        Bus cryoChamber = BusRepository.retrieve("8888888");
        // if null test passed
        assertNull(stored);
        assertNull(cryoChamber);
        // count should not have gone up
        assertEquals(prev, BusRepository.count());
        BusRepository.clearDB(); // clear db
    }

    // Test 3:
    @Test
    void updateBusCheck() throws IOException {
        // create new bus 
        Bus bus = new Bus("99999999", "68pq#&rsLM", 40, 30.0, "Diesel");
        // add bus
        BusRepository.add(bus);
        // update capacity to 20
        BusRepository.update("99999999", "68pq#&rsLM", 20, 30.0, "Diesel");
        // check that capcity ==2
        assertEquals(20, BusRepository.retrieve("99999999").getCapacity());
        BusRepository.clearDB(); // clear db

    }

    // Test 4:
    @Test
    void busAccountedFor() {
        int prev = BusRepository.count(); // previous count value
        // create new bus
        Bus bus = new Bus("66666666", "68pq#&rsLM", 40, 30.0, "Diesel");
        BusRepository.add(bus); // add bus
        // checks count has been updates.
        assertEquals(prev + 1,  BusRepository.count());
        BusRepository.clearDB(); // clear db

    }
}
