import model.entity.User;
import model.entity.enums.Role;

import java.sql.*;

public class App {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(  "jdbc:mysql://localhost:3306/admissionsystemdb","root" ,"root");
        Statement query = connection.createStatement();
        ResultSet resultSet = query.executeQuery("SELECT role FROM user");
        while(resultSet.next()) {
            User user = new User();
            Role role = Role.valueOf(resultSet.getString("user.role").toUpperCase());
            user.setRole(role);
            System.out.println(user.toString());
        }
    }
}
