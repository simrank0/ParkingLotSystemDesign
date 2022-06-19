package parkingLot.exception;

/**
 * Generic exception for parking lot exceptions
 */
public class ParkingLotException extends RuntimeException {
    public ParkingLotException() {

    }

    public ParkingLotException(String message) {
        super(message);
    }
}
