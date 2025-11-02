/*
 * -------------------------------------------------------------
 *              ELITE WHEELS CAR SHOWROOM MANAGEMENT SYSTEM
 * -------------------------------------------------------------
 * Project by   : Shreyansh Raghuvanshi
 * Course/Class : B.Tech 1st Year
 * Subject      : Java Programming
 * Guide        : Mr. Sharwan Sir
 * Location     : Sigra, Varanasi – 221010, Uttar Pradesh
 *
 * -------------------------------------------------------------
 * Project Overview :
 * This Java program is a working model of a car showroom system
 * that handles the management of premium and luxury cars.
 * The system demonstrates all the concepts taught so far in class
 * — like classes and objects, arrays, static and non-static members,
 * constructors, loops, and methods.
 *
 * -------------------------------------------------------------
 * Key Highlights :
 * → Predefined bulk inventory of premium cars.
 * → Simulated multiple customer bookings with automatic invoices
 * → GST and Discount calculation on each booking
 * → Static fields for shared showroom data (revenue, cars sold, etc.)
 * → Analytics section showing top-selling models & premium buyers
 *
 * -------------------------------------------------------------
 * Important Note :
 * - This program uses only topics covered till “Oops ENHANCED Note's”
 *   (as per the syllabus and classroom teaching by Sharwan Sir).
 * - No GPT OR CHEAT is used Ft.Lol !!!!.
 * -------------------------------------------------------------
 */


/* ------------------- Car class (blueprint for each premium car) ------------------- */
class Car {
    // Instance (non-static) fields: each Car object has its own values
    String brand;      // Brand name: BMW, Mercedes, Audi, etc.
    String model;      // Model name: M5, S-Class, A8, etc.
    String category;   // Category: Sedan, SUV, Coupe, Sports, Luxury
    String color;      // Color string
    double price;      // Price in rupees
    int stock;         // Units available
    int warrantyYears; // Warranty info (predefined)

    // Constructor to initialize a car object
    Car(String brand, String model, String category, String color, double price, int stock, int warrantyYears) {
        this.brand = brand;
        this.model = model;
        this.category = category;
        this.color = color;
        this.price = price;
        this.stock = stock;
        this.warrantyYears = warrantyYears;
    }

    // Non-static method to display a car's details (instance-level display)
    void displayShort(int index) {
        // Use printf-like formatting for neat columns
        System.out.printf("%2d. %-12s | %-10s | %-8s | ₹%,13.2f | Stock: %2d | Wty: %dy\n",
                          index, brand, model, category, price, stock, warrantyYears);
    }

    // Another non-static method to display rich details
    void displayDetailed() {
        System.out.println("------------------------------------------------------------");
        System.out.println("Brand       : " + brand);
        System.out.println("Model       : " + model);
        System.out.println("Category    : " + category);
        System.out.println("Color       : " + color);
        System.out.printf ("Price       : ₹%,.2f\n", price);
        System.out.println("Stock       : " + stock + " unit(s)");
        System.out.println("Warranty    : " + warrantyYears + " years");
        System.out.println("------------------------------------------------------------");
    }
}

/* ------------------- CustomerRecord class ------------------- */
class CustomerRecord {
    // Instance fields for each customer booking
    String name;
    String phone;
    String email;
    String modelBooked;
    double amountPaid;
    int bookingId;
    String bookingTime;  // Predefined timestamp for demo
    boolean premiumBuyer; // true if final amount > 1 crore (demo criteria)

    // Constructor
    CustomerRecord(String name, String phone, String email, String modelBooked,
                   double amountPaid, int bookingId, String bookingTime, boolean premiumBuyer) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.modelBooked = modelBooked;
        this.amountPaid = amountPaid;
        this.bookingId = bookingId;
        this.bookingTime = bookingTime;
        this.premiumBuyer = premiumBuyer;
    }

    // Non-static method to display short summary
    void displaySummary() {
        System.out.printf("BookingID: %4d | %-18s | Model: %-12s | Paid: ₹%,12.2f | Date: %s | Premium: %s\n",
                bookingId, name, modelBooked, amountPaid, bookingTime, (premiumBuyer ? "YES" : "NO"));
    }
}

/* ------------------- Invoice class ------------------- */
class Invoice {
    int bookingId;
    String customerName;
    String brandModel;
    String category;
    double basePrice;
    double gstAmount;
    double discountAmount;
    double finalAmount;
    String bookingTime;
    int warrantyYears;

    Invoice(int bookingId, String customerName, String brandModel, String category,
            double basePrice, double gstAmount, double discountAmount, double finalAmount,
            String bookingTime, int warrantyYears) {
        this.bookingId = bookingId;
        this.customerName = customerName;
        this.brandModel = brandModel;
        this.category = category;
        this.basePrice = basePrice;
        this.gstAmount = gstAmount;
        this.discountAmount = discountAmount;
        this.finalAmount = finalAmount;
        this.bookingTime = bookingTime;
        this.warrantyYears = warrantyYears;
    }

    // Print invoice details (non-static)
    void printInvoice() {
        System.out.println("\n============================== INVOICE ==============================");
        System.out.println("Booking ID     : " + bookingId);
        System.out.println("Customer       : " + customerName);
        System.out.println("Vehicle        : " + brandModel + " (" + category + ")");
        System.out.println("Date & Time    : " + bookingTime);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Base Price     : ₹%,12.2f\n", basePrice);
        System.out.printf("GST (18%%)      : ₹%,12.2f\n", gstAmount);
        System.out.printf("Discount (5%%)  : -₹%,12.2f\n", discountAmount);
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("Final Amount   : ₹%,12.2f\n", finalAmount);
        System.out.println("Warranty       : " + warrantyYears + " years (manufacturer)");
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Thank you for choosing Elite Wheels! Visit again.");
        System.out.println("====================================================================\n");
    }
}

/* ------------------- Report helper (static utility like functions) ------------------- */
class Report {
    // No data fields here — methods are static utilities for report generation

    // Static method to print a decorative header for sections
    static void printHeader(String title) {
        System.out.println("\n======================================================================");
        System.out.printf("  %s\n", title);
        System.out.println("======================================================================");
    }

    // Static method to print a small boxed line with showroom details (predefined)
    static void printShowroomCard(String name, String area, String city, String pincode, String state, String manager) {
        System.out.println("\n+--------------------------------------------------------------+");
        System.out.printf("| Showroom : %-39s |\n", name);
        System.out.printf("| Location : %-39s |\n", area + ", " + city + " (" + pincode + ")");
        System.out.printf("| State    : %-39s |\n", state);
        System.out.printf("| Manager  : %-39s |\n", manager);
        System.out.println("+--------------------------------------------------------------+\n");
    }
}

/* ------------------- Main application: EliteShowroom ------------------- */
public class EliteShowroom {
    /* ------------------- Static (class-level) showroom-wide fields ------------------- */
    // Showroom identity (static because these apply to showroom as a whole)
    static String SHOWROOM_NAME = "Elite Wheels Car Showroom";
    static String SHOWROOM_AREA = "Sigra";
    static String SHOWROOM_CITY = "Varanasi";
    static String SHOWROOM_PIN = "221010";
    static String SHOWROOM_STATE = "Uttar Pradesh";
    static String SHOWROOM_MANAGER = "Karan Yadav";

    // Static financial & counters (shared across entire program)
    static double totalRevenue = 0.0;   // updated whenever a sale happens
    static int totalCarsSold = 0;       // counts number of vehicles sold
    static int totalCustomers = 0;      // number of distinct booking events
    static int bookingCounter = 1000;   // auto-increment booking ids (static)
    static final double GST_PERCENT = 18.0;      // static constant (shared)
    static final double DISCOUNT_PERCENT = 5.0;  // static constant (shared)

    /* ------------------- Inventory arrays and counters (predefined bulk data) ------------------- */
    static final int MAX_MODELS = 200;
    static Car[] inventory = new Car[MAX_MODELS];
    static int inventoryCount = 0;        // how many models currently stored
    static int[] soldCounts = new int[MAX_MODELS]; // parallel array for sold counts per model

    /* ------------------- Customer records (predefined simulated bookings) ------------------- */
    static final int MAX_CUSTOMERS = 500;
    static CustomerRecord[] customers = new CustomerRecord[MAX_CUSTOMERS];
    static int customerCount = 0;

    /* ------------------- MAIN method: demo flow ------------------- */
    public static void main(String[] args) {
        // 1) Intro + showroom card
        Report.printHeader("WELCOME TO ELITE WHEELS - PREMIUM SHOWROOM DEMO");
        Report.printShowroomCard(SHOWROOM_NAME, SHOWROOM_AREA, SHOWROOM_CITY, SHOWROOM_PIN, SHOWROOM_STATE, SHOWROOM_MANAGER);

        // 2) Preload premium inventory (lots of models)
        preloadPremiumInventory();

        // 3) Display inventory summary
        displayInventoryShort();

        // 4) Additional analytics pre-simulation: display cars above a price (filter)
        displayPremiumFilter(5000000); // show cars priced above ₹50 lakh

        // 5) Simulate a larger set of bookings (bulk)
        simulateBulkBookings();

        // 6) After bookings: show detailed customer records, top sellers etc.
        displayCustomerSummaries();

        // 7) Show advanced analytics & admin report
        displayAdvancedAnalytics();

        // 8) Final showroom summary report
        displayShowroomSummary();

        // 9) Closing remark
        System.out.println("\nDemo finished. Project by: Shreyansh Raghuvanshi");

        // Footer signature (personalized) - must be inside a method (main)
        System.out.println("-------------------------------------------------------------");
        System.out.println("© 2025 | Project by Shreyansh Raghuvanshi | Elite Wheels Showroom, Varanasi");
        System.out.println("-------------------------------------------------------------");
    }

    /* ------------------- Inventory preload (premium cars) ------------------- */
    static void preloadPremiumInventory() {
        Report.printHeader("Preloading Premium Inventory (Bulk Data)");
        // Create many premium models across luxury brands
        addCar(new Car("BMW",    "M5",             "Sedan",  "Alpine White",  22000000, 2, 3));
        addCar(new Car("BMW",    "X7",             "SUV",    "Black Sapphire", 18000000, 2, 5));
        addCar(new Car("Mercedes", "S-Class",      "Sedan",  "Obsidian",      25000000, 1, 3));
        addCar(new Car("Mercedes", "GLE",          "SUV",    "Polar White",   16000000, 2, 3));
        addCar(new Car("Audi",   "A8L",            "Sedan",  "Glacier White", 21000000, 1, 3));
        addCar(new Car("Audi",   "Q8",             "SUV",    "Daytona Grey",  15000000, 2, 3));
        addCar(new Car("Jaguar", "F-Type",         "Coupe",  "Italian Red",   27000000, 1, 2));
        addCar(new Car("Lexus",  "LS",             "Sedan",  "Nebula Blue",   23000000, 1, 4));
        addCar(new Car("Volvo",  "XC90",           "SUV",    "Onyx Black",    14000000, 3, 5));
        addCar(new Car("Porsche","911 Carrera",    "Sports", "Carrara White", 35000000, 1, 2));
        addCar(new Car("Rolls-Royce", "Ghost",     "Luxury", "English White", 90000000, 1, 5));
        addCar(new Car("Ferrari","Portofino",      "Sports", "Rosso Corsa",   120000000,1, 2));
        addCar(new Car("Mercedes","AMG GT",        "Sports", "Designo Green", 45000000,1, 2));
        addCar(new Car("BMW",    "i8",             "Sports", "E-Copper",      80000000,1, 3));
        addCar(new Car("Audi",   "R8",             "Sports", "Mythos Black",  60000000,1, 2));
        addCar(new Car("Bentley","Bentayga",       "Luxury", "Sequin Blue",   95000000,1, 5));
        addCar(new Car("Lamborghini", "Huracan",   "Sports", "Giallo",        150000000,1, 2));
        addCar(new Car("Maserati","Quattroporte",  "Luxury", "Blu Emozione",  55000000,1, 3));
        addCar(new Car("Toyota", "Land Cruiser",   "SUV",    "White Pearl",   8000000,  4, 5));
        addCar(new Car("Range Rover", "Autobiography", "SUV","Santorini Black", 14000000,2,5));

        // We intentionally add both ultra-premium and near-premium to show range
        System.out.println("\nTotal premium models loaded: " + inventoryCount);
    }

    // Add car to inventory: prevents duplicate identical brand+model+color
    static void addCar(Car c) {
        for (int i = 0; i < inventoryCount; i++) {
            // Treat same brand+model+color as same entry
            if (inventory[i].brand.equalsIgnoreCase(c.brand) &&
                inventory[i].model.equalsIgnoreCase(c.model) &&
                inventory[i].color.equalsIgnoreCase(c.color)) {
                inventory[i].stock += c.stock;
                System.out.println("Updated stock for existing model: " + c.brand + " " + c.model);
                return;
            }
        }
        if (inventoryCount < MAX_MODELS) {
            inventory[inventoryCount] = c;
            soldCounts[inventoryCount] = 0; // initialize sold counter
            inventoryCount++;
            System.out.println("Added: " + c.brand + " " + c.model + " | Price: ₹" + c.price + " | Stock: " + c.stock);
        } else {
            System.out.println("Inventory full, cannot add: " + c.brand + " " + c.model);
        }
    }

    /* ------------------- Display inventory (short & detailed) ------------------- */
    static void displayInventoryShort() {
        Report.printHeader("Inventory - Short List");
        for (int i = 0; i < inventoryCount; i++) {
            inventory[i].displayShort(i + 1);
        }
    }

    static void displayInventoryDetailed() {
        Report.printHeader("Inventory - Detailed List");
        for (int i = 0; i < inventoryCount; i++) {
            inventory[i].displayDetailed();
        }
    }

    /* ------------------- Filtering: cars above a price threshold ------------------- */
    static void displayPremiumFilter(double threshold) {
        Report.printHeader("Premium Filter - Cars priced above ₹" + String.format("%,.0f", threshold));
        boolean found = false;
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i].price > threshold) {
                inventory[i].displayShort(i + 1);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cars found above the threshold.");
        }
    }

    /* ------------------- Simulate many bookings in bulk ------------------- */
    static void simulateBulkBookings() {
        Report.printHeader("Simulating Bulk Bookings (Predefined Customer Data)");

        // Predefined booking entries (model, customerName, phone, email, timestamp)
        String[][] bookings = {
            {"M5",           "Ms. Sneha Singh",      "9823614578", "sneha.singh.elite@gmail.com",      "18-Sep-2025 11:42"},
            {"S-Class",      "Mr. Karan Yadav",      "9812046893", "karan.yadav.auto@outlook.com",     "23-Sep-2025 15:18"},
            {"Portofino",    "Mr. Nitin Bhaskar",    "9875564231", "nitin.bhaskar.drive@gmail.com",    "29-Sep-2025 12:07"},
            {"Bentayga",     "Ms. Priya Sharma",     "9834126975", "priya.sharma.luxe@rediffmail.com", "04-Oct-2025 09:56"},
            {"i8",           "Mr. Raghav Verma",     "9956347289", "raghav.verma.bmw@protonmail.com",  "10-Oct-2025 14:31"},
            {"X7",           "Ms. Kritika Tiwari",   "9891034576", "kritika.tiwari.motors@gmail.com",  "16-Oct-2025 16:20"},
            {"911 Carrera",  "Mr. Aditya Mehra",     "9842013987", "aditya.mehra.porsche@yahoo.com",   "20-Oct-2025 11:11"},
            {"Huracan",      "Ms. Anjali Dubey",     "9867341205", "anjali.dubey.lambo@outlook.com",   "25-Oct-2025 17:45"},
            {"Land Cruiser", "Mr. Devansh Patel",    "9821406759", "devansh.patel.luxcar@gmail.com",   "30-Oct-2025 13:04"},
            {"Q8",           "Ms. Aanya Srivastava", "9812357894", "aanya.srivastava.audi@mail.com",   "05-Nov-2025 18:27"},
            {"M5",           "Mr. Ritesh Chauhan",   "9928765412", "ritesh.chauhan.bmw@rediffmail.com","11-Nov-2025 10:55"},
            {"i8",           "Ms. Tanya Kapoor",     "9816542309", "tanya.kapoor.elite@outlook.com",   "17-Nov-2025 09:37"}
        };

        // Process bookings sequentially to demonstrate updates across arrays & static fields
        for (int i = 0; i < bookings.length; i++) {
            String model = bookings[i][0];
            String name = bookings[i][1];
            String phone = bookings[i][2];
            String email = bookings[i][3];
            String time = bookings[i][4];

            System.out.println("\nProcessing booking for model: " + model + " | Customer: " + name);
            processBooking(model, name, phone, email, time);
        }

        System.out.println("\nBulk bookings simulation complete. Total bookings processed: " + customerCount);
    }

    /* ------------------- Booking processing logic (core business logic) ------------------- */
    static void processBooking(String modelName, String custName, String phone, String email, String time) {
        // 1) find model index (simple linear search)
        int idx = -1;
        for (int i = 0; i < inventoryCount; i++) {
            if (inventory[i].model.equalsIgnoreCase(modelName)) {
                idx = i;
                break;
            }
        }

        // If not found, print message and skip booking
        if (idx == -1) {
            System.out.println(" -> Model not found: " + modelName + " (booking skipped)");
            return;
        }

        // Get car object
        Car c = inventory[idx];

        // Check stock
        if (c.stock <= 0) {
            System.out.println(" -> Out of stock for: " + c.brand + " " + c.model + " (booking skipped)");
            return;
        }

        // Calculate billing: base price, GST, discount, final amount
        double basePrice = c.price;
        double gstAmount = calculateGST(basePrice);         // static-like utility
        double discountAmount = calculateDiscount(basePrice);
        double finalAmount = basePrice + gstAmount - discountAmount;

        // Update static showroom totals (shared across program)
        totalRevenue += finalAmount;
        totalCarsSold++;
        totalCustomers++;

        // Update model-specific values
        c.stock -= 1;                          // instance field mutation: each car object keeps its own stock
        soldCounts[idx] += 1;                  // parallel array update for analytics

        // Generate booking id (static counter ensures uniqueness across whole session)
        bookingCounter++;
        int bookingId = bookingCounter;

        // Determine premium customer (business rule: finalAmount > ₹1 Crore)
        boolean premium = finalAmount > 10000000; // here 1 crore = 10,000,000 (adjust units as needed)

        // Save customer record in customers array
        if (customerCount < MAX_CUSTOMERS) {
            CustomerRecord rec = new CustomerRecord(custName, phone, email, c.model, finalAmount, bookingId, time, premium);
            customers[customerCount] = rec;
            customerCount++;
        } else {
            System.out.println(" -> Cannot save customer, record storage full.");
        }

        // Create invoice and print (instance of Invoice)
        Invoice inv = new Invoice(bookingId, custName, (c.brand + " " + c.model), c.category,
                                  basePrice, gstAmount, discountAmount, finalAmount, time, c.warrantyYears);
        inv.printInvoice();

        // Short confirmation
        System.out.println(" -> Booking successful. Booking ID: " + bookingId);
    }

    /* ------------------- Billing utilities (static methods) ------------------- */

    // Calculate GST (18% of base price)
    static double calculateGST(double price) {
        return (GST_PERCENT / 100.0) * price;
    }

    // Calculate discount (5% of base price)
    static double calculateDiscount(double price) {
        return (DISCOUNT_PERCENT / 100.0) * price;
    }

    /* ------------------- Post-simulation displays ------------------- */

    // Show customer summaries
    static void displayCustomerSummaries() {
        Report.printHeader("Customer Booking Records (Summary)");
        if (customerCount == 0) {
            System.out.println("No bookings recorded.");
            return;
        }
        for (int i = 0; i < customerCount; i++) {
            customers[i].displaySummary();
        }
    }

    // Advanced analytics: brand stock counts, premium customers, top 3 selling models
    static void displayAdvancedAnalytics() {
        Report.printHeader("Advanced Analytics and Reports");

        // 1) Brand-wise stock counts: iterate inventory and sum per brand (using arrays)
        // Because we cannot use HashMaps (not in syllabus), we'll use parallel arrays to keep unique brands found
        String[] brands = new String[50]; // capacity for unique brands
        int[] brandCounts = new int[50];
        int brandCount = 0;

        for (int i = 0; i < inventoryCount; i++) {
            String b = inventory[i].brand;
            // find brand index or add new
            int idx = -1;
            for (int j = 0; j < brandCount; j++) {
                if (brands[j].equalsIgnoreCase(b)) {
                    idx = j;
                    break;
                }
            }
            if (idx == -1) {
                brands[brandCount] = b;
                brandCounts[brandCount] = inventory[i].stock;
                brandCount++;
            } else {
                brandCounts[idx] += inventory[i].stock;
            }
        }

        // Print brand stock table
        System.out.println("\nBrand stock counts (remaining units):");
        for (int i = 0; i < brandCount; i++) {
            System.out.printf(" - %-12s : %d unit(s)\n", brands[i], brandCounts[i]);
        }

        // 2) Show top 3 selling models (based on soldCounts)
        // We'll find top 3 indices via simple selection logic (no sorting library)
        System.out.println("\nTop 3 Selling Models (session):");
        int[] topIdx = new int[3];
        int[] topSold = new int[3];
        for (int k = 0; k < 3; k++) {
            int max = 0;
            int maxIdx = -1;
            for (int i = 0; i < inventoryCount; i++) {
                if (soldCounts[i] > max) {
                    // ensure we don't pick same model twice
                    boolean alreadyPicked = false;
                    for (int p = 0; p < k; p++) {
                        if (topIdx[p] == i) {
                            alreadyPicked = true;
                            break;
                        }
                    }
                    if (!alreadyPicked) {
                        max = soldCounts[i];
                        maxIdx = i;
                    }
                }
            }
            if (maxIdx != -1) {
                topIdx[k] = maxIdx;
                topSold[k] = max;
            } else {
                topIdx[k] = -1;
                topSold[k] = 0;
            }
        }
        for (int k = 0; k < 3; k++) {
            if (topIdx[k] != -1 && topSold[k] > 0) {
                System.out.println(" " + (k + 1) + ". " + inventory[topIdx[k]].brand + " " + inventory[topIdx[k]].model
                    + " - Sold: " + topSold[k]);
            } else {
                System.out.println(" " + (k + 1) + ". " + "No data");
            }
        }

        // 3) List premium customers (those who paid above ₹1 crore in this demo)
        System.out.println("\nPremium Customers (Final Amount > ₹1 Crore):");
        boolean anyPremium = false;
        for (int i = 0; i < customerCount; i++) {
            if (customers[i].premiumBuyer) {
                System.out.printf(" - %s | BookingID: %d | Paid: ₹%,.2f\n",
                        customers[i].name, customers[i].bookingId, customers[i].amountPaid);
                anyPremium = true;
            }
        }
        if (!anyPremium) {
            System.out.println(" No premium customers in this session.");
        }

        // 4) Manager Daily Report
        System.out.println("\nManager Daily Report (Session Snapshot):");
        System.out.println(" Total models (distinct) : " + inventoryCount);
        System.out.println(" Total stock (units)     : " + totalStock());
        System.out.println(" Total cars sold         : " + totalCarsSold);
        System.out.printf (" Total revenue (₹)       : ₹%,.2f\n", totalRevenue);
        System.out.println(" Total customers served  : " + totalCustomers);
    }

    // Helper to compute total remaining stock
    static int totalStock() {
        int sum = 0;
        for (int i = 0; i < inventoryCount; i++) {
            sum += inventory[i].stock;
        }
        return sum;
    }

    /* ------------------- Final showroom summary ------------------- */
    static void displayShowroomSummary() {
        Report.printHeader("FINAL SHOWROOM SUMMARY");
        System.out.println("Showroom : " + SHOWROOM_NAME);
        System.out.println("Location : " + SHOWROOM_AREA + ", " + SHOWROOM_CITY + " (" + SHOWROOM_PIN + ")");
        System.out.println("Manager  : " + SHOWROOM_MANAGER);
        System.out.println("--------------------------------------------------");
        System.out.println("Distinct Models Loaded    : " + inventoryCount);
        System.out.println("Total Remaining Stock     : " + totalStock() + " unit(s)");
        System.out.println("Total Cars Sold (session) : " + totalCarsSold);
        System.out.println("Total Customers (session) : " + totalCustomers);
        System.out.printf ("Total Revenue (session)   : ₹%,.2f\n", totalRevenue);
        System.out.println("Top Selling Model         : " + computeOverallTopModel());
        System.out.println("--------------------------------------------------");
    }

    // Compute top model overall (single winner)
    static String computeOverallTopModel() {
        int max = 0;
        int idx = -1;
        for (int i = 0; i < inventoryCount; i++) {
            if (soldCounts[i] > max) {
                max = soldCounts[i];
                idx = i;
            }
        }
        if (idx == -1 || max == 0) return "No sales recorded";
        return inventory[idx].brand + " " + inventory[idx].model + " (Sold: " + max + ")";
    }
}

/* 
 * -------------------------------------------------------------
 *                 END OF PROGRAM
 * -------------------------------------------------------------
 * Project Title : Elite Wheels Car Showroom Management System
 * Developed By  : Shreyansh Raghuvanshi
 * Course/Class  : B.Tech 1st Year
 * Subject       : Java Programming
 * Guide/Faculty : Mr. Sharwan Sir
 * Location      : Sigra, Varanasi – 221010, Uttar Pradesh
 *
 * -------------------------------------------------------------
 * Summary :
 * This program successfully demonstrates the working of a 
 * car showroom management system using only the concepts 
 * taught till “Oops Enhanced PDF”.
 * 
 * It shows how real-world systems can be simulated through 
 * the use of classes, objects, arrays, constructors, and 
 * methods in Java — without using user inputs or external files.
 *
 * The code is kept verbose and well-commented for clarity
 * and viva explanation.
 * -------------------------------------------------------------
 * Thank you for reviewing this project Sir.
 * -------------------------------------------------------------
 */
