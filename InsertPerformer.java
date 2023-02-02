/*
 This File will take conn object from main connection and using this connection
It will perform the insert query
 */
package javaapplication2;

import java.sql.*;
import javaapplication2.SelectPerformer;
import java.util.Scanner;

/**
 *
 * @author ADNAN
 */
public class InsertPerformer {

    
    SelectPerformer sp; ///To display data after succesful insert operation
    Connection conn;    ///To get connectiomn established
    Scanner s;
    String ename;
    String query;
    int eid, did;

    //Constrctor to get the connection
    InsertPerformer(Connection c) {
        conn = c; 
        s = new Scanner(System.in);
        sp=new SelectPerformer(conn);
    }

    void performInsert() {
        /// => Take input from the user
        System.out.println("Enter the data to be inserted: ");
        System.out.println("Eid: ");
        eid =s.nextInt();
        System.out.println("Ename: ");
        s = new Scanner(System.in);
        ename = s.nextLine();
        
        System.out.println("Did: ");
        did = s.nextInt();
        
        //Prepare the query;
        query="insert into emp values(" + eid + ",'" + ename + "'," + did + ")";
        try {
            /// => Createh statement
            Statement st = conn.createStatement();

            /// => Execute the statement
            /// NOTE: for update we use executeUpdate
            int inserted = st.executeUpdate(query);
            
            /// => If inserted further display all records
            if(inserted==1){
                System.out.println(query+" Performed => Data insered into database");
                System.out.println("Updated Database is: ");
                sp.PerformSelectOperation();
            }
            else{
                throw new SQLException();
            }
        } catch (Exception e) {
            System.out.println("Unable to perform "+query+" into DB a sper error: " + e);
        }

    }
}
