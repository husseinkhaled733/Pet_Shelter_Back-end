package Model;

public class PetDocument {
    private int petId;
    private String documentLink;
    private String type;

    public PetDocument(int petId, String documentLink, String type){
        this.setPetId(petId);
        this.setDocumentLink(documentLink);
        this.setType(type);
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getDocumentLink() {
        return documentLink;
    }

    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
