package parkingLot.command;

import parkingLot.OutputPrinter;
import parkingLot.model.Command;
import parkingLot.service.ParkingLotService;
import parkingLot.validator.IntegerValidator;

import java.util.List;

/**
 * Executor to handle command of freeing a slot from a car.
 */
public class LeaveCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "leave";

    public LeaveCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(Command command) {
        final List<String> params = command.getParams();
        if (params.size() != 1)
            return false;
        return IntegerValidator.isInteger(params.get(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Command command) {
        final int slotNumber = Integer.parseInt(command.getParams().get(0));
        parkingLotService.makeSlotFree(slotNumber);
        outputPrinter.printWithNewLine("Slot number " + slotNumber + " is now free.");
    }
}
