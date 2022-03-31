package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;

/**
 * A list of tags that enforces uniqueness between its elements and does not allow nulls.
 * A tag is considered unique by comparing using {@code Tag#equals(Object)}. As such, adding and updating of
 * tags uses {@code Tag#equals(Object)} for equality to ensure that the person being added or updated is
 * unique in terms of identity in the {@code UniqueTagList}. {@code UniqueTagList} uses {@code HashMap}
 * to keep track of the occurrence frequency of each {@code Tag}. The {Tag} only exists in the hash map if and only
 * if the frequency is more than 0.
 *
 * Supports a minimal set of list operations.
 *
 * @see Tag#equals(Object)
 * @see HashMap
 */
public class UniqueTagList implements ReadOnlyUniqueTagList {

    private final Logger logger = LogsCenter.getLogger(UniqueTagList.class);

    private final HashMap<Tag, Integer> tagFrequencyMap;

    /**
     * Constructs an {@code UniqueTagList} object.
     */
    public UniqueTagList() {
        tagFrequencyMap = new HashMap<>();
    }

    /**
     * Constructs a copy of the specified {@code UniqueTagList}.
     *
     * @param uniqueTagList the {@code UniqueTagList} to be copied.
     */
    private UniqueTagList(UniqueTagList uniqueTagList) {
        tagFrequencyMap = new HashMap<>(uniqueTagList.tagFrequencyMap);
    }

    /**
     * Returns a copy of this {@code UniqueTagList}.
     *
     * @return a copy of this {@code UniqueTagList}.
     */
    public UniqueTagList getCopy() {
        return new UniqueTagList(this);
    }

    /**
     * Adds tags in the specified tag set to this {@code UniqueTagList}.
     * For every {@code Tag}, the frequency of the {@code Tag} will be incremented if the {@code Tag} already exists.
     * Else, the {@code Tag} will be added to this {@code tagFrequencyMap} with a value of 1.
     *
     * @param tagSet the tag set containing the tags to be added.
     */
    public void addTags(Set<Tag> tagSet) {
        requireNonNull(tagSet);

        for (Tag tag : tagSet) {
            requireNonNull(tag);
            if (tagFrequencyMap.containsKey(tag)) {
                int updatedCount = tagFrequencyMap.get(tag) + 1;
                tagFrequencyMap.put(tag, updatedCount);
            } else {
                tagFrequencyMap.put(tag, 1);
            }
        }
    }

    /**
     * Removes tags in the specified tag set from this {@code UniqueTagList}.
     * For every {@code Tag}, the frequency of the {@code Tag} will be decremented if the {@code Tag} frequency is
     * more than 1. Else, the {@code Tag} will be removed from this {@code tagFrequencyMap}.
     *
     * @param tagSet the tag set containing the tags to be removed.
     */
    public void removeTags(Set<Tag> tagSet) {
        requireNonNull(tagSet);

        for (Tag tag : tagSet) {
            requireNonNull(tag);

            if (!tagFrequencyMap.containsKey(tag)) {
                String warningMessage = String.format("Attempting to remove an un-added Tag: %1$s", tag);
                logger.warning(warningMessage);
                assert false : warningMessage;
                continue;
            }

            if (tagFrequencyMap.get(tag) <= 1) {
                assert tagFrequencyMap.get(tag) == 1 : "Frequency should not be less than 1.";
                tagFrequencyMap.remove(tag);
            } else {
                int updatedCount = tagFrequencyMap.get(tag) - 1;
                tagFrequencyMap.put(tag, updatedCount);
            }
        }
    }

    /**
     * Removes tags in the specified tag set {code tagsToRemove} from this {@code UniqueTagList} and then
     * adds tags in the specified tag set {code tagsToAdd} from this {@code UniqueTagList}.
     *
     * @param tagsToRemove the tag set containing the tags to be removed.
     * @param tagsToAdd the tag set containing the tags to be added.
     */
    public void removeAndAddTags(Set<Tag> tagsToRemove, Set<Tag> tagsToAdd) {
        removeTags(tagsToRemove);
        addTags(tagsToAdd);
    }

    /**
     * Clears all tags in this {@code UniqueTagList}.
     */
    public void clearTags() {
        tagFrequencyMap.clear();
    }

    @Override
    public List<Tag> getUniqueTagList() {
        List<Tag> tagList = new ArrayList<>(tagFrequencyMap.keySet());
        Collections.sort(tagList);
        return Collections.unmodifiableList(tagList);
    }

    @Override
    public int getUniqueTagListSize() {
        return tagFrequencyMap.size();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof UniqueTagList)) {
            return false;
        }

        return tagFrequencyMap.equals(((UniqueTagList) other).tagFrequencyMap);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        List<Tag> tagList = getUniqueTagList();

        for (Tag tag : tagList) {
            output.append(tag);
            output.append(" ");
        }

        String trimmedOutputString = output.toString().trim();

        return trimmedOutputString;
    }
}
