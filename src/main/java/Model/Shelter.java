package Model;

public class Shelter {
    private int id;
    private String name;
    private String country;
    private String city;
    private String phone;
    private String email;
    private String detailedAddress;
    private int managerID;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    @Override
    public String toString() {
        return String.format(
                "{id = %d, name = %s, country = %s, city = %s, phone = %s, " +
                "email = %s, detailedAddress = %s, managerID = %d}",
                getId(),
                getName(),
                getCountry(),
                getCity(),
                getPhone(),
                getEmail(),
                getDetailedAddress(),
                getManagerID());
    }
}
