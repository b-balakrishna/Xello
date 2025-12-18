package com.example.demo.entities;

public class User {
    private final Long id;
    private final String email;
    private String password;

    public User(String email, String password) {
        this.id = null;
        isValidEmail(email);
        this.email = email;
        setPassword(password);
    }

    public User(Long id, User other) {
        this.id = id;
        this.email = other.email;
        this.password = other.password;
    }

    public Long getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        isValidPassword(password);
        this.password = password;
    }

    private void isValidPassword(String password) {
        // Implement your password validation logic here
        if (password.length() < 8) {
            throw new IllegalArgumentException("Password must have a minimum length of 8 characters");
        }
    }

    private void isValidEmail(String email) {
        // Implement your email validation logic here
        if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")){
            throw new IllegalArgumentException("Invalid email format");
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User [id=" + id + "]";
    }
}
