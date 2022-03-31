package seedu.address.model.contactedinfo;

import static java.util.Objects.requireNonNull;

import seedu.address.model.date.DocumentedDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

/**
 * Represents a Person's recently contacted information in the address book.
 */
public class ContactedInfo implements Comparable<ContactedInfo> {
    public static final String MESSAGE_CONSTRAINTS =
            "You should provide a valid date and description.\n"
            + RecentDate.MESSAGE_CONSTRAINTS + " and should not be in the future.\n" + Description.MESSAGE_CONSTRAINTS;

    private final Description description;
    private final RecentDate recentDate;

    /**
     * Constructs an {@code ContactedInfo}.
     *
     * @param recentDate A valid description.
     * @param description a valid date.
     */
    public ContactedInfo(RecentDate recentDate, Description description) {
        requireNonNull(recentDate);
        requireNonNull(description);
        this.description = description;
        this.recentDate = recentDate;
    }

    /**
     * Returns the default contacted info.
     *
     * @return the default contacted info.
     */
    public static ContactedInfo getDefaultContactedInfo() {
        return new ContactedInfo(RecentDate.defaultRecentDate(), Description.defaultDesc());
    }

    /**
     * Returns true if a given string is a valid contacted information.
     *
     * @param dateTest date string to test.
     * @param descriptionTest description to test.
     * @return true if a given string is a valid contacted information.
     */
    public static boolean isValidContactedInfo(String dateTest, String descriptionTest) {
        return DocumentedDate.isValidDate(dateTest)
                && Description.isValidDescription(descriptionTest)
                && RecentDate.isValidRecentDate(dateTest);
    }

    /**
     * Returns {@code RecentDate} instance.
     *
     * @return {@code RecentDate} instance.
     */
    public RecentDate getRecentDate() {
        return recentDate;
    }

    /**
     * Returns the number of days passed since the saved ContactedDate.
     *
     * @return an integer representing the number of days passed.
     */
    public Integer getDaysPassed() {
        int daysPassed = recentDate.getDaysPassed();
        assert daysPassed >= 0 : "Days passed should not be less than 0";
        return daysPassed;
    }

    /**
     * Returns {@code Description} instance.
     *
     * @return {@code Description} instance.
     */
    public Description getDescription() {
        return description;
    }

    /**
     * returns contacted information in String format.
     *
     * @return contacted information in String format.
     */
    @Override
    public String toString() {
        return "[" + recentDate.toString()
                + "] "
                + description.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactedInfo // instanceof handles nulls
                && recentDate.equals(((ContactedInfo) other).recentDate)
                && description.equals(((ContactedInfo) other).description)); // state check
    }

    @Override
    public int compareTo(ContactedInfo o) {
        return recentDate.compareTo(o.recentDate);
    }

    @Override
    public int hashCode() {
        return description.hashCode() + recentDate.hashCode();
    }
}
