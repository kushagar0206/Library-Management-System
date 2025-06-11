package LibraryManagementSystem;

 class Query {

     static String insert = "INSERT INTO book(id, name, author_name, date) VALUES(?, ?, ?, ?)";
     static String read = "SELECT * FROM book ";
     static String update = "UPDATE book SET name ? WHERE id ? ";
     static String delete = "DELETE FROM book WHWRW id ? ";

}
