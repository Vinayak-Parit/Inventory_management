package RepositoryLayer;

import java.util.List;
import java.util.Optional;

import ModelClass.AddProduct;

public interface AddProductRepositerory
{
	  public boolean addProducts(AddProduct prod);
	 public Optional <List<AddProduct>> getallProductList();
	public Optional<List<AddProduct>> getProductByCategory(String Product_Category);
	public boolean getUserLoginByEmail(String Email);
	public boolean getUserLoginByPassword(String Password);
	public boolean UserRegistration(String Name, String EmailId, String Password, String moblie);
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price, int Quantity);
	public boolean delteTheProductByName(String Product_Name);
	public boolean BuyProduct(String Name, String Address, long contact, String Prod_name, int quantity,double amt);
	public double getAmount(String Prod_name);
	public boolean AddBulckdata(String path);
	public boolean orderList(int email);
	public boolean Cart( int email, String  ProductName, int quantity);
	public int getEmailId(String email);
	public int getOrderIdByName(int email);
	public boolean GenrateBill(int oId);
	public boolean paymentStatus(int orderId, String status);
	public boolean updateQuantity(String paid);


}
