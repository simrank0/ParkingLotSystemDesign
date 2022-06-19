package parkingLot.model;

/**
 * Model represents slots in a parking lot.
 */
public class Slot {
    private Car parkedCar;
    private Integer slotNumber;

    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Car getParkedCar() {
        return parkedCar;
    }

    public boolean isSlotFree() {
        return parkedCar == null;
    }

    public void assignSlot(Car car) {
        this.parkedCar = car;
    }

    public void unassignCar() {
        this.parkedCar = null;
    }
}
