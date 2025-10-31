import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class save {

	
	 public void saveDataToFile(HashMap<Integer, Warehouse> warehouses) {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter("inventory.txt"))) {

	            for (Warehouse w : warehouses.values()) {
	                bw.write("WAREHOUSE: " + w.getwId() + ", " + w.getwName());
	                bw.newLine();

	                for (Product p : w.getInventory().values()) {
	                    bw.write(p.getId() + "," + p.getName() + "," + p.getQuantity() + "," + p.getMinQuantity());
	                    bw.newLine();
	                }
	                bw.newLine();
	            }

	            System.out.println("Data saved successfully to file.");

	        } catch (IOException e) {
	            System.out.println("Error while saving data: " + e.getMessage());
	        }
	    }
	 
	 
	 public HashMap<Integer, Warehouse> loadDataFromFile(StockObserver observer) {
		    HashMap<Integer, Warehouse> warehouses = new HashMap<>();

		    try {
		        Path path = Paths.get("inventory.txt");

		        if (!Files.exists(path)) {
		            System.out.println("⚠️ No previous data found (file not created yet).");
		            return warehouses;
		        }

		        List<String> lines = Files.readAllLines(path);
		        Warehouse currentWarehouse = null;

		        for (String line : lines) {
		            line = line.trim();

		            if (line.isEmpty()) continue;

		            if (line.startsWith("WAREHOUSE:")) {
		                // Extract warehouse info
		                String[] parts = line.split(":")[1].split(",");
		                int wId = Integer.parseInt(parts[0].trim());
		                String wName = parts[1].trim();

		                currentWarehouse = new Warehouse(wId, wName, observer);
		                warehouses.put(wId, currentWarehouse);
		            } 
		            else if (currentWarehouse != null) {
		                // Extract product info
		                String[] data = line.split(",");
		                int pId = Integer.parseInt(data[0].trim());
		                String pName = data[1].trim();
		                int qty = Integer.parseInt(data[2].trim());
		                int minQty = Integer.parseInt(data[3].trim());

		                Product p = new Product(pId, pName, qty, minQty);
		                currentWarehouse.add_Product(p);
		            }
		        }

		        System.out.println("Data loaded successfully.");

		    } catch (IOException e) {
		        System.out.println("Error while loading data: " + e.getMessage());
		    }

		    return warehouses;
		}
}
