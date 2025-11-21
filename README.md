# Proyecto: Consumo del API de Tipo de Cambio del Banco de Guatemala
ste proyecto consiste en una aplicación web que consulta el tipo de cambio del Banco de Guatemala, lo muestra en una interfaz realizada con Next.js y lo guarda en una base de datos mariadb utilizando un backend desarrollado en Spring Boot.

### Prerrequisitos
- Java 17+
- Maven
- Node.js y npm
- MariaDB instalado (administrado con DBeaver)
- Editor o IDE (IntelliJ, VSCode, etc.)

## Configurar la base de datos
1. Abre el archivo application.properties el cual esta ubicado en "Backend\src\main\resources\application.properties"
Encontraras el siguiente codigo:
##
spring.application.name=Backend

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/monedadb
spring.datasource.username=root
spring.datasource.password=12345
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

management.endpoints.web.exposure.include=*

##Ingresa los datos de tu base de datos que usaras en username y password abajo de dejo nuevamente el codigo de referencia de como quedaria

spring.application.name=Backend

server.port=8080

spring.datasource.url=jdbc:mysql://localhost:3306/monedadb
spring.datasource.username=tuusuario
spring.datasource.password=tucontraseña
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

management.endpoints.web.exposure.include=*

