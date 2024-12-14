package ModelClass;

public class AddProduct 
{
	private int pid;
	private double Price;
	private String Product_Name;
	private int Quantity;
    private String Product_Category;
    private String password="vinayak@24";
    
    public AddProduct(int pid,String Product_Name,double Price,String Product_Category)
    {
    	this.pid=pid;
    	this.Product_Name=Product_Name;
    	this.Price=Price;
    	this.Product_Category=Product_Category;
    }
    public AddProduct()
    {
    	
    }
    
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public double getPrice() {
		return Price;
	}
	public void setPrice(double price) {
		Price = price;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String product_Name) {
		Product_Name = product_Name;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getProduct_Category() {
		return Product_Category;
	}
	public void setProduct_Category(String product_Category) {
		Product_Category = product_Category;
	}
    
    public String getPassWord()
    {
    	return password;
    }

}
