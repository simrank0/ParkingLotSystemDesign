package parkingLot.model;

import parkingLot.exception.InvalidSlotException;
import parkingLot.exception.ParkingLotException;
import parkingLot.exception.SlotAlreadyOccupiedException;

import java.util.HashMap;
import java.util.Map;

/**
 * Model to represent functioning of a parking lot
 */

public class ParkingLot {
    private static int MAX_CAPACITY = 100000;
    private int capacity;
    private Map<Integer, Slot> slots;

    public int getCapacity() {
        return capacity;
    }

    public Map<Integer, Slot> getSlots() {
        return slots;
    }

    public ParkingLot(final int capacity) {
        if (capacity > MAX_CAPACITY || capacity < 0)
            throw new ParkingLotException("Invalid capacity given for parking lot");

        this.capacity = capacity;
        this.slots = new HashMap<>();
    }

    /**
     * Helper method to get {@link Slot} object from a given slot number.
     * If slot does not exist, new slot will be created and returned.
     *
     * @param slotNumber Slot number
     * @return Slot.
     */
    public Slot getSlot(final Integer slotNumber) {
        if (slotNumber > getCapacity() || slotNumber <= 0)
            throw new InvalidSlotException();
        Map<Integer, Slot> slotMap = getSlots();
        if (!slotMap.containsKey(slotNumber))
            slotMap.put(slotNumber, new Slot(slotNumber));
        return slotMap.get(slotNumber);
    }

    /**
     * Parks a car into a given slot.
     *
     * @param car        Car to be parked.
     * @param slotNumber Slot number in which car has to be parked.
     * @return {@link Slot} if parking succeeds. If the slot is already occupied then
     * {@link SlotAlreadyOccupiedException} is thrown.
     */
    public Slot park(final Car car, final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        if (!slot.isSlotFree())
            throw new SlotAlreadyOccupiedException();
        slot.assignSlot(car);
        return slot;
    }

    /**
     * Makes the slot free from the current parked car.
     *
     * @param slotNumber Slot number to be freed.
     * @return {@link Slot} Freed slot.
     */
    public Slot makeSlotFree(final Integer slotNumber) {
        final Slot slot = getSlot(slotNumber);
        slot.unassignCar();
        return slot;
    }

}
