package com.biblioapp.core.security.service;

import com.biblioapp.core.security.entity.Usuari;
import com.biblioapp.core.security.repository.UsuariRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @Author: Lluis Antoni Roigé Higueras
 * Implementacio dels metodes de UsuariService
 */

@Service
@Transactional
public class UsuariService {

    @Autowired
    UsuariRepository usuariRepository;

    public Optional<Usuari> getByUsuari(String nomUsuari) {
        return usuariRepository.findByNomUsuari(nomUsuari);
    }

    public Optional<Usuari> getUsuari(int idUsuari) {
        return usuariRepository.findById(idUsuari);
    }

    public Boolean existsByUsuari(String nomUsuari){
        return usuariRepository.existsByNomUsuari(nomUsuari);
    }

    public Boolean existsByEmail(String email){
        return usuariRepository.existsByEmail(email);
    }

    public boolean existsByIdUsuari (int idUsuari) {
        return usuariRepository.existsById(idUsuari);
    }

    public void save(Usuari usuari){
        usuariRepository.save(usuari);
    }

    public List<Usuari> llistaUsuaris(){
        return  usuariRepository.findAll();
    }

    public void update(Usuari usuari){
        usuariRepository.save(usuari);
    }

    public void delete(int idUsuari) {
        usuariRepository.deleteById(idUsuari);
    }

}