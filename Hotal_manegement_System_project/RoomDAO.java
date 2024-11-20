import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {
    
    // Method to add a room
    public void addRoom(Room room) {
        String query = "INSERT INTO rooms (room_no, room_type, rate, status) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, room.getRoomNo());
            statement.setString(2, room.getRoomType());
            statement.setDouble(3, room.getRate());
            statement.setString(4, room.getStatus());
            statement.executeUpdate();
            System.out.println("Room added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all rooms
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM rooms";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Room room = new Room(
                        resultSet.getInt("room_no"),
                        resultSet.getString("room_type"),
                        resultSet.getDouble("rate"),
                        resultSet.getString("status")
                );
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Method to get a specific room by room number
    public Room getRoom(int roomNo) {
        Room room = null;
        String query = "SELECT * FROM rooms WHERE room_no = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomNo);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    room = new Room(
                            resultSet.getInt("room_no"),
                            resultSet.getString("room_type"),
                            resultSet.getDouble("rate"),
                            resultSet.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    // Method to update room status
    public void updateRoomStatus(int roomNo, String status) {
        String query = "UPDATE rooms SET status = ? WHERE room_no = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setInt(2, roomNo);
            statement.executeUpdate();
            System.out.println("Room status updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to update room rate
    public void updateRoomRate(int roomNo, double rate) {
        String query = "UPDATE rooms SET rate = ? WHERE room_no = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, rate);
            statement.setInt(2, roomNo);
            statement.executeUpdate();
            System.out.println("Room rate updated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete a room by room number
    public void deleteRoom(int roomNo) {
        String query = "DELETE FROM rooms WHERE room_no = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, roomNo);
            statement.executeUpdate();
            System.out.println("Room deleted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
