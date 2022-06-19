package parkingLot.command;

import parkingLot.OutputPrinter;
import parkingLot.model.Command;
import parkingLot.service.ParkingLotService;

/**
 * Executor to handle command of exiting from a parking lot. Used in interactive mode to exit.
 */
public class ExitCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "exit";

    public ExitCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
        super(parkingLotService, outputPrinter);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(Command command) {
        return command.getParams().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Command command) {
        outputPrinter.end();
    }
}
