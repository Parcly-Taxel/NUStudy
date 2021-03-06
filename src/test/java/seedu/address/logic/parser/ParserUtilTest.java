package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.note.Content;
import seedu.address.model.note.Title;

class ParserUtilTest {
    private static final String INVALID_TITLE = "a\nb";
    private static final String INVALID_CONTENT = " ";

    private static final String VALID_TITLE = "Rachel Walker";
    private static final String VALID_CONTENT = "123 Main Street #0505";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST, ParserUtil.parseIndex("  1  "));
    }

    @Test
    void parseTitle_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTitle(null));
    }

    @Test
    void parseTitle_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTitle(INVALID_TITLE));
    }

    @Test
    void parseTitle_validValueWithoutWhitespace_returnsName() throws Exception {
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(VALID_TITLE));
    }

    @Test
    void parseTitle_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_TITLE + WHITESPACE;
        Title expectedTitle = new Title(VALID_TITLE);
        assertEquals(expectedTitle, ParserUtil.parseTitle(nameWithWhitespace));
    }

    @Test
    void parseContent_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseContent(null));
    }

    @Test
    void parseContent_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseContent(INVALID_CONTENT));
    }

    @Test
    void parseContent_validValueWithoutWhitespace_returnsAddress() throws Exception {
        Content expectedContent = new Content(VALID_CONTENT);
        assertEquals(expectedContent, ParserUtil.parseContent(VALID_CONTENT));
    }

    @Test
    void parseContent_validValueWithWhitespace_returnsTrimmedAddress() throws Exception {
        String addressWithWhitespace = WHITESPACE + VALID_CONTENT + WHITESPACE;
        Content expectedContent = new Content(VALID_CONTENT);
        assertEquals(expectedContent, ParserUtil.parseContent(addressWithWhitespace));
    }
}
