# BiblioApp
Projecte IOC - BiblioApp 
## BASE DE DATOS

### MONTAR COTAINER
 
1. Navegar con la linia de comandos (puede ser powershell o CMD) hasta el directorio donde esta el fichero docker-compose.yml
2. Ejecutar el comando `docker-compose up -d` donde -d es para simbolizar que se ejecute en segundo plano
3. Comprobar que el contenedor esta inciado con el comando `docker ps`
4. Cargar el DUMP de la base de datos si es necesario usando los pasos descritos en [CARGAR DUMP DE LA BASE DE DATOS CON DOCKER]
  
### ACCESO A LA BASE DE DATOS
Con el programa de vuestra preferencia (MySQL Workbench, DBeaver, etc..) se podra acceder con los siguientes datos de conexión:
* Hostname: `127.0.0.1`
* Port: `3306`
* Username: `root`
* Password: `password`
### CREAR DUMP DE LA BASE DE DATOS CON DOCKER

Crear manualmente el dump de la base de datos desde MySQL Workbench usando la herramienta `Data Exmport` con las siguientes opciones:
1. En el apartado `Objects to export` marcar las opciones `Dump Stored Procedures and Functions`, `Dump Events`, `Dump Triggers`
2. En el apartado `Export Options` usar la opción `Export to Self-Contained File` para que se exporte en un solo fichero
3. Marcar las opciones `Create Dump in a Single Transaction` y `Include Create Schema`
  
### CARGAR DUMP DE LA BASE DE DATOS CON DOCKER


Cargar manualmente el dump de la base de datos desde MySQL Workbench usando la herramienta `Data Import/Restore` disponible en el apartado `Management`


