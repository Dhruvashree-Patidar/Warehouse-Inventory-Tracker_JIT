import java.util.HashMap;

public class Warehouse 
{
	private HashMap<Integer, Product> inventory;
	private StockObserver observer;

    public Warehouse(StockObserver observer) 
    {
        inventory = new HashMap<>();
        this.observer=observer;
    }

    public void add_Product(Product p) 
    {
        inventory.put(p.getId(), p);
        System.out.println(p.getName() + " added to warehouse.");
    }

    public void receive_Shipment(int pId, int quantity) 
    {
        if (inventory.containsKey(pId)) 
        {
            Product p = inventory.get(pId);
            p.setQuantity(p.getQuantity() + quantity);
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
                p.setQuantity(p.getQuantity() - quantity);
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
