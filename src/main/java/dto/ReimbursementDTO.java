package dto;

import java.sql.Timestamp;
import java.util.Arrays;

public class ReimbursementDTO {
    private Integer Id;
    private Integer Amount;
    private Timestamp Submitted;
    private Timestamp Resolved;
    private String Description;
    private byte[] receipt;
    private Integer author;
    private Integer resolver;
    private Integer status_id;
    private Integer type_id;

    public ReimbursementDTO() {
    }

    public ReimbursementDTO(Integer id, Integer amount, Timestamp submitted, Timestamp resolved, String description, byte[] receipt, Integer author, Integer resolver, Integer status_id, Integer type_id) {
        Id = id;
        Amount = amount;
        Submitted = submitted;
        Resolved = resolved;
        Description = description;
        this.receipt = receipt;
        this.author = author;
        this.resolver = resolver;
        this.status_id = status_id;
        this.type_id = type_id;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getAmount() {
        return Amount;
    }

    public void setAmount(Integer amount) {
        Amount = amount;
    }

    public Timestamp getSubmitted() {
        return Submitted;
    }

    public void setSubmitted(Timestamp submitted) {
        Submitted = submitted;
    }

    public Timestamp getResolved() {
        return Resolved;
    }

    public void setResolved(Timestamp resolved) {
        Resolved = resolved;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public Integer getResolver() {
        return resolver;
    }

    public void setResolver(Integer resolver) {
        this.resolver = resolver;
    }

    public Integer getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Integer status_id) {
        this.status_id = status_id;
    }

    public Integer getType_id() {
        return type_id;
    }

    public void setType_id(Integer type_id) {
        this.type_id = type_id;
    }

    @Override
    public String toString() {
        return "ReimbursementDTO{" +
                "Id=" + Id +
                ", Amount=" + Amount +
                ", Submitted=" + Submitted +
                ", Resolved=" + Resolved +
                ", Description='" + Description + '\'' +
                ", receipt=" + Arrays.toString(receipt) +
                ", author=" + author +
                ", resolver=" + resolver +
                ", status_id=" + status_id +
                ", type_id=" + type_id +
                '}';
    }
}
