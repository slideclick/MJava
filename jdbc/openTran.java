// Query1.java:  Query an mSQL database using JDBC.
//
// Copyright 1998 Developer's Daily (http://www.DevDaily.com). All rights reserved.


import java.sql.*;

class openTran {
    
    public static void main (String[] args)  throws SQLException{
         {
            String url = "jdbc:sqlserver://10.175.13.170:1433;databaseName=TSQLFundamentals2008";
            Connection conn = DriverManager.getConnection(url,"sa","P@swrd");
            Statement stmt = conn.createStatement();
            
            
            stmt.executeUpdate("  BEGIN TRAN;  UPDATE Sales.OrderDetails    SET unitprice = unitprice + 1.00  WHERE productid = 2;");

            conn.close();
        } 
        
    }

}