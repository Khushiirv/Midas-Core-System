package com.jpmc.midascore.data.model;

import jakarta.persistence.*;

@Table(name="users")
@Entity
public class CustomerRecord {

    @Id
    @GeneratedValue()
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private float balance;

    protected CustomerRecord() {
    }

    public CustomerRecord(String name, float balance) {
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, name='%s', balance='%f'", id, name, balance);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}