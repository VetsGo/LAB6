package org.example;

public class Main {

    public int[][][] a;

    public Main(int numHalls, int numRows, int numSeats) {
        a = new int[numHalls][numRows][numSeats];
        for (int hall = 0; hall < numHalls; hall++) {
            for (int row = 0; row < numRows; row++) {
                for (int seat = 0; seat < numSeats; seat++) {
                    a[hall][row][seat] = 0;
                }
            }
        }
    }

    public void bookSeats(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (a[hallNumber][row][seat] == 1) {
                System.out.println("Місце " + (seat+1) + " в ряду " + (row+1) + " вже заброньоване");
            } else {
                a[hallNumber][row][seat] = 1;
                System.out.println("Місце " + (seat+1) + " в ряду " + (row+1) + " заброньоване");
            }
        }
    }

    public void cancelBooking(int hallNumber, int row, int[] seats) {
        for (int seat : seats) {
            if (a[hallNumber][row][seat] == 1) {
                a[hallNumber][row][seat] = 0;
                System.out.println("Скасовано бронювання для місця " + (seat+1) + " в ряду " + (row+1));
            } else {
                System.out.println("Місце " + (seat+1) + " в ряду " + (row+1) + " не заброньоване");
            }
        }
    }

    public boolean checkAvailability(int hallNumber, int row, int numSeats) {
        int consecutiveSeats = 0;
        for (int seat = 0; seat < a[hallNumber][row].length; seat++) {
            if (a[hallNumber][row][seat] == 0) {
                consecutiveSeats++;
                if (consecutiveSeats == numSeats) {
                    return true;
                }
            } else {
                consecutiveSeats = 0;
            }
        }
        return false;
    }

    public void printSeatingArrangement(int hallNumber) {
        System.out.println("Схема розміщення місць для залу " + (hallNumber + 1) + ": ");

        System.out.print("  ");
        for (int seat = 1; seat <= a[hallNumber][0].length; seat++) {
            if (seat <= 10) {
                System.out.print(" " + seat);
            } else {
                System.out.print(seat);
            }
        }
        System.out.println();

        for (int row = 0; row < a[hallNumber].length; row++) {
            System.out.print(row < 9 ? " " : "");
            System.out.print((row + 1) + " ");

            for (int seat = 0; seat < a[hallNumber][row].length; seat++) {
                if (a[hallNumber][row][seat] == 0) {
                    System.out.print("\u001B[32m0\u001B[0m ");
                } else {
                    System.out.print("\u001B[31m1\u001B[0m ");
                }
            }
            System.out.println(row < 10 ? " " + (row + 1) : (row + 1));
        }

        System.out.print("  ");
        for (int seat = 1; seat <= a[hallNumber][0].length; seat++) {
            if (seat <= 10) {
                System.out.print(" " + seat);
            } else {
                System.out.print(seat);
            }
        }
        System.out.println();
    }

    public int findBestAvailable(int hallNumber, int numSeats) {
        for (int row = 0; row < a[hallNumber].length; row++) {
            if (checkAvailability(hallNumber, row, numSeats)) {
                return row;
            }
        }
        return -1;
    }

    public void autoBook(int hallNumber, int numSeats) {
        int row = findBestAvailable(hallNumber, numSeats);
        if (row != -1) {
            int[] seats = new int[numSeats];
            int availableSeats = numSeats;

            for (int seat = 0; seat < a[hallNumber][row].length; seat++) {
                if (a[hallNumber][row][seat] == 0) {
                    seats[numSeats - availableSeats] = seat;
                    availableSeats--;
                    if (availableSeats == 0) {
                        break;
                    }
                }
            }
            bookSeats(hallNumber, row, seats);
        } else {
            System.out.println("Недостатньо вільних послідовних місць");
        }
    }

    public static void main(String[] args) {
        Main cinema = new Main(5, 10, 20);

        cinema.bookSeats(2, 5, new int[]{3, 4, 5, 6, 7, 8, 9, 10});
        cinema.cancelBooking(2, 5, new int[]{4, 5, 3, 10});
        cinema.printSeatingArrangement(2);

        if (cinema.checkAvailability(2, 5, 10)) {
            System.out.println("Задана кількість послідовних місць доступна");
        } else {
            System.out.println("Недостатньо вільних послідовних місць");
        }

        cinema.autoBook(2, 10);
        cinema.printSeatingArrangement(2);

        cinema.autoBook(2, 13);
        cinema.printSeatingArrangement(2);

        cinema.autoBook(2, 7);
        cinema.printSeatingArrangement(2);

        cinema.autoBook(2, 3);
        cinema.printSeatingArrangement(2);
    }
}