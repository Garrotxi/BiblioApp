package com.biblioapp.core.security.entity;

import com.biblioapp.core.security.enums.RolNom;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "rols")
public class Rol {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private RolNom rolNom;

    public Rol(){

    }

    public Rol(@NotNull RolNom rolNom){
        this.rolNom = rolNom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RolNom getRolNom() {
        return rolNom;
    }

    public void setRolNom(RolNom rolNom) {
        this.rolNom = rolNom;
    }
}
