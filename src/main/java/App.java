public class App{
    public static void main(String[] args) {

        Driver driver = DriverRepository.retrieve("1");
        if(driver == null){
            System.out.println("could not find that driver");
            return;
        }

        System.out.println(driver.getName());
    }
}
