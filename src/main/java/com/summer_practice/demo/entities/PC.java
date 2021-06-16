package com.summer_practice.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private  Long id;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "created_by")
    private String created_by;
    @Column(name = "updated_at")
    private Timestamp updated_at;
    @Column(name = "updated_by")
    private String updated_by;
    @Column(name = "length")
    private  int length;
    @Column(name = "height")
    private  int height;
    @Column(name = "width")
    private  int width;
    private  int hddSize;
    private int cpuCount;

    @ManyToOne
    @JoinColumn(name="w_place_id", nullable=false)
    private WorkPlace w_place;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getUpdated_by() {
        return updated_by;
    }

    public void setUpdated_by(String updated_by) {
        this.updated_by = updated_by;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHddSize() {
        return hddSize;
    }

    public void setHddSize(int hddSize) {
        this.hddSize = hddSize;
    }

    public int getCpuCount() {
        return cpuCount;
    }

    public void setCpuCount(int cpuCount) {
        this.cpuCount = cpuCount;
    }

    public WorkPlace getW_place() {
        return w_place;
    }

    public void setW_place(WorkPlace w_place) {
        this.w_place = w_place;
    }
}
