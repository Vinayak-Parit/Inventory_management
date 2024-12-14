package ClientApllication;

import java.util.*;

import ModelClass.AddProduct;
import ServiceLayer.*;

import java.sql.*;

public class InventoryManagemntMainClass {

    public static void main(String arg[]) {
        int pid;
        double Price;
        String Product_Name;
        int Quantity;
        String Product_Category;

        Scanner sc = new Scanner(System.in);
        int ch = 0;
        AddProduct addprod = new AddProduct();
        AddProductData srv = new AddProductServiceClass();
        System.out.println("Select your role: admin or user");
        String Role = sc.nextLine();
          
        if (Role.equals("admin")) {
            System.out.println("Enter the password:");
            String password = sc.nextLine();

            if (password.equals(addprod.getPassWord())) {
                do {
                    System.out.println("1. ADD PRODUCTS....");
                    System.out.println("2. VIEW THE PRODUCTS....");
                    System.out.println("3. VIEW THE PRODUCTS CATEGORY WISE");
                    System.out.println("4.UPDATE THE PRODUCT BYUSING PRODUCT ID");
                    System.out.println("Enter your choice:");
                    ch = sc.nextInt();
                    sc.nextLine();

                    switch (ch) {

                        case 1:
                            // Add product
                            System.out.println("Enter the product id:");
                            pid = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the product name:");
                            Product_Name = sc.nextLine();

                            System.out.println("Enter the product category:");
                            Product_Category = sc.nextLine();

                            System.out.println("Enter the price of product:");
                            Price = sc.nextDouble();

                            System.out.println("Enter the quantity of product:");
                            Quantity = sc.nextInt();
                            addprod.setPid(pid);
                            addprod.setProduct_Name(Product_Name);
                            addprod.setPrice(Price);
                            addprod.setQuantity(Quantity);
                            addprod.setProduct_Category(Product_Category);
                            if (srv.addProducts(addprod)) {
                                System.out.println("Product Added Successfully");
                            } else {
                                System.out.println("Product Not Added Successfully.....!");
                            }
                            break;

                        case 2:
                            // View all products
                            Optional<List<AddProduct>> o = srv.getallProductList();
                            System.out.println("Product_Id\tProduct_Name\tProduct_Price\tProduct_Category");
                            if (o.isPresent()) {
                                List<AddProduct> l = o.get();
                                l.forEach((product) -> System.out
                                        .println(product.getPid() + "\t\t" + product.getProduct_Name()+ " \t\t"
                                                + product.getPrice() + "\t\t  " + product.getProduct_Category()));
                            }
                            o.get().clear();
                            break;

                        case 3:
                            // View products by category
                            System.out.println("Enter the category to search products:");
                            Product_Category = sc.nextLine();
                            Optional<List<AddProduct>> o2 = srv.getProductByCategory(Product_Category);
                            System.out.println("Product_Id\t\t\tProduct_Name\t\t\tProduct_Price\t\t\tProduct_Category");

                            if (o2.isPresent()) {
                                List<AddProduct> l = o2.get();
                                l.forEach((product) -> System.out
                                        .println(product.getPid() + "\t\t\t" + product.getProduct_Name() + "\t\t\t"
                                                + product.getPrice() + "\t\t\t" + product.getProduct_Category()));
                            }
                            o2.get().clear();
                            break;
                        case 4:
                        	
                            System.out.println("Enter the product id:");
                            int id = sc.nextInt();
                            sc.nextLine();
                            System.out.println("Enter the product name:");
                            Product_Name = sc.nextLine();

                            System.out.println("Enter the product category:");
                            Product_Category = sc.nextLine();

                            System.out.println("Enter the price of product:");
                            Price = sc.nextDouble();

                            System.out.println("Enter the quantity of product:");
                            Quantity = sc.nextInt();
                             if(srv.updateTheProductById(id, Product_Name, Product_Category, Price))
                             {
                            	 System.out.println("Product is updated successfully");
                             }
                             else
                             {
                            	 System.out.println("Failed to update........!");
                             }

                        	
                        	break;
                        case 5:
                        	System.out.println("Enter the products name do you want to delete");
                        	System.out.println("Enter the product name:");
                            Product_Name = sc.nextLine();
                            
                            if(srv.delteTheProductByName(Product_Name))
                            {
                            	System.out.println("Product deleted successfully");
                        
                            }
                            else
                            {
                            	System.out.println("failed to delte........!");
                            }
                        	break;
                        default:
                            System.out.println("Wrong choice...........!");
                            break;
                    }
                } while (ch > 0);
            } else {
                System.out.println("Wrong password");
            }

        } else if (Role.equals("user")) {
            boolean validChoice = false;
            
            do {
            		
            	
                System.out.println("If you are already registered, press 1 to login, or press 2 to register:");
                int cho = sc.nextInt();
                sc.nextLine();

                switch (cho) 
                {
                    case 1:
                        // Login process kara
                        System.out.println("*****************************LOGIN HERE**************************************");
                        System.out.println("Enter the Email id:");
                        String Email = sc.nextLine();
                        System.out.println("Enter the password:");
                        String password = sc.nextLine();
                        boolean findEmail = srv.getUserLoginByEmail(Email);
                        boolean findPassword = srv.getUserLoginByPassword(password);
                        if (findEmail && findPassword)
                        {
                            // If login is successful, show product options
                            do 
                            {
                                System.out.println("1. VIEW THE PRODUCTS....");
                                System.out.println("2. VIEW THE PRODUCTS CATEGORY WISE");
                                System.out.println("3.BUY THE PRODUCTS");
                                System.out.println("Enter your choice:");
                                ch = sc.nextInt();
                                sc.nextLine();

                                switch (ch) 
                                {  
                                    case 1:
                                        // View all products
                                        Optional<List<AddProduct>> o = srv.getallProductList();
                                        System.out.println("Product_Id\tProduct_Name\tProduct_Price\tProduct_Category");
                                        if (o.isPresent())
                                        {
                                            List<AddProduct> l = o.get();
                                            l.forEach((product) -> System.out
                                                    .println(product.getPid() + "\t\t" + product.getProduct_Name() + "\t\t"
                                                            + product.getPrice() + "\t\t" + product.getProduct_Category()));
                                        }
                                        o.get().clear();
                                        break;

                                    case 2:
                                        // View products by category
                                        System.out.println("Enter the category to search products:");
                                        Product_Category = sc.nextLine();
                                        Optional<List<AddProduct>> o2 = srv.getProductByCategory(Product_Category);
                                        System.out.println("Product_Id\tProduct_Name\tProduct_Price\tProduct_Category");

                                        if (o2.isPresent()) 
                                        {
                                            List<AddProduct> l = o2.get();
                                            l.forEach((product) -> System.out
                                                    .println(product.getPid() + "\t\t" + product.getProduct_Name() + "\t\t"
                                                            + product.getPrice() + "\t\t" + product.getProduct_Category()));
                                        }
                                        o2.get().clear();
                                        break;

                                    default:
                                        System.out.println("Wrong choice...........!");
                                        break;
                                }
                                
                            } while (ch > 0);

                        } 
                        else
                        {
                            System.out.println("Error: Login failed!");
                        }
                        break;

                    case 2:
                    	do
                    	{
                    		
                    		System.out.println("=====================>Frees 1.For continue registration================>");
                    		
                    		System.out.println("<=====================Fress 2. for go back ================<");
                    		System.out.println("Enter the choice:");
                    		ch=sc.nextInt();
                    	switch(ch)
                    	{
                    	
                    case 1:
                        // User registration process
                        System.out.println("Enter your name:");
                        String Name = sc.nextLine();

                        System.out.println("Enter your Email id:");
                        String EmailId = sc.nextLine();

                        System.out.println("Enter your Password:");
                        String Password = sc.nextLine();

                        System.out.println("Enter your Mobile number:");
                        String mobile = sc.nextLine();
                        if (srv.UserRegistration(Name, EmailId, Password, mobile)) 
                        {
                            System.out.println("REGISTERED SUCCESSFULLY!");
                            // After successful registration, automatically go to login
                            System.out.println("Now, proceed to login.");
                            validChoice = true;  // Set to true to break the loop and go to login
                            break;
                        } 
                        else 
                        {
                            System.out.println("REGISTRATION FAILED!");
                        }
                    		
                        break;
                    case 2:
                    	
                    	break;
                    }
                    		
                   }while(ch>0);
                     break;
                    	
                    case 3:
                    	System.out.println("Enter The Name:");
                    	String Name=sc.nextLine();
                    	
                    	System.out.println("Enter the Address:");
                    	String Address=sc.nextLine();
                    	
                    	System.out.println("Enter the contact number:");
                    	long contact=sc.nextLong();
                    	sc.nextLine();
                    	System.out.println("Enter the product name do u want to buy:");
                    	String Prod_name=sc.nextLine();
                    	System.out.println("Enter the quantity of product to buy: ");
                    	int quantity=sc.nextInt();
                    	
                    	
                    	break;
                    	
                    default:
                        // Invalid choice handling
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } while (!validChoice);  // Continue the loop until a valid choice (login or registration) is made
        
        }
        
        else
        {
            System.out.println("Invalid role! Please choose either 'admin' or 'user'.");
        }
       
        }
    
}
