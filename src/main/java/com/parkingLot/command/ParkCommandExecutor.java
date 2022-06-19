package parkingLot.command;

import parkingLot.OutputPrinter;
import parkingLot.exception.NoFreeSlotAvailableException;
import parkingLot.model.Car;
import parkingLot.model.Command;
import parkingLot.service.ParkingLotService;

/**
 * Executor to hanlde command of parking a car in a slot inside parking lot.
 * This outputs the allotted slot in which the car is parked.
 */
public class ParkCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "park";

    public ParkCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(Command command) {
        return command.getParams().size() == 2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Command command) {
        final Car car = new Car(command.getParams().get(0), command.getParams().get(1));
        try {
            final Integer slot = parkingLotService.park(car);
            outputPrinter.printWithNewLine("Allocated slot number: " + slot +
                    " to car: " + command.getParams().get(0));
        } catch (NoFreeSlotAvailableException e) {
            outputPrinter.parkingLotFull();
        }
    }
}
