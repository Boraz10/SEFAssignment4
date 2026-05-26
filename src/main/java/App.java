package app;

public class App{
    public static void main(String[] args) {

        Driver driver = DriverRepository.retrieve("1");

        System.out.println(driver.getName());
    }
}
