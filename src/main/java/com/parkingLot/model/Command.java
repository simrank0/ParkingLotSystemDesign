package parkingLot.model;

import parkingLot.exception.InvalidCommandException;

import java.util.Arrays;
import java.util.List;

/**
 * Model object to represent input command.
 */
public class Command {

    private static final String SPACE = " ";
    private String commandName;
    private List<String> params;


    public String getCommandName() {
        return commandName;
    }

    public List<String> getParams() {
        return params;
    }

    /**
     * Constructor. It takes input line and parses the main.java.com.ParkingLot.command name and params out of it.
     * If the main.java.com.ParkingLot.command or the given name is not valid then it throws {@link InvalidCommandException}
     *
     * @param inputLine
     */
    public Command(final String inputLine) {
        final List<String> tokensList = Arrays.stream(inputLine.trim().split(SPACE))
                .map(String::trim)
                .filter(token -> (token.length() > 0))
                .toList();
        if (tokensList.size() == 0)
            throw new InvalidCommandException();
        commandName = tokensList.get(0).toLowerCase();
        params = tokensList.subList(1, tokensList.size());
    }
}
