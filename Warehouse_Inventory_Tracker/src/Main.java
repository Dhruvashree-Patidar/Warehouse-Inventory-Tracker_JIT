import java.util.HashMap;
import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        
        StockObserver alertService = new StockAlertService();

        save s=new save();
        HashMap<Integer, Warehouse> warehouses = s.loadDataFromFile(alertService);

        int choice;
        do 
        {
        	System.out.println("1. Add Warehouse");
        	System.out.println("2. Add Product to Warehouse");
        	System.out.println("3. Receive Shipment");
        	System.out.println("4. Fulfill Order");
        	System.out.println("5. Display Warehouse Inventory");
        	System.out.println("6. Exit");
            
            while (!sc.hasNextInt()) 
            { 
                System.out.println("Please enter a valid numeric choice!");
                sc.next(); 
            }

            choice = sc.nextInt();
            switch (choice) 
            {
                case 1:
                	System.out.print("Enter Warehouse ID: ");
                	int wId = sc.nextInt();
                	sc.nextLine();
                	System.out.print("Enter Warehouse Name: ");
                	String wName = sc.nextLine();
                	if (warehouses.containsKey(wId)) 
                	{
                		System.out.println("already exists! Cannot add duplicate");
                	}
                	else
                	{
	                	Warehouse w = new Warehouse(wId, wName, alertService);
	                	warehouses.put(wId, w);
	                	System.out.println("Warehouse added successfully!");
                	}
                	break;
                	
                	
                	
                case 2:
                	System.out.print("Enter Warehouse ID: ");
                	int wid = sc.nextInt();

                	if (warehouses.containsKey(wid)) 
                	{
                	    Warehouse selected = warehouses.get(wid);

                	    System.out.print("Enter Product ID: ");
                	    int pId = sc.nextInt();
                	    sc.nextLine();
                	    System.out.print("Enter Product Name: ");
                	    String pName = sc.nextLine();
                	    System.out.print("Enter Quantity: ");
                	    int qty = sc.nextInt();
                	    System.out.print("Enter Min Quantity: ");
                	    int minQty = sc.nextInt();

                	    Product p = new Product(pId, pName, qty, minQty);
                	    selected.add_Product(p);

                	} 
                	else 
                	{
                	    System.out.println("Warehouse not found!");
                	}

                    break;

                case 3:
                	System.out.print("Enter Warehouse ID: ");
                	int Id = sc.nextInt();

                	if (warehouses.containsKey(Id)) 
                	{
                	    Warehouse selected = warehouses.get(Id);
                	    
                	    System.out.print("Enter Product ID: ");
                        int sId = sc.nextInt();
                        
                        System.out.print("Enter Quantity to Add: ");
                        int addQty = sc.nextInt();
                	    selected.receive_Shipment(sId, addQty);
                	}
                	else 
                	{
                	    System.out.println("Warehouse not found!");
                	}
                	
                    break;

                case 4:
                	System.out.print("Enter Warehouse ID: ");
                	int id = sc.nextInt();

                	if (warehouses.containsKey(id)) 
                	{
                	    Warehouse selected = warehouses.get(id);
                	    
                	    System.out.print("Enter Product ID: ");
                	    int oId = sc.nextInt();
                    
                	    System.out.print("Enter Quantity to Remove: ");
                	    int removeQty = sc.nextInt();
                    
                	    selected.fulfill_Order(oId, removeQty);
                	}
                	else 
                	{
                	    System.out.println("Warehouse not found!");
                	}
                    break;

                case 5:
                	System.out.print("Enter Warehouse ID: ");
                	int ID = sc.nextInt();

                	if (warehouses.containsKey(ID)) 
                	{
                	    Warehouse selected = warehouses.get(ID);
                	    selected.display_All_Products();
                	}
                	else 
                	{
                	    System.out.println("Warehouse not found!");
                	}
                    break;

                case 6:
                	
                	    System.out.println("Saving inventory and exiting...");
                	    s.saveDataToFile(warehouses);
 
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        } 
        while (choice != 6);

        sc.close();
    }
}
