package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonOutsideDateRangePredicate;

/**
 * List all persons contacted outside of the designated number of days.
 */
public class ContactedOutsideRangeCommand extends Command {

    public static final String COMMAND_WORD = "after";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts contacted outside "
            + "the specified range of days and displays them as a list with index numbers.\n\n"
            + "Parameters: number of days (Positive integer or 0)\n\n"
            + "Example: " + COMMAND_WORD + " 5";
    private final PersonOutsideDateRangePredicate predicate;

    /**
     * Constructs a {@code ContactedOutsideRangeCommand} object with the given {@code PersonOutsideDateRangePredicate}.
     *
     * @param predicate the predicate for the command execution in the context of {@code ContactedOutsideRangeCommand}.
     */
    public ContactedOutsideRangeCommand(PersonOutsideDateRangePredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }
    /**
     * Executes the command and returns the {@code CommandResult}
     *
     * @param model {@code Model} which the command should operate on.
     * @return {@code CommandResult} of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonListSize()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof ContactedOutsideRangeCommand)) {
            return false;
        }
        // state check
        return predicate.equals(((ContactedOutsideRangeCommand) other).predicate);
    }
}
