import java.io.IOException;

public class App{
    public static void main(String[] args) throws IOException {

        Driver driver = DriverRepository.retrieve("1");
        if(driver == null){
            System.out.println("could not find that driver");
            return;
        }

        System.out.println(driver.getName());


        BusRepository.update("12345678", 12, 3, "qse");

//        assert bus2 != null;
//        System.out.println(bus2.getBusID());


    }
}
