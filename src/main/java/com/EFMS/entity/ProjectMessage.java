package com.EFMS.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "project_message")
public class ProjectMessage {

    private Long pm_id;
    private Long project_id;
    private String username;
    private String message;

    public ProjectMessage() {}

    public ProjectMessage(Long pm_id, Long project_id, String username, String message) {
        this.pm_id = pm_id;
        this.project_id = project_id;
        this.username = username;
        this.message = message;
    }

    public Long getPm_id() {
        return pm_id;
    }

    public void setPm_id(Long pm_id) {
        this.pm_id = pm_id;
    }

    public Long getProject_id() {
        return project_id;
    }

    public void setProject_id(Long project_id) {
        this.project_id = project_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
