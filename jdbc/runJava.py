from java.lang import *  
from java.sql import *  
import sys  
def createEmployee(**args):  
    global stmt  
    sqlTemplate='''
    INSERT INTO Customers(cust_id, cust_name, cust_address, cust_city, cust_state, cust_zip, cust_country, cust_contact)
VALUES('100006', 'The Toy Store', '4545 53rd Street', 'Chicago', 'IL', '54545', 'USA', 'Kim Howard');'''  
    # dictionary based SQL string formatting  
    sql=sqlTemplate % args    
    stmt.addBatch(sql)  
      
Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance()  
url = "jdbc:sqlserver://10.175.13.170:1433;databaseName=Forta";
con = DriverManager.getConnection(url,"sa","P"); 
con.setAutoCommit(0)  
stmt = con.createStatement()  
execfile(r'joescript.txt')  
stmt.executeBatch()  
stmt.close()  
# commit  
con.commit()  
con.close() 