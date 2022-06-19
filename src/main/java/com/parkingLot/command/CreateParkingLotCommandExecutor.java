package parkingLot.command;

import parkingLot.OutputPrinter;
import parkingLot.model.Command;
import parkingLot.model.ParkingLot;
import parkingLot.model.parking.NaturalOrderingParkingStrategy;
import parkingLot.service.ParkingLotService;
import parkingLot.validator.IntegerValidator;

/**
 * Executor to handle command of creating the initial parking lot.
 */
public class CreateParkingLotCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "create_parking_lot";

    public CreateParkingLotCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(Command command) {
        if (command.getParams().size() != 1)
            return false;
        return IntegerValidator.isInteger(command.getParams().get(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Command command) {
        final int parkingLotCapacity = Integer.parseInt(command.getParams().get(0));
        final ParkingLot parkingLot = new ParkingLot(parkingLotCapacity);
        parkingLotService.createParkingLot(parkingLot, new NaturalOrderingParkingStrategy());
        outputPrinter.printWithNewLine("Created a parking lot with " + parkingLot.getCapacity() + " slots.");
    }
}
