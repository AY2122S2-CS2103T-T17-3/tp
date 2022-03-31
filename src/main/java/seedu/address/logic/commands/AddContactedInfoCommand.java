package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.person.Person;

/**
 * Adds or changes the recent date of an existing person in the address book.
 */
public class AddContactedInfoCommand extends Command {

    public static final String MESSAGE_ADD_INTERACTION_SUCCESS = "Recorded interaction with %1$s"
            + "\n\nSaved interaction as:\n%2$s";
    public static final String MESSAGE_DUPLICATE_CONTACTED_INFO = "Interaction records already contains: %1$s";

    public static final String COMMAND_WORD = "log";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the last contacted date  "
            + "by the index number used in the displayed contact list. "
            + "Existing date will be overwritten by the input.\n\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [DATE] "
            + "des/ [DESCRIPTION]\n\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ 2020-02-02 "
            + "des/ meetup.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Last contacted date: %2$s, Description: %3$s";

    private final Index index;
    private final ContactedInfo contactedInfo;

    /**
     * Creates an ContactedCommand to add the specified {@code Person}
     *
     * @param index of the person in the filtered person list to edit the contacted date
     * @param contactedInfo of the person to be updated to
     */
    public AddContactedInfoCommand(Index index, ContactedInfo contactedInfo) {
        requireAllNonNull(index, contactedInfo);

        this.index = index;
        this.contactedInfo = contactedInfo;
    }

    /**
     * Creates a CommandResult object.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the CommandResult object.
     * @throws CommandException if it is an invalid command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.containsContactedInfo(contactedInfo)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_CONTACTED_INFO, contactedInfo.toString()));
        }
        ArrayList<ContactedInfo> updatedContactedInfo = new ArrayList<>(personToEdit.getContactedInfoList());
        updatedContactedInfo.add(contactedInfo);
        Collections.sort(updatedContactedInfo);

        Person editedPerson = new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getAddress(),
                personToEdit.getBirthDate(),
                Collections.unmodifiableList(updatedContactedInfo),
                personToEdit.getTags(),
                personToEdit.getReminderList());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(
                String.format(MESSAGE_ADD_INTERACTION_SUCCESS, editedPerson.getName(), contactedInfo.toString()));
    }

    /**
     * Adds a {ContectInfo} to a ContactedInfo ArrayList by creating a new {@code ArrayList} (immutable).
     *
     * @param contactedInfoList the info ArrayList to add a {@code ContactedInfo} to.
     * @param infoToAdd the {@code ContactedInfo} to be added.
     * @return an immutable ContactedInfo ArrayList consisting
     * of the existing {@code ContactedInfo} and {@code infoToAdd}.
     */
    private static ArrayList<ContactedInfo> addContactedInfoToList(
            ArrayList<ContactedInfo> contactedInfoList, ContactedInfo infoToAdd) {

        ArrayList<ContactedInfo> updatedInfoList = new ArrayList<>(contactedInfoList);
        updatedInfoList.add(infoToAdd);

        return (ArrayList<ContactedInfo>) Collections.unmodifiableList(updatedInfoList);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddContactedInfoCommand)) {
            return false;
        }

        // state check
        AddContactedInfoCommand e = (AddContactedInfoCommand) other;
        return index.equals(e.index)
                && contactedInfo.equals(contactedInfo);
    }
}

