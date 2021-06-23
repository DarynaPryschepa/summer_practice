package com.summer_practice.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "monitor")
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mon_id")
    private Long monId;
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
    @Column(name = "vesa")
    private String vesa;
    @Column(name = "display_size")
    private String displaySize;
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = WorkPlace.class)
    @JoinColumn(name = "w_place_id", nullable = false)
    private WorkPlace wPlace;

    public Long getMonId() {
        return monId;
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

    public String getVesa() {
        return vesa;
    }

    public void setVesa(String vesa) {
        this.vesa = vesa;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String display_size) {
        this.displaySize = display_size;
    }

    public WorkPlace getwPlace() {
        return wPlace;
    }

    public void setwPlace(WorkPlace wPlace) {
        this.wPlace = wPlace;
    }
}
