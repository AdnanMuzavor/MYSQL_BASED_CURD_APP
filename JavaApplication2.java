/*
The aim is to learn how to perform curd operation through JAVA and MYSQL
 */
package javaapplication2;

import java.sql.*;
import java.util.Scanner;
import javaapplication2.SelectPerformer; //to perform select operation
import javaapplication2.InsertPerformer;
import javaapplication2.DeletePerformer;
import javaapplication2.UpdatePerformer;
/**
 *
 * @author ADNAN
 */
public class JavaApplication2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /// => Set the parameters needed
        String url = "jdbc:mysql://localhost/";
        String user = "root";
        String password = "";
        String DBName = "jdbc1";
        Scanner s;
        int menu_breaker = 0;
        int ch;
        /// => Establish the connection with Database
        try {
            /// => Use the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            /// => Establish the connection
            Connection conn = DriverManager.getConnection(url + DBName, user, password);
            /// => Notify regarding successful connection
            System.out.println("Connection established");

            /// => Menu
            while (true) {
                menu_breaker = 0;
                System.out.println(
                        "MENU:\n1)Select Operation\n2)Insert Operation\n3)Delete Operation\n4)Update operation\n5)To exit\nYour choice: "
                );
                s = new Scanner(System.in);
                ch = s.nextInt();
                switch (ch) {
                    case 1: /// => Fetch the records and deisplay in console using resp class
                        SelectPerformer s1 = new SelectPerformer(conn);
                        s1.PerformSelectOperation();
                        break;
                    case 2:
                        /// => Perform an insert operation
                        InsertPerformer i1 = new InsertPerformer(conn);
                        i1.performInsert();
                        break;
                    case 3:
                        /// => Perform delete operation
                        DeletePerformer d1 = new DeletePerformer(conn);
                        d1.performDelete();
                        break;
                    case 4:
                        /// => Perform update operation
                        UpdatePerformer up=new UpdatePerformer(conn);
                        up.performUpdate();
                        break;
                    case 5:
                        menu_breaker = 1;
                        break;
                    default:
                        System.out.println("Invalid input");
                }
                if (menu_breaker == 1) {
                    break;
                }
            }

            /// => close the connection After work is done
        } catch (Exception e) {
            System.out.println("Unable To connect with database as per error: " + e);
        }

    }
}
