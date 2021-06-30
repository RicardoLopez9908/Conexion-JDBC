# Conexion-JDBC

#### Utilizamos una interfaz de conexión que permita gestionar la comunicación y las acciones que se desea realizar en la base de datos en particular (en este caso utilizamos JDBC para manejar una base de datos MySQL).

- Creamos un nuevo proyecto Java en nuestro entorno de desarrollo (ej: Eclipse).
- Creamos una carpeta (folder) dentro del proyecto que contenga nuestro Driver ya descargado.

🗒️ _Nota: En mi caso, busqué en la pagina de MySQL el conector necesario para la versión que tengo descargada (mysql-connector-java-8.0.25.jar)._
- Una vez que ubicamos nuestro driver en la carpeta, nos dirigimos a las configuraciones del proyecto y seleccionamos: Build Path > Configure Build Path ... > Libraries > add JARs...  Y finalmente, luego de seleccionar nuestro driver, Apply and Close.

_Esto le permitira al proyecto poder acceder a las clases que contiene el archivo .jar_

ALGUNAS POSIBLES INTERACCIONES VISTAS DESDE UN DIAGRAMA:

<img src="https://github.com/RicardoLopez9908/Conexion-JDBC/blob/master/Interacciones%20JDBC.png" width="500">

