package Controllers;

import Model.Shelter;
import Model.Staff;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddShelterRequestWrapper {
    private Shelter shelter;
    private Staff manager;
}
