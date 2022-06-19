package parkingLot;

/**
 * Printer. Prints output to the user.
 */
public class OutputPrinter {
    public void welcome() {
        printWithNewLine("Welcome to the parking lot!");
    }

    public void end() {
        printWithNewLine("Thanks for using our service!");
    }

    public void notFound() {
        printWithNewLine("Not found.");
    }

    public void invalidFile() {
        printWithNewLine("Invalid file given.");
    }

    public void printWithNewLine(String result) {
        System.out.println(result);
    }

    public void parkingLotFull() {
        printWithNewLine("Sorry, parking is currently full.");
    }

    public void parkingLotEmpty() {
        printWithNewLine("Parking lot is empty.");
    }

    public void statusHeader() {
        printWithNewLine("Slot No.    Registration No    Colour");
    }
}
