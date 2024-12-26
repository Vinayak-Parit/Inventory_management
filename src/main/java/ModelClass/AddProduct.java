package ModelClass;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
public class AddProduct   //product Model
{
	private int pid;
	private double Price;
	private String Product_Name;
	private int Quantity;
    private String Product_Category;
    private String password="vinayak@24";
    
    public AddProduct(int pid,String Product_Name,double Price,String Product_Category,int Quantity)
    {
    	this.pid=pid;
    	this.Product_Name=Product_Name;
    	this.Price=Price;
    	this.Product_Category=Product_Category;
    	this.Quantity=Quantity;
    }
    
    public AddProduct()
    {

    }
    
    public String getPassWord()
    {
    	return password;
    }

}
