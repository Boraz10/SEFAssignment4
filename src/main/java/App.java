import java.io.IOException;

public class App{
    public static void main(String[] args) throws IOException {

        Driver driver = DriverRepository.retrieve("1");
        if(driver == null){
            System.out.println("could not find that driver");
            return;
        }

        System.out.println(driver.getName());


        // Testing bus
        System.out.println(BusRepository.retrieve("ss").getFuelType());

//        assert bus2 != null;
//        System.out.println(bus2.getBusID());


    }
}
