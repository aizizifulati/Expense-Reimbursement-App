package models;

public class ReimbursementType {

    private Integer type_id;
    private String type;

    public ReimbursementType() {
    }

    public ReimbursementType(Integer type_id, String type) {
        this.type_id = type_id;
        this.type = type;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ReimbursementType{" +
                "type_id=" + type_id +
                ", type='" + type + '\'' +
                '}';
    }
}
