package com.summer_practice.demo.entities;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="monitor")
public class Monitor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
private  Long id;
  @Column(name = "created_at")
private Timestamp created_at;
    @Column(name = "created_by")
    private String created_by;

}
