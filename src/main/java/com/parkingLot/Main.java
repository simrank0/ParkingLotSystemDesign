package parkingLot;

import parkingLot.command.CommandExecutorFactory;
import parkingLot.exception.InvalidModeException;
import parkingLot.mode.FileMode;
import parkingLot.mode.InteractiveMode;
import parkingLot.service.ParkingLotService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        final OutputPrinter outputPrinter = new OutputPrinter();
        final ParkingLotService parkingLotService = new ParkingLotService();
        final CommandExecutorFactory commandExecutorFactory =
                new CommandExecutorFactory(parkingLotService);
        if (isInteractiveMode(args)) {
            new InteractiveMode(commandExecutorFactory, outputPrinter).process();
        } else if (isFileMode(args)) {
            new FileMode(commandExecutorFactory, outputPrinter, args[0]).process();
        } else
            throw new InvalidModeException();
    }

    /**
     * Checks whether the program is running in file mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in file mode.
     */
    private static boolean isFileMode(String[] args) {
        return args.length == 1;
    }

    /**
     * Checks whether program is running in interactive shell mode.
     *
     * @param args Command line arguments.
     * @return Boolean indicating whether in interactive mode.
     */
    private static boolean isInteractiveMode(String[] args) {
        return args.length == 0;
    }
}
