import java.util.Scanner;

public class HotelTableReservation {
    static Scanner scanner = new Scanner(System.in);
    static String[][] tables = {{"2 persons", "4 persons", "6 persons"}, {"2AC", "2Non-AC", "4AC", "4Non-AC", "6AC", "6Non-AC"}};
    static boolean[][] tableAvailability = {{true, true, true}, {true, true, true, true, true, true}};
    static Booking[] bookings = new Booking[tables[0].length];

    public static void main(String[] args) {
        while (true) {
            System.out.println("Welcome to Hotel Table Reservation System!!!");
            System.out.println("1. Collect customer details and book table");
            System.out.println("2. Cancel booking");
            System.out.println("3. Show available tables");
            System.out.println("4. Show booked tables with customer details");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    collectCustomerDetails();
                    break;
                case 2:
                    cancelBooking();
                    break;
                case 3:
                    showAvailableTables();
                    break;
                case 4:
                    showBookedTables();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    static void collectCustomerDetails() {
        scanner.nextLine(); 
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter number of persons:");
        int numPersons = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter AC or Non-AC (AC/Non-AC):");
        String acType = scanner.nextLine();
        System.out.println("Enter phone number:");
        String phoneNumber = scanner.nextLine();

        bookTable(name, numPersons, acType, phoneNumber);
       System.out.println("Customer details collected successfully.");
       System.out.println();
    
    }

    static void bookTable(String name, int numPersons, String acType, String phoneNumber) {
        System.out.println("Available Tables:");
        for (int i = 0; i < tables[0].length; i++) {
            if (tableAvailability[0][i]) {
                System.out.println((i + 1) + ". " + tables[0][i] + " (" + tables[1][i] + ")");
            }
            System.out.println();
        }

        System.out.println("Enter the  table  number you want to book:");
        int tableNumber = scanner.nextInt();
        if (tableNumber < 1 || tableNumber > tables[0].length || !tableAvailability[0][tableNumber - 1]) {
            System.out.println("Invalid table number or table not available. Please try again.");
            
            return;
        }

        tableAvailability[0][tableNumber - 1] = false;
        bookings[tableNumber - 1] = new Booking(name, numPersons, acType, phoneNumber);
        System.out.println("Table booked successfully.");
        System.out.println();
        showAvailableTables(); 
        }

    static void cancelBooking() {
        System.out.println("Enter the table  number you want to cancel booking for:");
        int tableNumber = scanner.nextInt();
        if (tableNumber < 1 || tableNumber > tables[0].length || tableAvailability[0][tableNumber - 1] || bookings[tableNumber - 1] == null) {
            System.out.println("Invalid table number or no booking exists for this table. Please try again.");
           System.out.println();
            return;
        }

        tableAvailability[0][tableNumber - 1] = true;
        bookings[tableNumber - 1] = null;
        System.out.println("Booking for table " + tables[0][tableNumber - 1] + " is canceled.");
        System.out.println();
    }

    static void showAvailableTables() {
        boolean anyAvailable = false; 

        System.out.println("Available Tables:");
        for (int i = 0; i < tables[0].length; i++) {
            if (tableAvailability[0][i]) {
                System.out.println((i + 1) + ". " + tables[0][i]); 
                System.out.println();
                anyAvailable = true; 
            }
        }

        if (!anyAvailable) {
            System.out.println("No tables available for booking.");
            System.out.println();
        }
    }
    static class Booking {
        String name;
        int numPersons;
        String acType;
        String phoneNumber;

        public Booking(String name, int numPersons, String acType, String phoneNumber) {
            this.name = name;
            this.numPersons = numPersons;
            this.acType = acType;
            this.phoneNumber = phoneNumber;
        }
    }


    static void showBookedTables() {
        System.out.println("Booked Tables with Customer Details:");
        for (int i = 0; i < bookings.length; i++) {
            if (bookings[i] != null) {
                System.out.println("Table: " + tables[0][i]);
                System.out.println("Customer Name: " + bookings[i].name);
                System.out.println("Number of Persons: " + bookings[i].numPersons);
                System.out.println("AC Type: " + bookings[i].acType);
                System.out.println("Phone Number: " + bookings[i].phoneNumber);
                System.out.println();
            }
        }
    }

   }
