package com.example.casemodul4.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User User;

    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;

    @Column(name = "delivery_address", nullable = false)
    private String deliveryAddress;

    @Column(name = "status", columnDefinition = "VARCHAR(255) DEFAULT 'started'", nullable = false)
    private String status;

    @Column(name = "total_price", nullable = false)
    private Float totalPrice;

}
