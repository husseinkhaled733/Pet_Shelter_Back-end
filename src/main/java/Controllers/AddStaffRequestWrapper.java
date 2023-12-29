package Controllers;

import Model.Staff;

public class AddStaffRequestWrapper {
    private Staff staff;
    private Staff manager;

    public AddStaffRequestWrapper(Staff staff, Staff manager){
        this.staff = staff;
        this.manager = manager;
    }


    public Staff getStaff() {
        return staff;
    }

    public Staff getManager() {
        return manager;
    }
}
