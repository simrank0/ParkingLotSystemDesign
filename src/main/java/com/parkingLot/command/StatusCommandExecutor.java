package parkingLot.command;

import parkingLot.OutputPrinter;
import parkingLot.model.Car;
import parkingLot.model.Command;
import parkingLot.model.Slot;
import parkingLot.service.ParkingLotService;

import java.util.List;

/**
 * Executor to handle command of fetching the current status of the parking lot.
 * It gives which slot has which car. Car details will have both, its registration number and its color.
 */
public class StatusCommandExecutor extends CommandExecutor {
    public static final String COMMAND_NAME = "status";

    public StatusCommandExecutor(ParkingLotService parkingLotService, OutputPrinter outputPrinter) {
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
        final List<Slot> occupiedSlots = parkingLotService.getOccupiedSlots();

        if (occupiedSlots.isEmpty()) {
            outputPrinter.parkingLotEmpty();
            return;
        }

        outputPrinter.statusHeader();
        for (Slot slot : occupiedSlots) {
            final Car parkedCar = slot.getParkedCar();
            final String slotNumber = slot.getSlotNumber().toString();

            outputPrinter.printWithNewLine(padString(slotNumber, 12) +
                    padString(parkedCar.getRegistrationNumber(), 19) + parkedCar.getColor());
        }
    }

    private static String padString(final String word, int length) {
        String newWord = word;
        for (int count = word.length(); count < length; count++)
            newWord = newWord + " ";
        return newWord;
    }
}
