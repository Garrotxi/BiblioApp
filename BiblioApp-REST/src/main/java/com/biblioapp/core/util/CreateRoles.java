package com.biblioapp.core.util;
/**
 * @Author: Lluis Antoni Roigé Higueras
 * Aquesta classe s'ha de descomentar el primer cop que s'executa l'aplicacio sense importar la BBDD per a que afegeixi els rols.
 * En cas de iniciar l'aplicacio per primer cop amb una bbdd ja existent amb els rols ja implementats.
 */

/*
@Component
public class CreateRoles implements CommandLineRunner {
    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        Rol rolAdmin = new Rol(RolNom.ROLE_ADMIN);
        Rol rolUser = new Rol(RolNom.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);
    }
}
*/