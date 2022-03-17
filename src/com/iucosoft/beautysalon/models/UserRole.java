package com.iucosoft.beautysalon.models;

/**
 *
 * @author Rusanovschi
 */
public enum UserRole {

    ADMINISTRATOR("Administrator", 1), MANAGER("Manager", 2), OPERATOR("Operator", 3);

    private UserRole(String role, int id) {
        this.role = role;
        this.id = id;
    }

    private String role;
    private int id;

    public String getRole() {
        return role;
    }

    public int getId() {
        return id;
    }
    
    

}
