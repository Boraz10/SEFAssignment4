import java.io.IOException;

public class App{
    public static void main(String[] args) throws IOException {

//        Driver driver = DriverRepository.retrieve("1");
//        if(driver == null){
//            System.out.println("could not find that driver");
//            return;
//        }
//
//        System.out.println(driver.getName());

        Bus bus = new Bus("12315271", "68pq#&rsLM", 90, 3, "Hybrid");

        BusRepository.add(bus);

//        assert bus2 != null;
//        System.out.println(bus2.getBusID());


    }
}
