package Controllers;

import Model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddStaffRequestWrapper {
    private Staff staff;
    private Staff manager;
}
