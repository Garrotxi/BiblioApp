package com.biblioapp.core.security.service;

import com.biblioapp.core.security.entity.Usuari;
import com.biblioapp.core.security.entity.UsuariMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
/**
 * @Author: Lluis Antoni Roigé Higueras
 * Clase que converteix la clase usuari en un UsuariMain.
 * UserDetailsService es propia de Spring Security
 */
@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UsuariService usuariService;


    @Override
    public UserDetails loadUserByUsername(String nomUsuari) throws UsernameNotFoundException {
        Usuari usuari = usuariService.getByUsuari(nomUsuari).get();
        return UsuariMain.build(usuari);
    }
}
