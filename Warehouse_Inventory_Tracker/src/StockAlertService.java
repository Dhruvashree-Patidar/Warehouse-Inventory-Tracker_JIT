
public class StockAlertService implements StockObserver 
{

	@Override
	public void onLowStock(Product p) 
	{
		System.out.println("ALERT: Low stock for " + p.getName() 
        + " - Only " + p.getQuantity() + " left!");
		
	}
}

