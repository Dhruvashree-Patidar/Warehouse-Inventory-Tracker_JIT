
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Warehouse 
{
	int wId;
	String wName;
	
	private HashMap<Integer, Product> inventory;
	private StockObserver observer;

	public Warehouse(int wId, String wName, StockObserver observer) 
	{
        this.wId = wId;
        this.wName = wName;
        this.observer = observer;
        this.inventory = new HashMap<>();
    }
	
    public int getwId() 
    {
		return wId;
	}

	public String getwName() 
	{
		return wName;
	}
	
	public HashMap<Integer, Product> getInventory() {
		return inventory;
	}


	public void add_Product(Product p) 
    {
    	if (inventory.containsKey(p.getId())) 
        {
            System.out.println("Product ID " + p.getId() + " already exists! Cannot add duplicate.");
        }
    	else
    	{
	        inventory.put(p.getId(), p);
	        System.out.println(p.getName() + " added to warehouse.");
    	}
    }

    public void receive_Shipment(int pId, int quantity) 
    {
        if (inventory.containsKey(pId)) 
        {
            Product p = inventory.get(pId);
            p.increaseStock(quantity);
            System.out.println(quantity + " units added to " + p.getName());
        } 
        else 
        {
            System.out.println("Product not found!");
        }
    }

    public void fulfill_Order(int pId, int quantity) 
    {
        if (inventory.containsKey(pId)) 
        {
            Product p = inventory.get(pId);
            if (p.getQuantity() >= quantity) 
            {
                p.decreaseStock(quantity);
                System.out.println(quantity + " units removed from " + p.getName());

                if (p.getQuantity() < p.getMinQuantity()) 
                {
                    observer.onLowStock(p);
                }
            } 
            else 
            {
                System.out.println("Not enough stock available!");
            }
        } 
        else 
        {
            System.out.println("Product not found!");
        }
    }

    public void display_All_Products() 
    {
        System.out.println("\n--- Warehouse Inventory ---");
        
        if (inventory.isEmpty()) 
        {
            System.out.println("No products in warehouse yet!");
            return;
        }
        
        for (Product p : inventory.values()) 
        {
            System.out.println("ID: " + p.getId() + ", Name: " + p.getName() +
                    ", Quantity: " + p.getQuantity());
        }
    }
    
   
}

