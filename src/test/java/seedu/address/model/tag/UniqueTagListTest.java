package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void getCopy() {
        UniqueTagList copy = uniqueTagList.getCopy();
        assertFalse(copy == uniqueTagList);
        assertTrue(copy.equals(uniqueTagList));
    }

    @Test
    public void addTags_validTagSet_success() {
        Set<Tag> tagSet = Set.of(new Tag("first"), new Tag("second"));
        uniqueTagList.addTags(tagSet);
        assertEquals(2, uniqueTagList.getUniqueTagListSize());

        // case insensitivity test
        uniqueTagList.addTags(Set.of(new Tag("FirST")));
        assertEquals(2, uniqueTagList.getUniqueTagListSize());

        // takes first of the same tags
        assertEquals("first", uniqueTagList.getUniqueTagList().get(0).tagName);
    }

    @Test
    public void addTags_nullTag_throwsNullPointerException() {
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("first"));
        tagSet.add(null);
        assertThrows(NullPointerException.class, () -> uniqueTagList.addTags(tagSet));
    }

    @Test
    public void addTags_nullTagSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.addTags(null));
    }

    @Test
    public void removeTags_removeExistentTag_success() {
        Set<Tag> tagSet = Set.of(new Tag("first"), new Tag("second"));
        uniqueTagList.addTags(tagSet);
        uniqueTagList.addTags(Set.of(new Tag("Second")));
        Set<Tag> tagSetToRemove = Set.of(new Tag("first"));
        assertEquals(2, uniqueTagList.getUniqueTagListSize());
        uniqueTagList.removeTags(tagSetToRemove);
        assertEquals(1, uniqueTagList.getUniqueTagListSize());
        tagSetToRemove = Set.of(new Tag("Second"));
        uniqueTagList.removeTags(tagSetToRemove);
        assertEquals(1, uniqueTagList.getUniqueTagListSize());
        uniqueTagList.removeTags(tagSetToRemove);
        assertEquals(0, uniqueTagList.getUniqueTagListSize());
    }

    @Test
    public void removeTags_removeNonExistentTag_throwsAssertionError() {
        Set<Tag> tagSet = Set.of(new Tag("first"), new Tag("second"));
        assertThrows(AssertionError.class, () -> uniqueTagList.removeTags(tagSet));
    }

    @Test
    public void removeTags_nullTag_throwsNullPointerException() {
        Set<Tag> tagSet = new HashSet<>();
        tagSet.add(new Tag("first"));
        tagSet.add(null);
        assertThrows(NullPointerException.class, () -> uniqueTagList.removeTags(tagSet));
    }

    @Test
    public void removeTags_nullTagSet_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.removeTags(null));
    }

    @Test
    public void clearTags() {
        Set<Tag> tagSet = Set.of(new Tag("first"), new Tag("second"));
        uniqueTagList.addTags(tagSet);
        assertEquals(2, uniqueTagList.getUniqueTagListSize());
        uniqueTagList.clearTags();
        assertEquals(0, uniqueTagList.getUniqueTagListSize());
    }

    @Test
    public void getUniqueTagList() {
        assertEquals(Collections.emptyList(), uniqueTagList.getUniqueTagList());

        // sorted and repeated
        Tag firstTag = new Tag("aaron");
        Tag secondTag = new Tag("betty");
        Tag thirdTag = new Tag("carl");
        uniqueTagList.addTags(Set.of(thirdTag));
        uniqueTagList.addTags(Set.of(secondTag));
        uniqueTagList.addTags(Set.of(firstTag));
        uniqueTagList.addTags(Set.of(firstTag));

        List<Tag> tagList = uniqueTagList.getUniqueTagList();
        assertEquals(firstTag, tagList.get(0));
        assertEquals(secondTag, tagList.get(1));
        assertEquals(thirdTag, tagList.get(2));
    }

    @Test
    public void getUniqueTagListSize() {
        assertEquals(0, uniqueTagList.getUniqueTagListSize());
    }

    @Test
    public void equals() {
        assertTrue(uniqueTagList.equals(uniqueTagList));
        assertFalse(uniqueTagList.equals(6));
        assertFalse(uniqueTagList.equals(null));
    }

    @Test
    public void toString_moreThanFiveUniqueTags_success() {
        Set<Tag> tagSet = Set.of(
                new Tag("test1"),
                new Tag("test2"),
                new Tag("test3"),
                new Tag("test4"),
                new Tag("test5"),
                new Tag("test6")
        );
        uniqueTagList.addTags(tagSet);
        String expectedString = "[test1] [test2] [test3] [test4] [test5] [test6]";
        assertEquals(expectedString, uniqueTagList.toString());

        tagSet = Set.of(
                new Tag("test7"),
                new Tag("test8"),
                new Tag("test9"),
                new Tag("test10")
        );
        uniqueTagList.addTags(tagSet);
        expectedString = "[test1] [test10] [test2] [test3] [test4] [test5] [test6] [test7] [test8] [test9]";
        assertEquals(expectedString, uniqueTagList.toString());

        tagSet = Set.of(new Tag("test11"));
        uniqueTagList.addTags(tagSet);
        expectedString = "[test1] [test10] [test11] [test2] [test3] [test4] [test5] [test6] [test7] [test8] [test9]";
        assertEquals(expectedString, uniqueTagList.toString());
    }
}
