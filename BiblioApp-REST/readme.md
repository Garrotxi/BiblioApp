# BiblioApp-REST

##SETUP INICIAL

###1. Configurar docker i entorn BBDD

- Iniciar amb docker el fitxer `docker-compose.yml` ubicat en la carpeta ..\Docker
- Executar comanda `docker-compose up -d`
- Comprovar que el contenidor esta iniciat amb la comanda `docker ps`
- Configurar programa gestor de BBDD amb els següents parametres de configuració:
  * Hostname: `127.0.0.1`
  * Port: `3306`
  * Username: `root`
  * Password: `password`
- Carregar el DUMP de la base de dades anomenat `biblioapp.sql`

###2. Creació de certificats autosignats per a la seguretat HTTPS

Per a crear els certificats autosignats, utilitzarem l'eina que ens proporciona el SDK de Java anomenada keytools:

Iniciar la línia de comandes en mode administrador
- Navegar amb la línia de comandes fins al directori on vulguem que es guardin els certificats
- Generar un certificat de client:
  - `keytool -genkeypair -alias client -keyalg RSA -validity 3650 -keypass 123456 -storepass 123456 -keystore local.jks`
- Generar un magatzem de claus del servidor amb la següent comanda:
  - `keytool -genkeypair -alias server -keyalg RSA -validity 3650 -keypass 123456 -storepass 123456 -keystore server.keystore`
- Exportar el certificat de client:
  - `keytool -export -alias client -file local.cer -keystore local.jks -storepass 123456`
- Exportar el certificat del servidor amb la següent comanda:
  - `keytool -export -alias server -file server.cer -keystore server.keystore -storepass 123456`
- Generar el magatzem de certificats de confiança de client
  - `keytool -import -v -alias server -file server.cer -keystore truststorelist.jks -storepass 123456`
- Importar el certificat de client en el magatzem de certificats del servidor:
  - `keytool -import -v -alias client -file local.cer -keystore server.keystore -storepass 123456`


###3. Primer inici del projecte BiblioApp-REST

- Descomentar el contingut del programa java ubicat en `com/biblioapp/core/util/CreateRoles.java`
- Executar el projecte des de la classe `BiblioAppRestApplication.java`

###4. Jocs de probes en postman

- Importar els jocs de proves de postman `Tests biblioapp.postman_collection.json`




