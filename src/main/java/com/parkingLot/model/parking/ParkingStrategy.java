package parkingLot.model.parking;

/**
 * Strategy which will be used to decide how slots will be used to park cars.
 */
public interface ParkingStrategy {

    /**
     * Add a new slot to parking strategy. After adding this new slot will become available for parkings.
     *
     * @param slotNumber Slot number to be added
     */
    void addSlot(Integer slotNumber);

    /**
     * Remove a slot from parking strategy. After removing, this slot won't be available for parking.
     *
     * @param slotNumber Slot number to be removed.
     */
    void removeSlot(Integer slotNumber);

    /**
     * Get the next free slot as per parking strategy.
     *
     * @return Next free slot number
     */
    Integer getNextSlot();
}
