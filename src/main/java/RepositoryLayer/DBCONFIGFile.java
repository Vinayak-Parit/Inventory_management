package RepositoryLayer;

import java.io.*;
import java.sql.*;
import java.util.Properties;
public class DBCONFIGFile 
{
   private static DBCONFIGFile db;
   private static  Connection con;
   private static PreparedStatement pst;
   private static  ResultSet rs;
   
   private DBCONFIGFile()
   {
	   try
	   {
	
		 Properties p=new Properties();
		 File f=new File(".");
		 FileInputStream fins=new FileInputStream(f.getAbsolutePath().substring(0,f.getAbsolutePath().length()-1)+"src/main/resources/DbconfigFileProperties.properties");
         p.load(fins);
         Class.forName(p.getProperty("driver"));
         con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
         if(con!=null)
         {
        	 System.out.println("Connection is successfull......");
         }
         else
         {
        	 System.out.println("Connection is failed..........!");
         }
	   }
	   catch(Exception e)
	   {
		   System.out.println("Exception is"+e);	   
	   }
   }
   
   public static DBCONFIGFile getInstanceOf()
   {
	   if(db==null)
	   {
		   db=new DBCONFIGFile();
	   }
	   return db;
	   
   }
   
   public static Connection getConnection()
   {
	   return con;
   }
   
   public static PreparedStatement getPreapared()
   {
	   return pst;
   }
   public static ResultSet getResultSet()
   {
	   return rs;
   }
  

}

