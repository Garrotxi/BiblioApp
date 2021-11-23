# BiblioApp-REST

##SETUP INICIAL

###1. Configurar docker i entorn BBDD

- Iniciar amb docker el fitxer `docker-compose.yml` ubicat en la carpeta ..\Docker
- Executar comanda `docker-compose up -d`
- Comprobar que el contenidor esta iniciat amb la comanda `docker ps`
- Configurar programa gestor de BBDD amb els seguents parametres de configuració:
  * Hostname: `127.0.0.1`
  * Port: `3306`
  * Username: `root`
  * Password: `password`
- Carregar el DUMP de la base de dades anomenat `biblioapp.sql`

###2. Primer inici del projecte BiblioApp-REST

- Descomentar el contingut del programa java ubicat en `com/biblioapp/core/util/CreateRoles.java`
- Executar el projecte desde la clase `BiblioAppRestApplication.java`

###3. Jocs de probes en postman

- Importar els jocs de probes de postman `Tests biblioapp.postman_collection.json`




