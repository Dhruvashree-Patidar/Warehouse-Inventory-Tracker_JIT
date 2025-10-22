import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Set up observer and warehouse
        StockObserver alertService = new StockAlertService();
        Warehouse warehouse = new Warehouse(alertService);

        // Load from file on startup
        warehouse.loadFromFile("inventory.txt");

        int choice;
        do {
            System.out.println("\n--- Warehouse Inventory Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Receive Shipment");
            System.out.println("3. Fulfill Order");
            System.out.println("4. Display All Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            while (!sc.hasNextInt()) { // validation
                System.out.println("Please enter a valid numeric choice!");
                sc.next(); // Skip invalid input
            }

            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Product ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // consume leftover newline
                    
                    System.out.print("Enter Product Name: ");
                    String name = sc.nextLine();
                    
                    System.out.print("Enter Initial Quantity: ");
                    int qty = sc.nextInt();
                    
                    System.out.print("Enter Minimum Quantity (Threshold): ");
                    int minQty = sc.nextInt();
                    
                    Product p = new Product(id, name, qty, minQty);
                    warehouse.add_Product(p);
                    break;

                case 2:
                    System.out.print("Enter Product ID: ");
                    int sId = sc.nextInt();
                    
                    System.out.print("Enter Quantity to Add: ");
                    int addQty = sc.nextInt();
                    
                    warehouse.receive_Shipment(sId, addQty);
                    break;

                case 3:
                    System.out.print("Enter Product ID: ");
                    int oId = sc.nextInt();
                    
                    System.out.print("Enter Quantity to Remove: ");
                    int removeQty = sc.nextInt();
                    
                    warehouse.fulfill_Order(oId, removeQty);
                    break;

                case 4:
                    warehouse.display_All_Products();
                    break;

                case 5:
                    System.out.println("Saving inventory and exiting...");
                    warehouse.saveToFile("inventory.txt");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}
