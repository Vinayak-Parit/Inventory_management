package RepositoryLayer;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import ModelClass.AddProduct;
import ServiceLayer.AddProductData;

public class AddProductImplementerRepository extends DATABASESTATE implements AddProductRepositerory {
	List l = new ArrayList();

	@Override
	public boolean addProducts(AddProduct prod) {
		try {
			// if(con!=null)System.out.println("success")else System.out.println("fail");
			pst = con.prepareStatement("insert into Add_Product values(0,?,?,?,?)");
			pst.setString(1, prod.getProduct_Name());
			pst.setDouble(2, prod.getPrice());
			pst.setString(3, prod.getProduct_Category());
			pst.setInt(4, prod.getQuantity());
			int value = pst.executeUpdate();
			return value > 0 ? true : false;
		} catch (Exception e) {

			System.out.println("Exception is=" + e);
			e.printStackTrace();
			return false;
		}
	}

	public Optional<List<AddProduct>> getallProductList()

	{
		try {
			pst = con.prepareStatement("select * from Add_Product");
			rs = pst.executeQuery();
			while (rs.next()) {

				AddProduct allRecord = new AddProduct(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getInt(5));
				l.add(allRecord);
			}

			return l.size() > 0 ? Optional.of(l) : Optional.empty();
		} catch (SQLException e) {
			System.out.println("Exception is" + e);
			return null;

		}
	}

	@Override
	public Optional<List<AddProduct>> getProductByCategory(String Product_Category) {
		try {
			pst = con.prepareStatement("Select * from Add_Product where Product_Category=?");
			pst.setString(1, Product_Category);
			rs = pst.executeQuery();
			while (rs.next()) {
				AddProduct allRecord = new AddProduct(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4),
						rs.getInt(5));
				l.add(allRecord);
			}
			return l.size() > 0 ? Optional.of(l) : Optional.empty();

		} catch (Exception e) {
			System.out.println("Exception is " + e);
			return null;
		}
	}

	@Override
	public boolean getUserLoginByEmail(String Email) {
		try {
			pst = con.prepareStatement("select Email_Id  from user_registration where Email_Id=?");
			pst.setString(1, Email);
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Exception is" + e);
			return false;
		}
	}

	@Override
	public boolean getUserLoginByPassword(String Password) {
		try {
			pst = con.prepareStatement("select Password  from user_registration where Password=?");
			pst.setString(1, Password);
			String GetPassword = rs.getString(1);
			rs = pst.executeQuery();

			if (rs.next()) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println("Exception is" + e);
			return false;
		}

	}

	@Override
	public boolean UserRegistration(String Name, String EmailId, String Password, String moblie) {

		try {

			pst = con.prepareStatement("insert into User_Registration values(null,?,?,?,?)");
			pst.setString(1, Name);
			pst.setString(2, EmailId);
			pst.setString(3, Password);
			pst.setString(4, moblie);
			int value = pst.executeUpdate();
			return value > 0 ? true : false;

		} catch (SQLException e) {
			System.out.println("Exception is " + e);
			return false;
		}

	}

	@Override
	public boolean updateTheProductById(int id, String Product_Name, String Product_Category, double Price, int Quantity) {
		try {
			pst = con.prepareStatement(
					"update add_Product set Product_Name=?,Product_Price=?,Product_Category=? ,Quwhere pid=? ");
			pst.setString(1, Product_Name);
			pst.setDouble(2, Price);
			pst.setString(3, Product_Category);

			pst.setInt(4, id);
			int value = pst.executeUpdate();
			return value > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println("Exception is" + e);
			e.printStackTrace();
			return false;

		}

	}

	@Override
	public boolean delteTheProductByName(String Product_Name) {
		try {
			pst = con.prepareStatement("delete from add_Product where Product_Name=?");
			pst.setString(1, Product_Name);
			int value = pst.executeUpdate();
			return value > 0 ? true : false;
		} catch (SQLException e) {
			System.out.println("Error is" + e);
			e.printStackTrace();
			return false;
		}

	}

	public int getEmailId(String email) {
		int registerId = 0;
		try {

			pst = con.prepareStatement("SELECT Register_Id FROM user_registration WHERE Email_Id = ?");
			pst.setString(1, email);

			rs = pst.executeQuery();

			if (rs.next()) {
				registerId = rs.getInt(1);
			}
		} catch (Exception ex) {
			System.out.println("Error: " + ex.toString());
		}

		return registerId;
	}

	public int getOrderIdByName(int email) {
		try {

			pst = con.prepareStatement("SELECT order_id FROM orderlist WHERE Register_Id = ?");

			pst.setInt(1, email);
			rs = pst.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				return -1;
			}

		} catch (Exception ex) {
			System.out.println("Error : " + ex.toString());
			return -1;
		}
	}

	public boolean orderList(int email) {
		try {

		
			pst = con.prepareStatement("INSERT INTO orderlist (Register_Id) VALUES (?);");
			

			pst.setInt(1, email);

			
			int value = pst.executeUpdate();
			
			return value > 0;
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("SQL Error: " + ex.getMessage());
			return false;
		}
	}

	public boolean Cart(int orderId, String ProductName, int quantity) {

		try {

			// product Id
			pst = con.prepareStatement("SELECT pid FROM add_product WHERE Product_Name = ?");
			pst.setString(1, ProductName);
			rs = pst.executeQuery();

			int pid = 0;
			while (rs.next()) {
				pid = rs.getInt(1);
			}
		
			// price
			pst = con.prepareStatement("SELECT Product_Price FROM add_Product WHERE Product_Name =  ?");
			pst.setString(1, ProductName);
			rs = pst.executeQuery();
			double pprice = 0;
			while (rs.next()) {

				pprice = rs.getDouble("Product_Price");
			}
			System.out.println("price added");

			double total = pprice * quantity;
			System.out.println("Toatl : " + total);

			pst = con.prepareStatement("INSERT INTO cart (order_id, pid, quantity, price, total) VALUES (?, ?, ?, ?, ?)");

			pst.setInt(1, orderId); // order_id
			pst.setInt(2, pid); // pid
			pst.setInt(3, quantity); // quantity
			pst.setDouble(4, pprice); // price
			pst.setDouble(5, total); // total (quantity * price)



			int rowsAffected = pst.executeUpdate();

			return rowsAffected > 0;
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
			return false;
		}
	}

	@Override
	public boolean BuyProduct(String Name, String Address, long contact, String Prod_name, int quantity, double amt) {
		try {
			pst = con.prepareStatement("insert into odered_history value(0,?,?,?,?,?,?)");
			pst.setString(1, Name);
			pst.setString(2, Prod_name);
			pst.setLong(3, contact);
			pst.setString(4, Address);
			pst.setInt(5, quantity);
			if (amt != 0.0) {
				pst.setString(6, amt + "paid");
				int value = pst.executeUpdate();
				return value > 0 ? true : false;
			} else {
				pst.setString(6, "pending");

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
			pst = con.prepareStatement("select Product_Price from add_product where Product_Name=?");
			pst.setString(1, Prod_name);
			rs = pst.executeQuery();
			while (rs.next()) {
				return rs.getDouble("Product_Price");
			}
		} catch (SQLException e) {
			System.out.println("Exception is" + e);

			return 0;

		}
		return 0;
	}

	@Override
	public boolean AddBulckdata(String path) {
		try {
			boolean result = false;
			FileReader fr = new FileReader(path);
			BufferedReader bf = new BufferedReader(fr);

			String data;
			while ((data = bf.readLine()) != null) {
				CallableStatement c = con.prepareCall("call addBulckData(0,?,?,?,?)");
				String bulck[] = data.split(",");
				c.setString(1, bulck[0]);
				c.setInt(2, Integer.parseInt(bulck[1]));
				c.setString(3, bulck[2]);
				c.setInt(4, Integer.parseInt(bulck[3]));
				result = c.execute();
			}
			return !result;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.print("Exception is" + e);
			return false;

		}
	}
	
	public boolean paymentStatus(int orderId, String status) {
	    try {
	        // Prepare the SQL query to update the payment status
	        pst = con.prepareStatement("UPDATE orderlist SET payment_status = ? WHERE order_id = ?");
	        pst.setString(1, status); // Set the payment status (e.g., "Paid", "Unpaid")
	        pst.setInt(2, orderId);  // Set the order ID

	        // Execute the update query
	        int rowsAffected = pst.executeUpdate();

	        // Check if any rows were updated
	        if (rowsAffected > 0) {
	            System.out.println("Payment status updated successfully.");
	            return true;
	        } else {
	            System.out.println("No order found with the given ID. Payment status not updated.");
	            return false;
	        }
	    } catch (SQLException ex) {
	        System.out.println("Error while updating payment status: " + ex.getMessage());
	        return false;
	    }
	}

	
	public boolean GenrateBill(int oId) {
		try {
			
			pst = con.prepareStatement(
				    "SELECT c.order_id, p.Product_Name, o.order_date, c.quantity, c.price, c.total " +
				    "FROM cart c " +
				    "JOIN add_product p ON c.pid = p.pid " +
				    "JOIN orderlist o ON o.order_id = c.order_id " +
				    "WHERE c.order_id = ?"
				);
				pst.setInt(1, oId);
				
				double sum = 0;
				rs = pst.executeQuery();
				System.out.println("Order Id    Product Name        Order Date           Quantity   Price   Total");
				System.out.println("-----------------------------------------------------------------------------");
				while (rs.next()) {
				    int orderId = rs.getInt("order_id");
				    String productName = rs.getString("Product_Name");
				    String orderDate = rs.getString("order_date");
				    int quantity = rs.getInt("quantity");
				    double price = rs.getDouble("price");
				    double total = rs.getDouble("total");
				    sum += total;

				    System.out.printf("%-12d %-20s %-20s %-10d %-8.2f %-8.2f\n", 
		                      orderId, productName, orderDate, quantity, price, total);
				}
				System.out.println("-------------------------------------------------------------");
				System.out.println("Grand Total : " + String.format("%.2f", sum));
			
			return true;
			
		} catch(Exception ex) {
			System.out.println("Error : "+ ex.toString());
			return false;
		}
		
	
	}
	
	public void emptyCart() {
	    try {
	        // First, delete the dependent records from the bill table
	        String deleteFromBill = "DELETE FROM bill WHERE cart_id IN (SELECT cart_id FROM cart)";
	        pst = con.prepareStatement(deleteFromBill);
	        int rowsDeletedFromBill = pst.executeUpdate();
	        
	        if (rowsDeletedFromBill > 0) {
	            System.out.println("Related records in the bill table have been deleted.");
	        } else {
	            System.out.println("No related records found in the bill table.");
	        }

	        // Now, delete all records from the cart table
	        String deleteFromCart = "DELETE FROM cart";
	        pst = con.prepareStatement(deleteFromCart);
	        int rowsDeletedFromCart = pst.executeUpdate();

	        if (rowsDeletedFromCart > 0) {
	            System.out.println("Cart is successfully emptied.");
	        } else {
	            System.out.println("No rows were deleted. The cart may already be empty.");
	        }

	    } catch (SQLException ex) {
	        System.out.println("Error while emptying the cart: " + ex.getMessage());
	    }
	    
	}
	
	
	public boolean updateQuantity(String paid) {
	    try {
	        // Step 1: Update the Quantity in add_product based on cart
	        String updateQuantitySQL = 
	            "UPDATE add_product ap " +
	            "JOIN cart c ON ap.pid = c.pid " +
	            "JOIN orderlist o ON c.order_id = o.order_id " +
	            "SET ap.Quantity = ap.Quantity - c.quantity " +
	            "WHERE o.payment_status = ?";

	        pst = con.prepareStatement(updateQuantitySQL);
	        pst.setString(1, paid);
	        int rowsUpdated = pst.executeUpdate();

	        return rowsUpdated > 0;
	           
	    } catch (SQLException ex) {
	        System.out.println("Error: " + ex.getMessage());
	        return false;
	    }
	}




}
