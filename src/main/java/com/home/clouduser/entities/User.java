package com.home.clouduser.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity // to be pojo class of Hibernate
@Data
@Table(name = "tb_user")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="address")
    private String address;

    @CreatedDate
    @JsonIgnore
    private Date createDate;

    @LastModifiedDate
    @JsonIgnore
    private Date lastModifiedDate;

    @CreatedBy
    @JsonIgnore
    private String createdBy;

    @LastModifiedBy
    @JsonIgnore
    private String lastModifiedBy;


}