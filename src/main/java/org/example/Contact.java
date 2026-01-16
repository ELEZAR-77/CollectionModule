package org.example;

import java.util.Objects;

public class Contact {
    private static Long idCounter = 0L;
    private Long id;
    private final String name;
    private final String phone;
    private final String email;
    private final String group;

    public Contact(String name, String phone, String email, String group) {
        idCounter++;
        this.id = idCounter;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || this.getClass() != object.getClass()) return false;

        Contact other = (Contact) object;

        return Objects.equals(name, other.name)
                && Objects.equals(phone, other.phone)
                && Objects.equals(email, other.email)
                && Objects.equals(group, other.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPhone(), getEmail(), getGroup());
    }


    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}
