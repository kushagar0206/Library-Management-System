package LibraryManagementSystem;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;


class Book{


    Connection con;
    Scanner sc;



        Book(Connection con, Scanner sc) {
            this.con = con;
            this.sc = sc;
        }



        public void menu () {
            System.out.println("-------------Welcome To Libraray-----------");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Rent Book");
            System.out.println("4. Rented Book List");
            System.out.println();

            System.out.print("Enter Your Choice: ");
            int option = sc.nextInt();
            System.out.println();

            if (option == 1) {
                addBook(con, sc);
            } else if (option == 2) {
                deleteBook(con, sc);

            } else if (option == 3) {
                rentBook(con, sc);
            } else if (option == 4) {
                rentedBookList(con, sc);

            }
        }


        public void addBook (Connection con, Scanner sc){
            String query = "INSERT INTO book(book_name, author_name) VALUES(?, ?)";
            try {

                PreparedStatement ps = con.prepareStatement(query);


                System.out.println("-----Add Book-----");

                System.out.print("Book Name: ");
                String bookName = sc.nextLine();
                ps.setString(1, bookName);
                sc.nextLine();


                System.out.print("Author Name: ");
                String authorName = sc.nextLine();
                ps.setString(2, authorName);

                int rawsAffected = ps.executeUpdate();

                if (rawsAffected > 0) {
                    System.out.println();
                    System.out.println("Book Added");
                }

                System.out.println();
                System.out.print("Enter 0 to Exit: ");
                int zero = sc.nextInt();
                System.out.println();

                if (zero == 0) {
                    menu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        public void deleteBook (Connection con, Scanner sc){
            String query = "SELECT * FROM book";
            String deleteQuery = "DELETE FROM book WHERE id = ?";

            try {
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                System.out.println("-----Delete Book-----");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String bName = rs.getString("book_name");
                    String aName = rs.getString("author_name");

                    System.out.println( id );
                    System.out.println("Book Name: " + bName);
                    System.out.println("Author Name: " + aName);
                    System.out.println();
                }


                System.out.println();
                PreparedStatement pa = con.prepareStatement(deleteQuery);

                System.out.print("Enter Book Id for Deleting: ");
                int choice = sc.nextInt();

                pa.setInt(1, choice);
                int rawf = pa.executeUpdate();

                System.out.println();

                if (rawf > 0) {
                    System.out.println("Book Deleted");
                }

                System.out.println();
                System.out.print("Enter 0 to Exit: ");
                int zero = sc.nextInt();

                if (zero == 0) {
                    menu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        public void rentBook (Connection con, Scanner sc){
            String query = "SELECT * FROM book";
            String querya = "SELECT book_name, author_name FROM book WHERE id = ?";
            String queryb = "INSERT INTO rented_book(book_name, author_name, student_roll_no, student_name, degree, date) VALUES(?, ?, ?, ?, ?, ?)";

            try {
                System.out.println("-----Rent Book-----");

                PreparedStatement ps = con.prepareStatement(query);

                ResultSet rs = ps.executeQuery();


                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("book_name");
                    String authorName = rs.getString("author_name");

                    System.out.println( id );
                    System.out.println("Book Name: " + name);
                    System.out.println("Author Name: " + authorName);
                    System.out.println();
                }

                System.out.print("Enter Your Choice: ");
                int rent = sc.nextInt();


                PreparedStatement pst = con.prepareStatement(querya);
                pst.setInt(1, rent);
                ResultSet rst = ps.executeQuery();


                while (rst.next()){
                    int ida = rs.getInt("id");
                    String bookName = rs.getString("book_name");
                    String authorName = rs.getString("author_name");
                    break;
                }

                sc.nextLine();
                System.out.println("Your book ready for renting");
                System.out.println();
                System.out.println("Please give information blow");
                System.out.println();


                System.out.println("Student Roll No: ");
                int rollNo = sc.nextInt();

                System.out.println("Student Name: ");
                String studentName = sc.nextLine();

                System.out.println("Degree/Course: ");
                String degree = sc.nextLine();

                LocalDate date = LocalDate.now();

                PreparedStatement pstm = con.prepareStatement(queryb);
                pstm.setString(1, "bookName");
                pstm.setString(2, "authorName");
                pstm.setInt(3, rollNo);
                pstm.setString(4, "studentName");
                pstm.setString(5, "degree");
                // pstm.setDate(8, "date");


                System.out.println();
                System.out.println("Book Rented");
                System.out.println();

                System.out.print("Enter 0 to Exit: ");
                int zero = sc.nextInt();

                if (zero == 0) {
                    menu();
                }
                rs.close();
                rst.close();
                con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        public void rentedBookList (Connection con, Scanner sc){
            String query = "SELECT * FROM rented_book";
            try {
                System.out.println("-----Rented Book List-----");
                PreparedStatement ps = con.prepareStatement(query);
                ResultSet rs = ps.executeQuery();


                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("book_name");
                    String authorName = rs.getString("author_name");
                    int student_roll_no = rs.getInt("student_roll_no");
                    String studentName = rs.getString("student_name");
                    String degree = rs.getString("degree");
                    String date = rs.getNString("date");

                    sc.nextLine();
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Author Name: " + authorName);
                    System.out.println("Student Roll No: " + student_roll_no);
                    System.out.println("Student Name: " + studentName);
                    System.out.println("Degree: " + degree);
                    // System.out.println("Issued Date: " + date);
                }

                sc.nextLine();
                System.out.print("Enter 0 to Exit: ");
                int zero = sc.nextInt();

                if (zero == 0) {
                    menu();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

}

