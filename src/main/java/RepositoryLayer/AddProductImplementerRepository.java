package RepositoryLayer;

import java.io.*;
import java.sql.*;
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
			pst=con.prepareStatement("insert into Add_Product values(0,?,?,?,?)");
			pst.setString(1,prod.getProduct_Name());
			pst.setDouble(2,prod.getPrice());
			pst.setString(3,prod.getProduct_Category());
			pst.setInt(4,prod.getQuantity());
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
				
				AddProduct allRecord=new AddProduct(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
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
				AddProduct allRecord=new AddProduct(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
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

	@Override
	public boolean BuyProduct(String Name, String Address, long contact, String Prod_name, int quantity,double amt) {
		try {
			pst=con.prepareStatement("insert into odered_history value(0,?,?,?,?,?,?)");
			pst.setString(1,Name);
			pst.setString(2,Prod_name);
			pst.setLong(3,contact);
			pst.setString(4,Address);
			pst.setInt(5,quantity);
			if(amt!=0.0)
			{
				pst.setString(6, amt+"paaid");
				int value=pst.executeUpdate();
				return value>0?true:false;
			}
			else
			{
				pst.setString(6,"pending");

			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();


		}
		return false;

	}

	@Override
	public double getAmount(String Prod_name) {
		try {
			pst=con.prepareStatement("select Product_Price from add_product where Product_Name=?");
			pst.setString(1, Prod_name);
			rs=pst.executeQuery();
			while(rs.next())
			{
				return rs.getDouble("Product_Price");
			}
		} catch (SQLException e)
		{
			System.out.println("Exception is"+e);

			return 0;

		}
		return 0;
	}

	@Override
	public boolean AddBulckdata(String path) 
	{
		try {boolean result=false;
			FileReader fr=new FileReader(path);
			BufferedReader bf=new BufferedReader(fr);
			
			String data;
			while((data=bf.readLine())!=null)
			{
			  CallableStatement c=con.prepareCall("call addBulckData(0,?,?,?,?)");
			  String bulck[]=data.split(",");
			  c.setString(1, bulck[0]);
			  c.setInt(2,Integer.parseInt(bulck[1]));
			  c.setString(3,bulck[2]);
			  c.setInt(4,Integer.parseInt(bulck[3]));
			  result=c.execute();
			}
			return !result;
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Exception is"+e);
			return false;

		}
	}


}
