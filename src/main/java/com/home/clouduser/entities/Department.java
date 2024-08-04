package com.home.clouduser.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "tb_department") // to be pojo class of Hibernate
@Data
@Table(name = "tb_department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

}
