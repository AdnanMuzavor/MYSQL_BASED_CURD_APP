/*
This File will take conn object from main connection and using this connection
It will perform the delete query
 */
package javaapplication2;

import java.sql.*;
import javaapplication2.SelectPerformer;
import java.util.Scanner;

/**
 *
 * @author ADNAN
 */
public class DeletePerformer {

    Connection conn;
    SelectPerformer sp;
    Scanner s;
    String query;
    int eid;

    DeletePerformer(Connection c) {
        conn = c;
        s = new Scanner(System.in);
        sp=new SelectPerformer(conn);
    }

    void performDelete() {
        /// => Take input the eid to be deleted
        System.out.println("Enter the eid of emp whose record is to be deleted: ");
        eid = s.nextInt();
        query = "delete from emp where eid=" + eid;
        try {
            Statement st = conn.createStatement();
            int deleted = st.executeUpdate(query);
            if (deleted >= 1) {
                System.out.println("Deleted " + deleted+ "record");
                System.out.println("Record with eid " + eid + " deleted, updated database is: ");
                sp.PerformSelectOperation();
            } else {
                throw new SQLException();
            }

        } catch (Exception e) {
            System.out.println("Delete query: " + query + " could not be peformed+ due to " + e);
        }
    }
}
