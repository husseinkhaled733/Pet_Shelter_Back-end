package Model;

public class StaffBuilder {
    private final Staff staff;

    public StaffBuilder(){
        this.staff = new Staff();
    }

    public StaffBuilder setID(int id){
        staff.setStaffID(id);
        return this;
    }

    public StaffBuilder setName(String name){
        staff.setName(name);
        return this;
    }

    public StaffBuilder setRole(String role){
        staff.setRole(role);
        return this;
    }

    public StaffBuilder setPhone(String phone){
        staff.setPhone(phone);
        return this;
    }

    public StaffBuilder setEmail(String email){
        staff.setEmail(email);
        return this;
    }
    public StaffBuilder setPassword(String password){
        staff.setPassword(password);
        return this;
    }

    public StaffBuilder setShelterID(int shelterID){
        staff.setShelterID(shelterID);
        return this;
    }

    public Staff get(){
        return staff;
    }
}
