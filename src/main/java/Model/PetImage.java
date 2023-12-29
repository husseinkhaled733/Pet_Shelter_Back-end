package Model;

public class PetImage {
    private int petID;
    private String imageLink;

    public PetImage(int petID, String imageLink){
        this.setPetID(petID);
        this.setImageLink(imageLink);
    }


    public int getPetID() {
        return petID;
    }

    public void setPetID(int petID) {
        this.petID = petID;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
