import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/admissionsystemdb","root" ,"root");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT * FROM subject");
        while(resultSet.next()) {
            System.out.println(resultSet.getString("name_en"));
        }
    }
}
