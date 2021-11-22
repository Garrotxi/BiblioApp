package com.biblioapp.core.security.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Lluis Antoni Roigé Higueras
 * Classe encarregada de generar la seguretat i implementa els privilegis
 * de cada usuari.
 * UserDetails es una clase propia de Spring Security
 */
public class UsuariMain implements UserDetails {
    private String nomUsuari;
    private String contrasenya;
    private String email;
    private String nom;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuariMain(String nomUsuari, String contrasenya, String email, String nom,
                      Collection<? extends GrantedAuthority> authorities) {
        this.nomUsuari = nomUsuari;
        this.contrasenya = contrasenya;
        this.email = email;
        this.nom = nom;
        this.authorities = authorities;
    }

    /*
     * Metode que asigna els privilegis
     */

    public static UsuariMain build(Usuari usuari){
        //Convertim la clase Rol a la clase GrantedAuthority
        List<GrantedAuthority> authorities =
                usuari.getRols()
                        .stream()
                        .map(rol -> new SimpleGrantedAuthority(rol.getRolNom().name()))
                        .collect(Collectors.toList());
        return new UsuariMain(usuari.getNomUsuari(), usuari.getContrasenya(), usuari.getEmail(), usuari.getNom(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasenya;
    }

    @Override
    public String getUsername() {
        return nomUsuari;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }
}
