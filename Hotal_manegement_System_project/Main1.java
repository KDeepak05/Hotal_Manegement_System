
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class customer_info{
    String Name;
    long Phone_no;
    String Gender;
    String Id;
    String Country;
    int Room_no;
    int Deposit;
    String Check_status;

    
}

public class Main1 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/customer_details";
        String user = "root";
        String password = "DEE#050606#pak";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            System.out.println("Connected to MySQL database!");

            // Query to select all records from emp table
            String query = "SELECT * FROM customer_info";
            ResultSet resultSet = statement.executeQuery(query);



            // Check if resultSet has any rows
            if (!resultSet.isBeforeFirst()) { 
                System.out.println("No data found in the emp table.");
            } else {
                // Retrieve and print column names
                int columnCount = resultSet.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getMetaData().getColumnName(i) + "\t");
                }
                System.out.println();

                // Loop through the result set and print all column values for each row
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            }
            // Insert a new record using PreparedStatement
            String insertQuery = "INSERT INTO customer_info (Name, Phone_no, Gender, Id, Country, Room_no, Deposit, Check_status) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, "Daniel");
                preparedStatement.setLong(2, 293908392);
                preparedStatement.setString(3, "M");
                preparedStatement.setString(4, "A005");
                preparedStatement.setString(5, "INDIA");
                preparedStatement.setInt(6, 221);
                preparedStatement.setInt(7, 700);
                preparedStatement.setString(8, "Checked-in");

                int rowsInserted = preparedStatement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new record was inserted successfully!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
