package ServiceLayer;

import java.util.List;
import java.util.Optional;

import ModelClass.AddProduct;
import RepositoryLayer.AddProductImplementerRepository;
import RepositoryLayer.AddProductRepositerory;

public class AddProductServiceClass implements AddProductData
{
	AddProductRepositerory addprod=new AddProductImplementerRepository();
	public boolean addProducts(AddProduct prod) 
	{
		return addprod.addProducts(prod);
	}
	public Optional <List<AddProduct>> getallProductList()
	{
		return addprod.getallProductList();
	}
	public Optional<List<AddProduct>> getProductByCategory(String Product_Category)
	{
		return addprod.getProductByCategory(Product_Category);
	}
	@Override
	public boolean getUserLoginByEmail(String Email) {
		// TODO Auto-generated method stub
		return addprod.getUserLoginByEmail(Email);
	}
	@Override
	public boolean getUserLoginByPassword(String Password) {
		// TODO Auto-generated method stub
		return addprod.getUserLoginByPassword(Password);
	}
	@Override
	public boolean UserRegistration(String Name, String EmailId, String Password, String moblie) {
		// TODO Auto-generated method stub
		return addprod.UserRegistration(Name, EmailId, Password, moblie);
	}
	@Override
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price, int Quantity) {
		// TODO Auto-generated method stub
		return addprod.updateTheProductById(id, Product_Name, Product_Category, Price, Quantity);
	}
	@Override
	public boolean delteTheProductByName(String Product_Name) {
		// TODO Auto-generated method stub
		return addprod.delteTheProductByName(Product_Name);
	}
	@Override
	public boolean BuyProduct(String Name, String Address, long contact, String Prod_name, int quantity, double amt) {
		// TODO Auto-generated method stub
		return addprod.BuyProduct(Name, Address, contact, Prod_name, quantity, amt);
	}
	@Override
	public double getAmount(String Prod_name) {
		// TODO Auto-generated method stub
		return addprod.getAmount(Prod_name);
	}
	@Override
	public boolean AddBulckdata(String path) {
		// TODO Auto-generated method stub
		return addprod.AddBulckdata(path);
	}
	@Override
	public boolean orderList(int email) {
		// TODO Auto-generated method stub
		return addprod.orderList(email);
	}
	@Override
	public boolean Cart(int email, String ProductName, int quantity) {
		// TODO Auto-generated method stub
		return addprod.Cart(email, ProductName, quantity);
	}
	@Override
	public int getEmailId(String email) {
		// TODO Auto-generated method stub
		return addprod.getEmailId(email);
	}
	@Override
	public int getOrderIdByName(int email) {
		// TODO Auto-generated method stub
		return addprod.getOrderIdByName(email);
	}
	@Override
	public boolean GenrateBill(int oId) {
		// TODO Auto-generated method stub
		return addprod.GenrateBill(oId);
	}
	@Override
	public boolean paymentStatus(int orderId, String status) {
		// TODO Auto-generated method stub
		return addprod.paymentStatus(orderId, status);
	}
	@Override
	public boolean updateQuantity(String paid) {
		// TODO Auto-generated method stub
		return addprod.updateQuantity(paid);
	}
	



}
