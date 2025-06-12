
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDate;


public class App{

    static String url = "jdbc:mysql://localhost:3306/library_db";
    static String userName = "root";
    static String password = "Kushagar@2003";

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            Scanner sc = new Scanner(System.in);

            Book b = new Book(con, sc);
            b.menu();

        }
        catch(SQLException e){
               System.out.println(e.getMessage());
                e.printStackTrace();
        }
    }
}


