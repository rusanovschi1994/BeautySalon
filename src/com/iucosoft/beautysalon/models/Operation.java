package com.iucosoft.beautysalon.models;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Objects;

/**
 *
 * @author Rusanovschi
 */
public class Operation {

    private int id;
    private LocalDate localDate;
    private Customer customer;
    private User user;
    private OperationStatus operationStatus;
    private OperationType operationType;
    
    

    public Operation() {
    }

    public Operation(int id, LocalDate localDate, Customer customer, User user, OperationStatus operationStatus, OperationType operationType) {
        this.id = id;
        this.localDate = localDate;
        this.customer = customer;
        this.user = user;
        this.operationStatus = operationStatus;
        this.operationType = operationType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.localDate);
        hash = 89 * hash + Objects.hashCode(this.customer);
        hash = 89 * hash + Objects.hashCode(this.user);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Operation other = (Operation) obj;
        if (!Objects.equals(this.localDate, other.localDate)) {
            return false;
        }
        if (!Objects.equals(this.customer, other.customer)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return customer.getName();
    }
}