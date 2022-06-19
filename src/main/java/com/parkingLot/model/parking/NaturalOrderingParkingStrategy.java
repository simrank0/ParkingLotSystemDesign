package parkingLot.model.parking;

import parkingLot.exception.NoFreeSlotAvailableException;

import java.util.TreeSet;

/**
 * Natural ordering of numbers is used for decing the slot numbers.
 * Example, 1st car will be parked in slot 1, then next in slot 2, then in slot 3, and so on.
 */
public class NaturalOrderingParkingStrategy implements ParkingStrategy {
    TreeSet<Integer> slotTreeSet;

    public NaturalOrderingParkingStrategy() {
        this.slotTreeSet = new TreeSet<>();
    }

    /**
     * @param slotNumber Slot number to be added
     */
    @Override
    public void addSlot(Integer slotNumber) {
        this.slotTreeSet.add(slotNumber);
    }

    /**
     * @param slotNumber Slot number to be removed.
     */
    @Override
    public void removeSlot(Integer slotNumber) {
        this.slotTreeSet.remove(slotNumber);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getNextSlot() {
        if (slotTreeSet.isEmpty()) throw new NoFreeSlotAvailableException();
        return this.slotTreeSet.first();
    }
}
