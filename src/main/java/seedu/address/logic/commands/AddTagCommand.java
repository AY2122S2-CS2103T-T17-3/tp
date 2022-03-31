package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Adds a tag to an existing person in the address book, if the tag does not exist.
 */
public class AddTagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Tags a contact to a category, "
            + "as specified by the index number used in the displayed contact list. The tag will be added only if "
            + "the tag is valid and is not already tagged to the contact (case-insensitive).\n\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TAG + "TAG]\n\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_TAG + "friends";

    public static final String MESSAGE_ADD_TAG_SUCCESS = "Added tag: %1$s";
    public static final String MESSAGE_DUPLICATE_TAG = "This person is already tagged to %1$s.";

    private final Index index;
    private final Tag tag;

    /**
     * Constructs an {@code AddTagCommand} with the given {@code Index} and {@code Tag}.
     *
     * @param index of the person in the filtered person list to add the {@code Tag}.
     * @param tag to be tagged to the {@code Person} specified by {@code index}.
     */
    public AddTagCommand(Index index, Tag tag) {
        requireNonNull(index);
        requireNonNull(tag);

        this.index = index;
        this.tag = tag;
    }

    /**
     * Adds a tag to an existing person in the address book, if the tag does not exist.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the command result after the command execution.
     * @throws CommandException if the {@code Tag} already exists or the {@code Index} is invalid.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        if (personToEdit.hasTag(this.tag)) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TAG, this.tag));
        }

        Person editedPerson = createPersonWithAddedTag(personToEdit, this.tag);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_TAG_SUCCESS, editedPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of personToEdit {@code Person}
     * added with tagToAdd {@code Tag}.
     *
     * @param personToEdit the {@code Person} to add the {@code Tag} to.
     * @param tagToAdd the {@code Tag} to add to {@code Person}.
     * @return the edited {@code Person} added with tagToAdd {@code Tag}.
     */
    private static Person createPersonWithAddedTag(Person personToEdit, Tag tagToAdd) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        BirthDate updatedBirthDate = personToEdit.getBirthDate();
        List<ContactedInfo> updatedContactedInfo = personToEdit.getContactedInfoList();
        Set<Tag> updatedTags = addTagToSet(personToEdit.getTags(), tagToAdd);
        ReminderList updatedReminders = personToEdit.getReminderList();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthDate,
                updatedContactedInfo, updatedTags, updatedReminders);
    }

    /**
     * Adds a {Tag} to a tag set by creating a new {@code HashSet} (immutable).
     *
     * @param tags the tag set to add a {@code Tag} to.
     * @param tagToAdd the {@code Tag} to be added.
     * @return an immutable tag set consisting of the existing {@code Tag} and {@code tagToAdd} (only unique tags).
     */
    private static Set<Tag> addTagToSet(Set<Tag> tags, Tag tagToAdd) {
        Set<Tag> updatedTags = new HashSet<>(tags);
        updatedTags.add(tagToAdd);
        return Collections.unmodifiableSet(updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTagCommand)) {
            return false;
        }

        // state check
        AddTagCommand e = (AddTagCommand) other;
        return this.index.equals(e.index)
                && this.tag.equals(e.tag);
    }
}
