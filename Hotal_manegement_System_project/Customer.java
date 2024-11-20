public class Customer {
    private int customerId;
    private String name;
    private long phoneNo;
    private String gender;
    private String idNumber;
    private String country;

    // Constructor
    public Customer(String name, long phoneNo, String gender, String idNumber, String country) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.gender = gender;
        this.idNumber = idNumber;
        this.country = country;
    }

    // Getters and Setters
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public String getName() { return name; }
    public long getPhoneNo() { return phoneNo; }
    public String getGender() { return gender; }
    public String getIdNumber() { return idNumber; }
    public String getCountry() { return country; }
}
