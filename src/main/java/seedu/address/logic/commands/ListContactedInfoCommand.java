package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all reminders for a contact.
 */
public class ListContactedInfoCommand extends Command {
    public static final String COMMAND_WORD = "logs";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all interaction records for a specified contact. \n\n"
            + "Parameters: "
            + "INDEX (Must be a positive integer)\n\n"
            + "Example: " + COMMAND_WORD + " "
            + "3";

    public static final String MESSAGE_LIST_CONTACTED_INFO_SUCCESS = "Listed all interaction records for %1$s:\n%2$s";

    private final Index index;

    /**
     * Creates a ListContactRemindersCommand to list the reminders.
     */
    public ListContactedInfoCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getOneBased() > lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personSpecified = model.getFilteredPerson(index);

        return new CommandResult(String.format(MESSAGE_LIST_CONTACTED_INFO_SUCCESS,
                personSpecified.getName(),
                personSpecified.getContactedInfoListToString()));
    }

    @Override
    public boolean equals(Object other) {
        return (other == this) // short circuit if same object
                || (other instanceof ListContactedInfoCommand) // instanceof handles nulls
                && index.equals(((ListContactedInfoCommand) other).index); // state check
    }

}
