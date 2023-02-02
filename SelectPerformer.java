/*
This File will take conn object from main connection and using this connection
It will perform the select query
 */
package javaapplication2;

import java.sql.*;


/**
 *
 * @author ADNAN
 */
public class SelectPerformer {

    Connection conn;

    //Initialse the Connection with connection established with DataBase
    SelectPerformer(Connection c) {
        conn = c;
    }

    void PerformSelectOperation() {
        try {
            /// => Create statement using connection established
            Statement st = conn.createStatement();

            /// => Get the result os select query and store in result set
            /// NOTE: executeQuery is used exclusively only for SELECT statement
            ResultSet rs = st.executeQuery("select * from emp");

            System.out.println(" | EID | Ename | DID |");
            /// =>Print the results one by one
            while (rs.next()) {
                /// => Fetch the tabel details using getString() function
                ///    which acts on rs i.e objetc of ResultSet class
                /// NOTE: It is 1 based indexing here
                String eid = rs.getString(1);
                String ename = rs.getString(2);
                String did = rs.getString(3);
                System.out.println(" | " + eid + " | " + ename + " | " + did + " | ");
            }

        } catch (Exception e) {
            System.out.println("Unable to select data from DB as per error: " + e);
        }
    }
}
