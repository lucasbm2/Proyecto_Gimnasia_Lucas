# GIMNASIAPP

GIMNASIAPP es una aplicación desarrollada para el cálculo del IMC y el seguimiento de marcas deportivas. Su objetivo es el control de gestión personal para evaluar la salud de los usuarios.

## Funcionalidades

- Registro e identificación de usuarios.
- Recuperación de contraseña.
- Cálculo y almacenamiento del IMC y marcas deportivas.
- Gestión de usuarios y contraseñas.
- Navegación entre pantallas.

## Estructura del Proyecto

**Paquete principal: com.example.gimnasiapp**

### Navegación y pantalla principal
- `PantallaInicial`: Pantalla principal de la aplicación.
- `PantallaLogin`: Permite el registro e inicio de sesión de usuarios.
- `PantallaContrasena`: Opción para recuperar o cambiar la contraseña.
- `PantallaMarcas`: Funcionalidad para el cálculo y almacenamiento de marcas deportivas.
- `NavigationWrapper` y `Screens`: Gestión de la navegación entre pantallas.

### Base de Datos
- `database`
  - `BD`: Controlador general de la base de datos.
  - `DatosDBHelper`: Manejo de datos relacionados con marcas deportivas.
  - `LoginDBHelper`: Gestión de credenciales y autenticación de usuarios.
  - `EntLogin`: Entidad de usuario utilizada en el sistema de autenticación.
