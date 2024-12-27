package ClientApllication;

import java.util.*;

import ModelClass.AddProduct;
import RepositoryLayer.AddProductImplementerRepository;
import ServiceLayer.*;

import java.io.*;
import java.sql.*;

public class InventoryManagemntMainClass { // main class

	public static void main(String arg[]) { // main function
		int pid;
		double Price;
		String Product_Name;
		int Quantity;
		String Product_Category;

		Scanner sc = new Scanner(System.in);
		int ch = 0;
		AddProduct addprod = new AddProduct();
		AddProductData srv = new AddProductServiceClass();
		System.out.println("Select your role: admin or user proceed here: ");
		String Role = sc.nextLine();

		if (Role.equals("admin")) { // Admin login
			System.out.println("Enter the password:");
			String password = sc.nextLine();

			if (password.equals(addprod.getPassWord())) {
				do {
					System.out.println("1. ADD PRODUCTS...");
					System.out.println("2. VIEW THE PRODUCTS....");
					System.out.println("3. VIEW THE PRODUCTS CATEGORY WISE...");
					System.out.println("4.UPDATE THE PRODUCT BYUSING PRODUCT ID..");
					System.out.println("5.DELETE THE PRODUCTS");
					System.out.println("6 ADD BULCK OF PRDUCTS");
					System.out.println("Enter your choice:");
					ch = sc.nextInt();
					sc.nextLine();

					switch (ch) {

					case 1:
						// Add product
						System.out.println("Enter the product name:");
						Product_Name = sc.nextLine();

						System.out.println("Enter the product category:");
						Product_Category = sc.nextLine();

						System.out.println("Enter the price of product:");
						Price = sc.nextDouble();

						System.out.println("Enter the quantity of product:");
						Quantity = sc.nextInt();

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
						System.out.println(String.format("%-12s%-20s%-15s%-20s%-10s", "Product_Id", "Product_Name",
								"Product_Price", "Product_Category", "Quantity"));
						System.out.println("-----------------------------------------------------------------------");
						if (o.isPresent()) {
							List<AddProduct> l = o.get();
							l.forEach((product) -> System.out.printf("%-12d%-20s%-15.2f%-20s%-10d%n", product.getPid(),
									product.getProduct_Name(), product.getPrice(), product.getProduct_Category(),
									product.getQuantity()));
						}
						o.get().clear();
						break;

					case 3:
						// View products by category
						System.out.println("Enter the category to search products:");
						Product_Category = sc.nextLine();
						Optional<List<AddProduct>> o2 = srv.getProductByCategory(Product_Category);
						System.out.println(String.format("%-12s%-20s%-15s%-20s%-10s", "Product_Id", "Product_Name",
								"Product_Price", "Product_Category", "Quantity"));
						System.out.println("----------------------------------------------------------------------");

						if (o2.isPresent()) {
							List<AddProduct> l = o2.get();
							l.forEach((product) -> System.out.printf("%-12d%-20s%-15.2f%-20s%-10d%n", product.getPid(),
									product.getProduct_Name(), product.getPrice(), product.getProduct_Category(),
									product.getQuantity()));
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

						System.out.println("Enter  product price:");
						Price = sc.nextDouble();

						System.out.println("Enter  product quantity:");
						Quantity = sc.nextInt();
						if (srv.updateTheProductById(id, Product_Name, Product_Category, Price,Quantity)) {
							System.out.println("Product is updated successfully");
						} else {
							System.out.println("Failed to update........!");
						}

						break;
					case 5:
						System.out.println("Enter the products name do you want to delete");
						System.out.println("Enter the product name:");
						Product_Name = sc.nextLine();

						if (srv.delteTheProductByName(Product_Name)) {
							System.out.println("Product deleted successfully");

						} else {
							System.out.println("failed to delte........!");
						}
						break;
					case 6:
						System.out.println("Select the file of bulck product data");
						String path = sc.nextLine();
						if (srv.AddBulckdata(path)) {
							System.out.println("Bulck data added sucefuly");
						} else {
							System.out.println("Falied to add data.......!");
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

				String Email;
				switch (cho) {
				case 1:
					// Login process
					System.out.println("*****************************LOGIN HERE**************************************");
					System.out.println("Enter the Email id:");
					Email = sc.nextLine();
					srv.getEmailId(Email);
					System.out.println("Email id Set : " + srv.getEmailId(Email));
					System.out.println("Enter the password:");
					String password = sc.nextLine();
					boolean findEmail = srv.getUserLoginByEmail(Email);
					boolean findPassword = srv.getUserLoginByPassword(password);
					if (findEmail && findPassword) {
						// If login is successful, show product options
						do {
							System.out.println("1. VIEW THE PRODUCTS....");
							System.out.println("2. VIEW THE PRODUCTS CATEGORY WISE");
							System.out.println("3. BUY THE PRODUCTS");
							System.out.println("4. Genrate Bill");
							System.out.println("Enter your choice:");
							ch = sc.nextInt();
							sc.nextLine();

							switch (ch) {
							case 1:
								// View all products
								Optional<List<AddProduct>> o = srv.getallProductList();
								System.out.println(String.format("%-12s%-20s%-15s%-20s%-10s", "Product_Id",
										"Product_Name", "Product_Price", "Product_Category", "Quantity"));
								System.out.println(
										"--------------------------------------------------------------------");
								if (o.isPresent()) {
									List<AddProduct> l = o.get();
									l.forEach((product) -> System.out.printf("%-12d%-20s%-15.2f%-20s%-10d%n",
											product.getPid(), product.getProduct_Name(), product.getPrice(),
											product.getProduct_Category(), product.getQuantity()));
								}
								o.get().clear();
								break;

							case 2:
								// View products by category
								System.out.println("Enter the category to search products:");
								Product_Category = sc.nextLine();
								Optional<List<AddProduct>> o2 = srv.getProductByCategory(Product_Category);
								System.out.println(String.format("%-12s%-20s%-15s%-20s%-10s", "Product_Id",
										"Product_Name", "Product_Price", "Product_Category", "Quantity"));
								System.out.println(
										"----------------------------------------------------------------------");

								if (o2.isPresent()) {
									List<AddProduct> l = o2.get();
									l.forEach((product) -> System.out.printf("%-12d%-20s%-15.2f%-20s%-10d%n",
											product.getPid(), product.getProduct_Name(), product.getPrice(),
											product.getProduct_Category(), product.getQuantity()));
								}
								o2.get().clear();
								break;
							case 3:
								do {
									 srv.getEmailId(Email);
									srv.orderList(srv.getEmailId(Email));
									
									System.out.println("Enter Product Name: ");
									String pname = sc.nextLine();

									System.out.println("Enter Product Quantity: ");
									int pqty = sc.nextInt();

									int orderid = srv.getOrderIdByName(srv.getEmailId(Email));
									if (srv.Cart(orderid, pname, pqty)) {
										System.out.println("Product added to the order successfully!");
									} else {
										System.out.println("Failed to add the product to the order.");
									}

									System.out.println("Do you want to add another product? (yes/no): ");
									sc.nextLine(); // Clear the buffer
									String choice = sc.nextLine();
									if (choice.equalsIgnoreCase("no")) {
										break; // Exit the loop
									}
								} while (true);
								break;
								
							case 4:
								int oid = srv.getOrderIdByName(srv.getEmailId(Email));
								if(srv.GenrateBill(oid)) {
									System.out.println("Bill Genrated Successfull");
									
									System.out.println("Payment Paid Yes Or No");
									String paid = sc.nextLine();
									
									if(paid.equalsIgnoreCase("yes")) {
										if(srv.paymentStatus(srv.getOrderIdByName(srv.getEmailId(Email)), "paid")) {
											
											if(srv.updateQuantity("paid")) {
												System.out.println("Payment Successfully..");
												new AddProductImplementerRepository().emptyCart();
											} else {
												System.out.println("payment unsuccessfully..");
											}
										} else {
											
											System.out.println("Payment unsucessfull...");
										}
									}
								} else {
									
									System.out.println("You want Empty this cart : Yes / No");
									String ans = sc.nextLine();
									if(ans.equalsIgnoreCase("YES")) {
										new AddProductImplementerRepository().emptyCart();
										System.out.println("Cart empty successfull....");
									}
									
								}
								break;

							default:
								System.out.println("Wrong choice...........!");
								break;
							}

						} while (ch > 0);

					} else {
						System.out.println("Error: Login failed!");
					}
					break;

				case 2:
					do {

						System.out.println(
								"=====================>[ Press 1.For continue registration ]<================>");

						System.out.println("<=====================>[ Press 2. for go back ]<================<");
						System.out.println("Enter the choice:");
						ch = sc.nextInt();
						sc.nextLine();
						switch (ch) {

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
							if (srv.UserRegistration(Name, EmailId, Password, mobile)) {
								System.out.println("REGISTERED SUCCESSFULLY!");
								// After successful registration, automatically go to login
								System.out.println("Now, proceed to login.");
								validChoice = true; // Set to true to break the loop and go to login
								break;
							} else {
								System.out.println("REGISTRATION FAILED!");
							}

							break;
						
						}

					} while (ch > 0);
					break;

				default:
					// Invalid choice handling
					System.out.println("Invalid choice. Please try again.");
					break;
				}
			} while (!validChoice); // Continue the loop until a valid choice (login or registration) is made

		}

		else {
			System.out.println("Invalid role! Please choose either 'admin' or 'user'.");
		}

	}

}
