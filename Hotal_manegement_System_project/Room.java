public class Room {
    private int roomNo;
    private String roomType;
    private double rate;
    private String status;

    // Constructor
    public Room(int roomNo, String roomType, double rate, String status) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.rate = rate;
        this.status = status;
    }

    // Getters and Setters
    public int getRoomNo() { return roomNo; }
    public String getRoomType() { return roomType; }
    public double getRate() { return rate; }
    public String getStatus() { return status; }
}
