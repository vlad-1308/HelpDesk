package ru.krasnik.helpdesk.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "entries")
@Data
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "theme")
    private String theme;
    @Column(name = "text")
    private String text;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "created_by")
    private Employee createdEmp;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name = "modified_date")
    private Date modifiedDate;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "modified_by")
    private Employee modifiedEmp;


    public Entry() {
    }

    public Entry(String theme, String text) {
        this.theme = theme;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {

            this.theme = theme;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {

            this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        if (getCreatedDate() == null)
            this.createdDate = createdDate;
    }

    public Employee getCreatedEmp() {
        return createdEmp;
    }

    public void setCreatedEmp(Employee createdEmp) {
        if (getCreatedEmp() == null)
            this.createdEmp = createdEmp;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Employee getModifiedEmp() {
        return modifiedEmp;
    }

    public void setModifiedEmp(Employee modifiedEmp) {
        this.modifiedEmp = modifiedEmp;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", text='" + text + '\'' +
                ", createdDate=" + createdDate +
                ", createdEmp=" + createdEmp +
                ", status=" + status +
                ", modifiedDate=" + modifiedDate +
                ", modifiedEmp=" + modifiedEmp +
                '}';
    }
}
