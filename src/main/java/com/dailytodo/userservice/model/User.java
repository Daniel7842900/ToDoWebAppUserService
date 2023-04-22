package com.dailytodo.userservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="\"User\"")
@Getter
@Setter
@Accessors(chain = true)
//@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name", length = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String firstName;

    @Column(name = "last_name", length = 20)
    @Size(min = 3, max = 20)
    @NotBlank
    private String lastName;

    @Email
    @Size(max = 100)
    @NotBlank
    private String email;

    @Column(length = 100)
    @NotBlank
    private String password;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    @UpdateTimestamp
    private Date updatedAt;

    public User() {}

    public User(long id, String firstName, String lastName, String email, String password, Date createdAt, Date updatedAt) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
