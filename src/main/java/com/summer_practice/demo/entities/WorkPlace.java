package com.summer_practice.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "working_place")
public class WorkPlace {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "wp_id")
    private Long wplaceId;
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
    @Column(name = "updated_by")
    private String updatedBy;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "wPlace", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Monitor> monitors;
    @OneToMany(mappedBy = "wPlacePc", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PC> pcs;

    public Long getId() {
        return wplaceId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Monitor> getMonitors() {
        return monitors;
    }

    public void setMonitors(Set<Monitor> monitors) {
        this.monitors = monitors;
    }

    public Set<PC> getPcs() {
        return pcs;
    }

    public void setPcs(Set<PC> pcs) {
        this.pcs = pcs;
    }
}
