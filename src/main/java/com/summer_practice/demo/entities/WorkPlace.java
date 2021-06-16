package com.summer_practice.demo.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name="working_place")
public class WorkPlace {

   private int id;
    @Column(name = "created_at")
    private Timestamp created_at;
    @Column(name = "created_by")
    private String created_by;

    @Column(name = "updated_at")
    private Timestamp updated_at;
    @Column(name = "updated_by")
    private String updated_by;
    @Column(name = "name")
   private String name;
    @Column(name = "city")
    private String city;
 @OneToMany(mappedBy="monitor")
 private Set<Monitor> monitors;
 @OneToMany(mappedBy="pc")
 private Set<PC> pcs;

 public int getId() {
  return id;
 }

 public void setId(int id) {
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
