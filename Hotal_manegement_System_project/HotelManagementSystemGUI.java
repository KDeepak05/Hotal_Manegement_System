import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class HotelManagementSystemGUI {
    private JFrame frame;
    private JTextField nameField, phoneField, genderField, idField, countryField;
    private JButton addCustomerButton, viewRoomsButton, makeBookingButton;
    
    private CustomerDAO customerDAO;
    private RoomDAO roomDAO;
    private BookingDAO bookingDAO;

    public HotelManagementSystemGUI() {
        customerDAO = new CustomerDAO();
        roomDAO = new RoomDAO();
        bookingDAO = new BookingDAO();
        
        frame = new JFrame("Hotel Management System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Create the input panel for customer information
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Labels and input fields
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Phone:"));
        phoneField = new JTextField();
        inputPanel.add(phoneField);

        inputPanel.add(new JLabel("Gender:"));
        genderField = new JTextField();
        inputPanel.add(genderField);

        inputPanel.add(new JLabel("ID Number:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Country:"));
        countryField = new JTextField();
        inputPanel.add(countryField);

        frame.add(inputPanel, BorderLayout.CENTER);

        // Create a button panel at the bottom
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

        // Buttons
        addCustomerButton = new JButton("Add Customer");
        viewRoomsButton = new JButton("View Rooms");
        makeBookingButton = new JButton("Make Booking");

        addCustomerButton.addActionListener(new AddCustomerActionListener());
        viewRoomsButton.addActionListener(new ViewRoomsActionListener());
        makeBookingButton.addActionListener(new MakeBookingActionListener());

        buttonPanel.add(addCustomerButton);
        buttonPanel.add(viewRoomsButton);
        buttonPanel.add(makeBookingButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // Action listener to add a customer
    private class AddCustomerActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            long phone = Long.parseLong(phoneField.getText());
            String gender = genderField.getText();
            String idNumber = idField.getText();
            String country = countryField.getText();

            Customer customer = new Customer(name, phone, gender, idNumber, country);
            customerDAO.addCustomer(customer);
            JOptionPane.showMessageDialog(frame, "Customer added successfully!");
        }
    }

    // Action listener to view rooms
    private class ViewRoomsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            java.util.List<Room> rooms = roomDAO.getAllRooms();
            StringBuilder roomsInfo = new StringBuilder("Room List:\n");
            for (Room room : rooms) {
                roomsInfo.append("Room No: ").append(room.getRoomNo())
                        .append(", Type: ").append(room.getRoomType())
                        .append(", Rate: ").append(room.getRate())
                        .append(", Status: ").append(room.getStatus())
                        .append("\n");
            }
            JOptionPane.showMessageDialog(frame, roomsInfo.toString());
        }
    }

    // Action listener to make a booking
    private class MakeBookingActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int customerId = Integer.parseInt(JOptionPane.showInputDialog("Enter Customer ID:"));
                int roomNo = Integer.parseInt(JOptionPane.showInputDialog("Enter Room No:"));
                Date checkInDate = Date.valueOf(JOptionPane.showInputDialog("Enter Check-in Date (YYYY-MM-DD):"));
                Date checkOutDate = Date.valueOf(JOptionPane.showInputDialog("Enter Check-out Date (YYYY-MM-DD):"));

                Booking booking = new Booking(customerId, roomNo, checkInDate, checkOutDate, "Reserved");
                bookingDAO.addBooking(booking);
                JOptionPane.showMessageDialog(frame, "Booking added successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error in booking: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new HotelManagementSystemGUI();
    }
}
