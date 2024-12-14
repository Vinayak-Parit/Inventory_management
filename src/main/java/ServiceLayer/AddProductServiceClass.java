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
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price) {
		// TODO Auto-generated method stub
		return addprod.updateTheProductById(id, Product_Name, Product_Category, Price);
	}
	@Override
	public boolean delteTheProductByName(String Product_Name) {
		// TODO Auto-generated method stub
		return addprod.delteTheProductByName(Product_Name);
	}

}
