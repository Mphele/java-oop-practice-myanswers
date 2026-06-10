package za.co.wethinkcode;

import org.junit.jupiter.api.Test;
import za.co.wethinkcode.model.Book;
import za.co.wethinkcode.model.ItemStatus;
import za.co.wethinkcode.model.ReferenceJournal;

import static org.junit.jupiter.api.Assertions.*;

public class TestLibraryAssets {

    @Test
    public void libraryItem_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new Book(null, "Java OOP"));
        assertThrows(IllegalArgumentException.class, () -> new Book("", "Java OOP"));
        assertThrows(IllegalArgumentException.class, () -> new Book("ID-1", null));
        assertThrows(IllegalArgumentException.class, () -> new Book("ID-1", ""));
    }

    @Test
    public void referenceJournal_constructorThrowsOnInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> new ReferenceJournal("RJ-1", "Science Weekly", 0));
    }

    @Test
    public void book_initializesToAvailable() {
        Book book = new Book("BK-100", "Clean Code");
        assertEquals(ItemStatus.AVAILABLE, book.getStatus());
    }

    @Test
    public void referenceJournal_initializesToAvailable() {
        ReferenceJournal journal = new ReferenceJournal("RJ-200", "Nature", 3);
        assertEquals(ItemStatus.AVAILABLE, journal.getStatus());
        assertEquals(3, journal.getFloorNumber());
    }

    @Test
    public void book_checkoutAndReturnTransitionsCorrectly() {
        Book book = new Book("BK-100", "Clean Code");

        book.checkout();
        assertEquals(ItemStatus.CHECKED_OUT, book.getStatus());

        book.returnItem();
        assertEquals(ItemStatus.AVAILABLE, book.getStatus());
    }

    @Test
    public void book_checkoutThrowsWhenAlreadyCheckedOut() {
        Book book = new Book("BK-100", "Clean Code");
        book.checkout();

        assertThrows(IllegalStateException.class, book::checkout);
    }

    @Test
    public void book_returnItemThrowsWhenAlreadyAvailable() {
        Book book = new Book("BK-100", "Clean Code");

        assertThrows(IllegalStateException.class, book::returnItem);
    }
}