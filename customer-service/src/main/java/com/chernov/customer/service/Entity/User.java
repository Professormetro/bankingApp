package com.chernov.customer.service.Entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "other_name")
    private String otherName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "address")
    private String address;

    @Column(name = "state_of_origin")
    private String stateOfOrigin;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "alternative_phone_number")
    private String alternativePhoneNumber;

    @Column(name = "user_status")
    private String status;

    @Column(name = "user_created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "user_modified_at")
    @UpdateTimestamp
    private LocalDateTime modifiedAt;

}
