# AlkeWalletM5
Encargo:
desarrollar una billetera virtual, conocida como "Alke Wallet" la cual permita a sus usuarios realizar pagos virtuales.
El proyecto debe estar implementado bajo el modelo MVVM
Primero, como requerimiento general tenemos que poder implementar la lógica de comunicación entre view y view model:
1. Definición de Interfaces:
a. Crear una interfaz para la capa View que describa los métodos ypropieda des que el View Model utilizará para comunicarse con la Vista.

b. Diseñar una interfaz para el View Model que declare los métodos y propiedades que estarán disponibles para la Vista. Estos deben estar
orientados a proporcionar los datos y acciones requeridas por la Vista para su correcto funcionamiento.

2. Implementación de Data Binding:
Establecer un mecanismo de data binding para permitir la actualización automática de la Vista cuando los datos en el View Model cambien y
viceversa. Utilizar el patrón Observer/Observable o tecnologías equivalentes para lograr esta sincronización.

3. Uso de Eventos:
Implementar eventos o mecanismos de notificación para informar a la Vista sobre eventos importantes o cambios en el View Model. Asegurarse
de que la Vista pueda suscribirse y manejar adecuadamente estos eventos para actualizar su interfaz gráfica o realizar acciones correspondientes.

4. Manejo de Comandos:
Establecer una estructura para el manejo de comandos desde la Vista al View Model. Definir y mapear los comandos que la Vista puede enviar al View Model para activar acciones específicas.

5. Validación de Datos:
Implementar mecanismos de validación de datos en el View Model para asegurar que los datos ingresados por el usuario desde la Vista sean
coherentes y cumplan con los criterios requeridos.

7. Documentación:
Proporcionar una documentación detallada de la arquitectura de comunicación entre View y View Model, explicando el propósito y
funcionamiento de cada componente, método y propiedad involucrada, deben ir en idioma español todos los comentarios.
