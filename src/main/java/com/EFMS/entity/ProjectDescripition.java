package com.EFMS.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "projects")
public class ProjectDescripition {
    @Id
    private ObjectId _id;
    private Long Project_Id;
    private String Project_Title;
    private String Project_details;
    private List<String> Framework;
    private String Posted_Date;
    private String Status;
    private String Assigner;
    private String poster;

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public Long getProject_Id() {
        return Project_Id;
    }

    public void setProject_Id(Long project_Id) {
        Project_Id = project_Id;
    }

    public String getProject_Title() {
        return Project_Title;
    }

    public void setProject_Title(String project_Title) {
        Project_Title = project_Title;
    }

    public String getProject_details() {
        return Project_details;
    }

    public void setProject_details(String project_details) {
        Project_details = project_details;
    }

    public List<String> getFramework() {
        return Framework;
    }

    public void setFramework(List<String> framework) {
        Framework = framework;
    }

    public String getPosted_Date() {
        return Posted_Date;
    }

    public void setPosted_Date(String posted_Date) {
        Posted_Date = posted_Date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getAssigner() {
        return Assigner;
    }

    public void setAssigner(String assigner) {
        Assigner = assigner;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
