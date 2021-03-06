package models;

public class ReimbursementStatus {
    private Integer status_id;
    private String status;

    public ReimbursementStatus() {
    }

    public ReimbursementStatus(Integer status_id, String status) {
        this.status_id = status_id;
        this.status = status;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "status_id=" + status_id +
                ", status='" + status + '\'' +
                '}';
    }
}
