import java.sql.Date;

public class Booking {
    private int bookingId;  // Field for booking ID
    private int customerId;
    private int roomNo;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;

    // Constructor without bookingId (for new bookings)
    public Booking(int customerId, int roomNo, Date checkInDate, Date checkOutDate, String status) {
        this.customerId = customerId;
        this.roomNo = roomNo;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    }

    // Constructor with bookingId (for existing bookings)
    public Booking(int bookingId, int customerId, int roomNo, Date checkInDate, Date checkOutDate, String status) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.roomNo = roomNo;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.status = status;
    }

    // Getters and Setters
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
