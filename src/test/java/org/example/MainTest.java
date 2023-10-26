package org.example;
import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testBookSeats() {
        Main cinema = new Main(5, 10, 20);

        cinema.bookSeats(2, 5, new int[]{3, 4, 5, 6, 7, 8, 9, 10});
        assertEquals(1, cinema.a[2][5][3]);
        assertEquals(1, cinema.a[2][5][10]);

        cinema.bookSeats(2, 5, new int[]{4, 5, 3, 10});
        assertEquals(1, cinema.a[2][5][3]);
    }

    @Test
    public void testCancelBooking() {
        Main cinema = new Main(5, 10, 20);

        cinema.bookSeats(2, 5, new int[]{3, 4, 5, 6, 7, 8, 9, 10});

        cinema.cancelBooking(2, 5, new int[]{4, 5, 3, 10});
        assertEquals(0, cinema.a[2][5][4]);
        assertEquals(0, cinema.a[2][5][5]);
    }

    @Test
    public void testCheckAvailability() {
        Main cinema = new Main(5, 10, 20);

        cinema.bookSeats(2, 5, new int[]{3, 4, 5, 6, 7, 8, 9, 10});
        assertTrue(cinema.checkAvailability(2, 5, 4));
        assertFalse(cinema.checkAvailability(2, 5, 12));
    }

    @Test
    public void testAutoBook() {
        Main cinema = new Main(5, 10, 20);

        cinema.autoBook(2, 6);
        assertEquals(0, cinema.a[2][5][0]);
        assertEquals(0, cinema.a[2][5][1]);
        assertEquals(0, cinema.a[2][5][2]);
        assertEquals(0, cinema.a[2][5][3]);
        assertEquals(0, cinema.a[2][5][4]);
        assertEquals(0, cinema.a[2][5][5]);

        cinema.autoBook(2, 15);
        assertEquals(1, cinema.a[2][1][0]);
    }

    @Test
    public void testFindBestAvailable() {
        Main cinema = new Main(5, 10, 20);

        cinema.bookSeats(2, 5, new int[]{3, 4, 5, 6, 7, 8, 9, 10});
        int bestRow = cinema.findBestAvailable(2, 4);
        assertEquals(0, bestRow);
        bestRow = cinema.findBestAvailable(2, 9);
        assertEquals(0, bestRow);
    }
}