package RepositoryLayer;

import java.sql.PreparedStatement;
import java.sql.*;

public class DATABASESTATE 
{
	protected DBCONFIGFile d = DBCONFIGFile.getInstanceOf();
	protected PreparedStatement pst=d.getPreapared();
	protected ResultSet rs=d.getResultSet();
	protected Connection con=d.getConnection();
	
}
