package dev.emergent;

import org.junit.Test;

import static org.junit.Assert.*;

public class ISBN10Test {

    @Test
    public void ISBN_shorterThan10Characters_ReturnsInvalidBookInfo() {

        // Arrange
        String shortISBN = "12345";

        // Act
        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(shortISBN);

        // Assert
        assertEquals("ISBN must be 10 characters in length", actual.title);
    }

    @Test
    public void ISBN_longerThan10Characters_ReturnsInvalidBookInfo() {

        // Arrange
        String longISBN = "1234567890BCDEF";

        // Act
        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(longISBN);

        // Assert
        assertEquals("ISBN must be 10 characters in length", actual.title);
    }

    @Test
    public void ISBN_BookNotFound() {

        String unknownISBN = "0553562614";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(unknownISBN);

        assertEquals("Title not found", actual.title);
    }

    @Test
    public void ISBN_BookFound() {
        String ISBN = "0321146530";

        ISBNFinder sut = new ISBNFinder();
        BookInfo actual = sut.lookup(ISBN);

        BookInfo expected = new BookInfo("Test Driven Development by Example", "Kent Beck", "0321146530", "9780321146533");

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void ISBN_CleanISBN_ClearsDashesandSpaces() {
        String ISBN = "0 471-60695 2";

        ISBNFinder sut = new ISBNFinder();
        assertEquals("0471606952", sut.CleanISBN(ISBN));
    }

    @Test
    public void ISBN_ValidateWrongInputISBN10() {
        String ISBN = "0 471 60695 2??";

        ISBNFinder sut = new ISBNFinder();
        assertFalse(sut.isValid(ISBN));
    }

    @Test
    public void ISBN_ValidateInputISBN10() {
        String ISBN = "0 471-60695 2";

        ISBNFinder sut = new ISBNFinder();
        assertTrue(sut.isValid(ISBN));
    }

    @Test
    public void ISBN_ValidateWrongInputISBN13() {
        String ISBN = "978 0 ?/471 48648 0";

        ISBNFinder sut = new ISBNFinder();
        assertFalse(sut.isValid(ISBN));
    }

    @Test
    public void ISBN_ValidateInputISBN13() {
        String ISBN = "978 0-471 48648 0";

        ISBNFinder sut = new ISBNFinder();
        assertTrue(sut.isValid(ISBN));
    }
}
