package RepositoryLayer;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ModelClass.AddProduct;
import ServiceLayer.AddProductData;

public class AddProductImplementerRepository extends DATABASESTATE implements AddProductRepositerory 
{
	List l=new ArrayList();

	@Override
	public boolean addProducts(AddProduct prod) 
	{
		try 
		{
			pst=con.prepareStatement("insert into Add_Product values(0,?,?,?)");
			pst.setString(1,prod.getProduct_Name());
			pst.setDouble(2,prod.getPrice());
			pst.setString(3,prod.getProduct_Category());
			int value=pst.executeUpdate();
			return value>0?true:false;
		} 
		catch (Exception e) 
		{
			
			System.out.println("Exception is="+e);
			e.printStackTrace();
			return false;
		}
	}

	 public Optional <List<AddProduct>> getallProductList()

	{
		try 
		{
			pst=con.prepareStatement("select * from Add_Product");
			rs=pst.executeQuery();
			while(rs.next())
			{
				
				AddProduct allRecord=new AddProduct(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4));
				l.add(allRecord);
			}
			
			return l.size()>0?Optional.of(l):Optional.empty();
		} 
		catch (SQLException e) 
		{
			 System.out.println("Exception is"+e);
				return null;

		}
	}

	@Override
	public Optional<List<AddProduct>> getProductByCategory(String Product_Category) 
	{
		try
		{
			pst=con.prepareStatement("Select * from Add_Product where Product_Category=?");
			pst.setString(1, Product_Category);
			rs=pst.executeQuery();
			while(rs.next())
			{
				AddProduct allRecord=new AddProduct(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4));
                l.add(allRecord);
			}
			return l.size()>0?Optional.of(l):Optional.empty();
			
		}
		catch(Exception e)
		{
			System.out.println("Exception is "+e);
			return null;
		}
	}

	@Override
	public boolean getUserLoginByEmail(String Email) 
	{
		try 
		{
			pst=con.prepareStatement("select Email_Id  from user_registration where Email_Id=?");
			pst.setString(1, Email);
			rs=pst.executeQuery();

			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}

		} catch (SQLException e)
		{
			System.out.println("Exception is"+e);
			return false;
		}
	}

	@Override
	public boolean getUserLoginByPassword(String Password) {
		try 
		{
			pst=con.prepareStatement("select Password  from user_registration where Password=?");
			pst.setString(1, Password);
			String GetPassword=rs.getString(1);
			rs=pst.executeQuery();

			if(rs.next())
			{
				return true;
			}
			else
			{
				return false;
			}

		} catch (SQLException e)
		{
			System.out.println("Exception is"+e);
			return false;
		}

		
	}

	@Override
	public boolean UserRegistration(String Name, String EmailId, String Password , String  moblie)  
	{
		
		try {
			
			pst=con.prepareStatement("insert into User_Registration values(null,?,?,?,?)");
			pst.setString(1,Name);
			pst.setString(2,EmailId);
			pst.setString(3,Password);
			pst.setString(4,moblie);
            int value=pst.executeUpdate();
            return value>0?true:false;

			
		}
		catch (SQLException e) 
		{
			System.out.println("Exception is "+e);
			return false;
		}
		
	}

	@Override
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price)
	{
		try 
		{
			pst=con.prepareStatement("update add_Product set Product_Name=?,Product_Price=?,Product_Category=? where pid=? ");
			pst.setString(1, Product_Name);
			pst.setDouble(2, Price);
			pst.setString(3, Product_Category);

			pst.setInt(4,id);
			int value=pst.executeUpdate();
			return value>0?true:false;
		} 
		catch (SQLException e)
		{
			System.out.println("Exception is"+e);
			e.printStackTrace();
			return false;

		}
		
	}

	@Override
	public boolean delteTheProductByName(String Product_Name)
	{
		try {
			pst=con.prepareStatement("delete from add_Product where Product_Name=?");
			pst.setString(1, Product_Name);
			int value=pst.executeUpdate();
			return value>0?true:false;
		}
		catch (SQLException e)
		{
			System.out.println("Error is"+e);
			e.printStackTrace();
			return false;
		}
		
		
	}


}
