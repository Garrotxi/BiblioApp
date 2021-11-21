package com.biblioapp.core.security.service;

import com.biblioapp.core.security.entity.Rol;
import com.biblioapp.core.security.enums.RolNom;
import com.biblioapp.core.security.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
/**
 * @Author: Lluis Antoni Roigé Higueras
 */

@Service
@Transactional
public class RolService {
    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNom(RolNom rolNom){
        return rolRepository.findByRolNom(rolNom);
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
