
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
    
    public void saveToFile(String filename) 
    {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) 
        {
            for (Product p : inventory.values()) 
            {
                bw.write(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getMinQuantity());
                bw.newLine();
            }
            System.out.println("Inventory saved to file.");
        } 
        catch (IOException e) 
        {
            System.out.println("Error saving inventory: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) 
    {
        try 
        {
            Path path = Paths.get(filename);
            if (Files.exists(path)) 
            {
                List<String> lines = Files.readAllLines(path);
                for (String line : lines) 
                {
                    String[] data = line.split(",");
                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    int qty = Integer.parseInt(data[2]);
                    int minQty = Integer.parseInt(data[3]);

                    Product p = new Product(id, name, qty, minQty);
                    inventory.put(id, p);
                }
                System.out.println("Inventory loaded from file.");
            } 
            else 
            {
                System.out.println("No previous inventory found. Starting new.");
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Error loading inventory: " + e.getMessage());
        }
    }

}
