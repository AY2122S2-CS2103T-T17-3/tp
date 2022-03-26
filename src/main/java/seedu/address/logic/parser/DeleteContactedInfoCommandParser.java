package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_CONTACTED_INFO;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteContactedInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteContactedInfoCommandParser implements Parser<DeleteContactedInfoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteContactedInfoCommand
     * and returns a DeleteContactedInfoCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteContactedInfoCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DELETE_CONTACTED_INFO);

        Index index;
        Index contactedInfoIndex;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactedInfoCommand.MESSAGE_USAGE), pe);
        }

        if (argumentMultimap.getValue(PREFIX_DELETE_CONTACTED_INFO).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactedInfoCommand.MESSAGE_USAGE));
        }

        if (!isNumeric(argumentMultimap.getValue(PREFIX_DELETE_CONTACTED_INFO).get())) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactedInfoCommand.MESSAGE_USAGE));
        }

        try {
            contactedInfoIndex = ParserUtil.parseIndex(
                    argumentMultimap.getValue(PREFIX_DELETE_CONTACTED_INFO).get());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactedInfoCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteContactedInfoCommand(index, contactedInfoIndex);
    }

    /**
     * Return true if String is a number.
     *
     * @param strNum the String to test.
     * @return true if String is a number
     */
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}

