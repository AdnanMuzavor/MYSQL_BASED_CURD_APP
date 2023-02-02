/*
This File will take conn object from main connection and using this connection
It will perform the update query
 */
package javaapplication2;

import java.sql.*;
import javaapplication2.SelectPerformer;
import java.util.Scanner;

/**
 *
 * @author ADNAN
 */
public class UpdatePerformer {

    SelectPerformer sp; ///To display data after succesful insert operation
    Connection conn;    ///To get connectiomn established
    Scanner s;
    String Updated_ename;
    String query;
    String attribute;
    String value;
    int Updated_eid, Updated_did, finder, ch;

    //Constrctor to get the connection
    UpdatePerformer(Connection c) {
        conn = c;
        s = new Scanner(System.in);
        sp = new SelectPerformer(conn);
    }

    void performUpdate() {
        int ch;

        try {
            System.out.println("Enter the eid for whom you wnat to update: ");
            finder = s.nextInt();
            /// => Take input from the user the attribuet so as to avoid multiple if elses
            System.out.println("Enter the attribute to be updated\n1)Eid\n2)Ename\n3)Did\nYour choice:  ");
            ch = s.nextInt();
            switch (ch) {
                case 1:
                    System.out.println("Eid: ");
                    attribute = "eid";
                    Updated_eid = s.nextInt();
                    break;
                case 2:
                    System.out.println("Ename: ");
                    attribute = "ename";
                    s = new Scanner(System.in);
                    Updated_ename = s.nextLine();
                    break;
                case 3:
                    System.out.println("Did: ");
                    Updated_did = s.nextInt();
                    attribute = "did";
                    break;
                default:
                    System.out.println("Invalid attribue chosen");
                    throw new SQLException();
            }

            //Prepare the query;
            //if it;s ename youll need a single ('') quotes else no issue
            query = "update emp set "
                    + attribute + "="
                    + (ch == 2 ? ("'" + Updated_ename + "'") : (ch == 1) ? Updated_eid : Updated_did)
                    + " where eid=" + finder;

            /// => Createh statement
            Statement st = conn.createStatement();

            /// => Execute the statement
            /// NOTE: for update we use executeUpdate
            int updated = st.executeUpdate(query);

            /// => If inserted further display all records
            if (updated>= 1) {
                System.out.println(query + " Performed => Data updated into database");
                System.out.println("Updated Database is: ");
                sp.PerformSelectOperation();
            } else {
                throw new SQLException();
            }
        } catch (Exception e) {
            System.out.println("Unable to perform " + query + " into DB a sper error: " + e);
        }

    }
}
