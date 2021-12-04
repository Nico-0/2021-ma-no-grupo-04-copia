# 2021-ma-no-grupo-04
# Rescate de patitas

![Patitas](patitas.jpg)

Nombre del grupo: The Coders

Integrantes:
Nicolás Androchuk - nicoandrochuk@frba.utn.edu.ar,
Erik Cori - erik.corip@gmail.com,


Links:
[Enunciado](https://docs.google.com/document/d/1Kc5iDjIq0qRyEQyDOdPtLaGLbQxCcc7c)
[Entrega 4](https://docs.google.com/document/d/1RRMmQ4Z5N-rN1cVJI9qn1JCaVDjuhFoZtxxhCd9kpnw)
[Entrega 5](https://docs.google.com/document/d/1GJosDgft_2aVpEPoPzStD5JQrEvkfnL15Q6jyAy-lj8)
[Entrega 6](https://docs.google.com/presentation/d/1C7lxzY4xC-HG78RR9syIOBo6yR7-M4tYq4mwLQ4wRTc)


-----Diagrama de clases- Diagrama Entidad Relacion- Diagrama de Despliegue ------
https://lucid.app/lucidchart/0642de96-96b6-4142-80f7-df26273773e9/edit?invitationId=inv_b2b94db4-6b0c-44d3-843d-79d2f43a2e74



> :warning: Por contar con menos integrantes, no se realizan las pantallas de alta. Hay datos cargados previamente, y botones para hacer aparecer mascotas y publicaciones con datos genéricos.



## Instalación
### Para correr local o subir a Heroku

- Configurar las credenciales de MongoDB en `src\main\java\com\dds\rescate\service\MongoDB.java`
- Configurar las credenciales de una base relacional en `src\main\resources\META-INF\persistence.xml`

## Cargar los datos
### Local

- Ejecutar la clase main `src\main\java\com\dds\rescate\server\jobs\CrearDatosBase.java`
- Ejecutar el server `src\main\java\com\dds\rescate\server\Server.java` y entrar a `localhost:9000`
- Iniciar sesión con usuario `adminPatitas` y contraseña `adminPatitas`
- Entrar al menú Gestión Organización y darle al botón Crear datos extra.

### Heroku
- Deployar en Heroku.
- Ejecutar el comando `heroku run sh target/bin/crear -a appname`
- Iniciar sesión con usuario `adminPatitas` y contraseña `adminPatitas`
- Entrar al menú Gestión Organización y darle al botón Crear datos extra.


## Funcionamiento

### Muro de Publicaciones
-  Se pueden ver las publicaciones en adopción y rescatadas, filtrar y pasar de página.

### Adoptar una mascota
- Iniciar sesión con el Usuario1, usuario y contraseña  `Usuario1`
- Buscar en el muro, una publicación en Adopción y otra Perdida, del Usuario2 (Peter Sobrino).
- Entrar a las publicaciones e intentar adoptar cada mascota.
- Iniciar sesión con el Usuario2, usuario y contraseña  `Usuario2`
- Las mascotas elegidas deberían estar en la sección Mis Mascotas, como mascotas comprometidas. Y sus publicaciones correspondientes en la sección Mis Publicaciones. (Del Usuario2)
- Entrar a la sección Confirmaciones.
- Puedo aceptar al interesado en una publicación y la mascota debería pasar al inventario del otro usuario (la publicación queda finalizada). Puedo rechazar al interesado en la segunda publicación y esta debería quedar disponible, para que otro usuario se interese por ella.

### Perder y recuperar una mascota
- Iniciar sesión con el Usuario1, usuario y contraseña  `Usuario1`
- Entrar a la sección mis mascotas y elegir una para que se me pierda sin querer. Si no hay mascotas disponibles, darle al botón Registrar nueva mascota, o entrar a la sección mis publicaciones y cancelar alguna publicación (para que la mascota deje de estar comprometida).
- Perder la mascota (quedará marcada como perdida) y tomar nota del identificador de su chapita.
- Puedo cambiar al Usuario2 o quedarme en el mismo, depende quién tenga la suerte de encontrar la mascota.
- Entrar a la sección Mis Publicaciones e insertar la chapita asociada la mascota encontrada.
- Volver al usuario dueño de la mascota, y entrar a la sección Confirmaciones.
- Puedo aceptar la mascota para recuperarla, o rechazarla si creo que se trató de un error y me están ofreciendo una mascota que no es.

### Crear publicaciones
- Puedo dar en adopción una mascota de mi lista de mascotas.
- Puedo crear una publicación de mascota perdida en la sección mis publicaciones, si encontré una mascota perdida y no tenía chapita.
- Un voluntario de la asociación registrada en la publicación debe aprobar la publicación antes de que pueda aparecer en el muro de publicaciones.
- Iniciar sesión con el voluntario de la asociación Patitas al Rescate, usuario y contraseña  `volPatitas`
- Entrar a la sección Aprobar Publicaciones. Puedo aprobar las publicaciones de la asociación correspondiente, para que aparezcan en el muro, o rechazarlas para dejar sin efecto.

### Recomendaciones de mascotas para adoptar
- Un usuario puede recibir recomendaciones de mascotas para adoptar, según sus preferencias de características de mascotas, y según las comodidades que él pueda ofrecer.
- Se recomiendan las publicaciones de mascotas en adopción mas óptimas, según su puntaje. Este se determina de forma automática en base a las características de la mascota publicada, y en base a las preguntas que responda quien ofrece la mascota.
- Primero debe generar su intención de adopción, en la sección Mis Publicaciones puede generar una intención adicional.
- En la sección Recomendaciones se pueden visualizar. Darle al botón Recomendar.
- Para cada intención de adopción va a salir el puntaje de todas las publicaciones de adopción disponibles. Para ver mas detalles pasar el mouse sobre el puntaje.
- En la tabla SQL `respuesta` se pueden editar manualmente los valores de las características para ver distintos resultados. Otra opción es probar primero solo con los datos base y luego cargar los datos extra.

### Recomendaciones periódicas
- Para comodidad del usuario se pueden ejecutar las recomendaciones de forma periódica y estar al tanto si aparece una publicación con mayor puntaje para él.
- Añadir el plugin Heroku Scheduler y setearle el comando `heroku run sh target/bin/recomendar -a nombreApp`
- Los usuarios que lo deseen pueden consultar sus últimas recomendaciones de forma externa con un GET a la ruta `/usuarios/{{idUsuario}}/recomendaciones`
- Se puede consultar el historial de las recomendaciones anteriores, en la base MongoDB.
