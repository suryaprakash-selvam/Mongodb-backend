package com.EFMS.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bids")
public class Bid {

    @Id
    private String id;

    private Long Bid_Id;
    private String Bid_Amount;
    private Long Project_id;
    private String UserName;
    private String Bid_Status;
    private String Bid_Date;
    private String Remarks;

    public Bid() {}

    // Getters and setters for the fields

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getBid_Id() {
        return Bid_Id;
    }

    public void setBid_Id(Long bid_Id) {
        Bid_Id = bid_Id;
    }

    public String getBid_Amount() {
        return Bid_Amount;
    }

    public void setBid_Amount(String bid_Amount) {
        Bid_Amount = bid_Amount;
    }

    public Long getProject_id() {
        return Project_id;
    }

    public void setProject_id(Long project_id) {
        Project_id = project_id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBid_Status() {
        return Bid_Status;
    }

    public void setBid_Status(String bid_Status) {
        Bid_Status = bid_Status;
    }

    public String getBid_Date() {
        return Bid_Date;
    }

    public void setBid_Date(String bid_Date) {
        Bid_Date = bid_Date;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String remarks) {
        Remarks = remarks;
    }
}
