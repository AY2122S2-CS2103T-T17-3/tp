package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Lists all birthdays occurring today.
 */
public class BirthdayCommand extends Command {

    public static final String COMMAND_WORD = "birthdays";

    public static final String MESSAGE_SUCCESS = "Here are the people with birthdays today.\n"
                                                    + Messages.MESSAGE_PERSONS_LISTED_OVERVIEW
                                                    + "\n\nSend them a birthday message!";
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS_WITH_BIRTHDAY_TODAY);
        return new CommandResult(String.format(MESSAGE_SUCCESS, model.getFilteredPersonListSize()));
    }

}
