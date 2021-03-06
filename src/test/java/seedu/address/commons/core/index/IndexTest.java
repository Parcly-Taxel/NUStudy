package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class IndexTest {
    @Test
    void createOneBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));

        // check equality using the same base
        assertEquals(1, Index.fromOneBased(1).getOneBased());
        assertEquals(5, Index.fromOneBased(5).getOneBased());

        // convert from one-based index to zero-based index
        assertEquals(0, Index.fromOneBased(1).getZeroBased());
        assertEquals(4, Index.fromOneBased(5).getZeroBased());
    }

    @Test
    void createZeroBasedIndex() {
        // invalid index
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));

        // check equality using the same base
        assertEquals(0, Index.fromZeroBased(0).getZeroBased());
        assertEquals(5, Index.fromZeroBased(5).getZeroBased());

        // convert from zero-based index to one-based index
        assertEquals(1, Index.fromZeroBased(0).getOneBased());
        assertEquals(6, Index.fromZeroBased(5).getOneBased());
    }

    @Test
    void equals() {
        final Index fifthNoteIndex = Index.fromOneBased(5);

        // same values -> returns true
        assertEquals(fifthNoteIndex, Index.fromOneBased(5));
        assertEquals(fifthNoteIndex, Index.fromZeroBased(4));

        // same object -> returns true
        assertEquals(fifthNoteIndex, fifthNoteIndex);

        // null -> returns false
        assertNotEquals(null, fifthNoteIndex);

        // different types -> returns false
        assertNotEquals(5.0, fifthNoteIndex);

        // different index -> returns false
        assertNotEquals(fifthNoteIndex, Index.fromOneBased(1));
    }
}
