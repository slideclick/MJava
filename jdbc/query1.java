// Query1.java:  Query an mSQL database using JDBC.
//
// Copyright 1998 Developer's Daily (http://www.DevDaily.com). All rights reserved.


import java.sql.*;

class Query1 {
    
    public static void main (String[] args)  throws SQLException{
         {
            String url = "jdbc:sqlserver://10.175.13.170:1433;databaseName=TSQLFundamentals2008";
            Connection conn = DriverManager.getConnection(url,"sa","P@swrd");
            Statement stmt = conn.createStatement();
            ResultSet rs;
            
            rs = stmt.executeQuery("    SELECT orderid, productid, unitprice  FROM Sales.OrderDetails  WHERE productid = 2;");
            while ( rs.next() ) {
                String lastName = rs.getString("Lname");
                System.out.println(lastName);
            }
            conn.close();
        } 
        
    }

}