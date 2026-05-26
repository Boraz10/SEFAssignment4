import java.util.ArrayList;

public class BusRepository {

    // TODO: Instead of arraylist, i think we need to save the data to a txt file
    private ArrayList<Bus> busList = new ArrayList<>();

    public void add(Bus bus) {
        busList.add(bus);

    }

    public void update(String busID, int capacity, double fuelLevel, String fuelType) {
        for (Bus b : busList) {
            if (b.getBusID().equals(busID)) {
                b.updateDetails(capacity, fuelLevel, fuelType);
                return;
            }
        }
        System.out.printf("Could not find bus with id: %s\n", busID);
    }

    public void update(int index, int capacity, double fuelLevel, String fuelType) {
        if (index >= busList.size()) {
            System.out.printf("Index %d out of range\n", index);
            return;
        }

        busList.get(index).updateDetails(capacity, fuelLevel, fuelType);
    }

    public Bus retrieve(String busID) {
        for (Bus b : busList) {
            if (b.getBusID().equals(busID)) {
                return b;
            }
        }
        System.out.printf("Could not find bus with id: %s\n", busID);
        return null;
    }

    public int count() {
        return busList.size();
    }
}
