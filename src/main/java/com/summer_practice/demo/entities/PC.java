package com.summer_practice.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "pc")
public class PC {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "pc_id")
    private Long pcId;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "length")
    private int length;
    @Column(name = "height")
    private int height;
    @Column(name = "width")
    private int width;
    @Column(name = "hddsize")
    private int hddSize;
    @Column(name = "cpucount")
    private int cpuCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "w_place_id", nullable = false)
    @JsonIgnore
    private WorkPlace wPlacePc;

    public Long getPcId() {
        return pcId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public WorkPlace getWplace() {
        return wPlacePc;
    }

    public void setWplace(WorkPlace w_place) {
        this.wPlacePc = w_place;
    }
}