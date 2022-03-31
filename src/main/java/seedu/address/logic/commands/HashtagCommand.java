package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonWithTagPredicate;

/**
 * Finds and lists all persons in address book who is tagged to the given tag.
 * Keyword matching is case-insensitive.
 */
public class HashtagCommand extends Command {

    public static final String COMMAND_WORD = "#";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts tagged to "
            + "the specified tag (case-insensitive) and displays them as a list with index numbers.\n\n"
            + "Parameters: TAG\n\n"
            + "Example: " + COMMAND_WORD + "client";

    private final PersonWithTagPredicate predicate;

    /**
     * Constructs a {@code HashtagCommand} object with the given {@code PersonWithTagPredicate}.
     *
     * @param predicate the predicate for the command execution in the context of {@code HashtagCommand}.
     */
    public HashtagCommand(PersonWithTagPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
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
        if (!(other instanceof HashtagCommand)) {
            return false;
        }

        // state check
        return predicate.equals(((HashtagCommand) other).predicate);
    }
}
