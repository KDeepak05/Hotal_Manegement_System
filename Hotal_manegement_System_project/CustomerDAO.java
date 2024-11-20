import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (name, phone_no, gender, id_number, country) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setLong(2, customer.getPhoneNo());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getIdNumber());
            statement.setString(5, customer.getCountry());
            statement.executeUpdate();
            System.out.println("Customer added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM customers";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getString("name"),
                        resultSet.getLong("phone_no"),
                        resultSet.getString("gender"),
                        resultSet.getString("id_number"),
                        resultSet.getString("country")
                );
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void updateCustomer(Customer customer) {
        String query = "UPDATE customers SET name = ?, phone_no = ?, gender = ?, id_number = ?, country = ? WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, customer.getName());
            statement.setLong(2, customer.getPhoneNo());
            statement.setString(3, customer.getGender());
            statement.setString(4, customer.getIdNumber());
            statement.setString(5, customer.getCountry());
            statement.setInt(6, customer.getCustomerId());
            statement.executeUpdate();
            System.out.println("Customer updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(int customerId) {
        String query = "DELETE FROM customers WHERE customer_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, customerId);
            statement.executeUpdate();
            System.out.println("Customer deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
