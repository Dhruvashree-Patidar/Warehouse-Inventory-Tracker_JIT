public class Product 
{
	private int id;
    private String name;
    private int quantity;
    private int minQuantity;

    public Product(int id, String name, int quantity, int minQuantity) 
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.minQuantity = minQuantity;
    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getMinQuantity() {
		return minQuantity;
	}
    
	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public void increaseStock(int amount) 
	{
        quantity += amount;
    }

    public void decreaseStock(int amount) 
    {
        if (amount <= quantity) 
        {
            quantity -= amount;
        } 
        else 
        {
            System.out.println("Insufficient stock for " + name);
        }
    }

    @Override
    public String toString() 
    {
        return "Product [ID=" + id + ", Name=" + name + ", Quantity=" + quantity + ", minQuantity=" + minQuantity + "]";
    }
}
