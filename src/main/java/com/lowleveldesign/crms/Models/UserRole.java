package com.lowleveldesign.crms.Models;

import com.lowleveldesign.crms.Utilities.Role;
import jakarta.persistence.*;

@Entity
@Table(
        name="roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id", "role"})
        })
public class UserRole {
    @Id
    private Integer id;
    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private Role role;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
