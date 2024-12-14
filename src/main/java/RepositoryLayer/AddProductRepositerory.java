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
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price);
	public boolean delteTheProductByName(String Product_Name);



}
