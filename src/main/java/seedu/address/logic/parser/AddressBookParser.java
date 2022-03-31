package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddContactedInfoCommand;
import seedu.address.logic.commands.AddReminderCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ContactedOutsideRangeCommand;
import seedu.address.logic.commands.ContactedWithinRangeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteContactedInfoCommand;
import seedu.address.logic.commands.DeleteReminderCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListContactRemindersCommand;
import seedu.address.logic.commands.ListContactedInfoCommand;
import seedu.address.logic.commands.ListDateRemindersCommand;
import seedu.address.logic.commands.ListTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final String trimmedUserInput = userInput.trim();
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(trimmedUserInput);

        if (HashtagCommandParser.isHashtagCommand(trimmedUserInput)) { // in hashtag format, it starts with #.
            return new HashtagCommandParser().parse(trimmedUserInput);
        }

        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case BirthdayCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new BirthdayCommand();

        case ListCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new ListCommand();

        case AddContactedInfoCommand.COMMAND_WORD:
            return new AddContactedCommandParser().parse(arguments);

        case ContactedWithinRangeCommand.COMMAND_WORD:
            return new ContactedWithinRangeCommandParser().parse(arguments);

        case ContactedOutsideRangeCommand.COMMAND_WORD:
            return new ContactedOutsideRangeCommandParser().parse(arguments);

        case ListContactedInfoCommand.COMMAND_WORD:
            return new ListContactedInfoCommandParser().parse(arguments);

        case DeleteContactedInfoCommand.COMMAND_WORD:
            return new DeleteContactedInfoCommandParser().parse(arguments);

        case AddTagCommand.COMMAND_WORD:
            return new AddTagCommandParser().parse(arguments);

        case DeleteTagCommand.COMMAND_WORD:
            return new DeleteTagCommandParser().parse(arguments);

        case ListTagsCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new ListTagsCommand();

        case ExitCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            checkEmptyArguments(arguments);
            return new HelpCommand();

        case AddReminderCommand.COMMAND_WORD:
            return new AddReminderCommandParser().parse(arguments);

        case DeleteReminderCommand.COMMAND_WORD:
            return new DeleteReminderCommandParser().parse(arguments);

        case ListContactRemindersCommand.COMMAND_WORD:
            return new ListContactRemindersCommandParser().parse(arguments);

        case ListDateRemindersCommand.COMMAND_WORD:
            return new ListDateRemindersCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Checks if argument is empty, to be used for commands that do not require arguments.
     *
     * @param args the argument to be checked.
     * @throws ParseException if argument is not empty.
     */
    private void checkEmptyArguments(String args) throws ParseException {
        if (args.length() != 0) {
            throw new ParseException(MESSAGE_INVALID_ARGUMENTS);
        }
    }

}
