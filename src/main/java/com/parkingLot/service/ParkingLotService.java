package parkingLot.service;

import parkingLot.exception.ParkingLotException;
import parkingLot.model.Car;
import parkingLot.model.ParkingLot;
import parkingLot.model.Slot;
import parkingLot.model.parking.NaturalOrderingParkingStrategy;
import parkingLot.model.parking.ParkingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Service for enabling the functioning of parking lot.
 * This contains the logic behind parking services.
 */
public class ParkingLotService {
    private ParkingLot parkingLot;
    private ParkingStrategy parkingStrategy;

    /**
     * Allots a parking lot to a parking service. Throws {@link ParkingLotException} if there is
     * already a parking lot allotted to the service previously.
     *
     * @param parkingLot                     Parking lot to be allotted.
     * @param naturalOrderingParkingStrategy Strategy to be used while parking.
     */
    public void createParkingLot(ParkingLot parkingLot, NaturalOrderingParkingStrategy naturalOrderingParkingStrategy) {
        if (this.parkingLot != null) {
            throw new ParkingLotException("Parking lot already exists.");
        }
        this.parkingLot = parkingLot;
        this.parkingStrategy = naturalOrderingParkingStrategy;
        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            parkingStrategy.addSlot(i);
        }
    }

    /**
     * Parks a {@link Car} into a parking lot. {@link ParkingStrategy} is used to decide the
     * slot number and then the car is parked into the {@link ParkingLot} into that slot number.
     *
     * @param car Car to be parked.
     * @return Slot number in which the car is parked.
     */
    public Integer park(Car car) {
        validateParkingLotExists();
        final Integer nextFreeSlot = parkingStrategy.getNextSlot();
        parkingLot.park(car, nextFreeSlot);
        parkingStrategy.removeSlot(nextFreeSlot);
        return nextFreeSlot;
    }

    /**
     * Unparks a car from a slot. Freed slot number is given back to the parking strategy
     * and is available for future parkings.
     *
     * @param slotNumber Slot number to be freed.
     */
    public void makeSlotFree(int slotNumber) {
        validateParkingLotExists();
        parkingLot.makeSlotFree(slotNumber);
        parkingStrategy.addSlot(slotNumber);
    }

    /**
     * Gets the list of all the slots which are occupied.
     *
     * @return list of occupied slots.
     */
    public List<Slot> getOccupiedSlots() {
        validateParkingLotExists();
        final List<Slot> occupiedSlotsList = new ArrayList<>();
        final Map<Integer, Slot> allSlots = parkingLot.getSlots();

        for (int i = 1; i <= parkingLot.getCapacity(); i++) {
            if (allSlots.containsKey(i)) {
                final Slot slot = allSlots.get(i);
                if (!slot.isSlotFree()) {
                    occupiedSlotsList.add(slot);
                }
            }
        }
        return occupiedSlotsList;
    }

    /**
     * Helper method to validate existence of parking lot.
     */
    private void validateParkingLotExists() {
        if (parkingLot == null)
            throw new ParkingLotException("Parking lot does not exist!");
    }

    /**
     * Gets all slots for which car with a given color is parked.
     *
     * @param color Color to be searched.
     * @return All matching slots.
     */
    public List<Slot> getSlotsForColor(String color) {
        final List<Slot> occupiedSlots = getOccupiedSlots();
        return occupiedSlots.stream()
                .filter(slot -> slot.getParkedCar().getColor().equals(color))
                .collect(Collectors.toList());
    }

}
