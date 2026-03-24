# Taller Transversal I – Práctica 3: TDD en Java 

Proyecto de ejemplo para la practica 3 de la asignatura **Taller Transversal I: Programación y Proceso de Información** de la Universidad de La Rioja (curso 25/26).

## Autor

*Santiago Die Morejón* – Universidad de La Rioja

## Descripción del Proyecto

Este proyecto implementa un sistema de gestión de tareas siguiendo la metodología **Test-Driven Development (TDD)**

Sistema de gestión de tareas que permite:
- Crear y gestionar tareas con fechas límite
- Marcar tareas como completadas
- Listar tareas pendientes
- Registrar direcciones de email para notificaciones
- Enviar alertas automáticas por email cuando las tareas vencen

El proyecto busca usar la metodología TDD para :
- **Tests unitarios**: Para componentes individuales (ToDo, Repositorio, Servicio)
- **Stubs**: Implementaciones simplificadas para pruebas (DBStub, MailerStub)
- **Mocks**: Objetos simulados para verificar interacciones
- **Tests de integración**: Pruebas de flujos completos del sistema

## Requisitos Previos

- Java 21 
- Maven 3.6+
- Un IDE compatible con Java

### Ejemplo de Uso del Servicio

```java
// Crear dependencias
IDB db = new DBStub();
IRepositorio repositorio = new Repositorio(db);
IMailer mailer = new MailerStub();

// Crear servicio
Servicio servicio = new Servicio(repositorio, mailer);

// Agregar email para notificaciones
servicio.agregarEmail("usuario@example.com");

// Crear tarea
servicio.crearTarea("Completar práctica", LocalDate.now().plusDays(7));

// Marcar como completada
servicio.marcarComoCompletada("Completar práctica");

// Listar tareas pendientes
List<ToDo> pendientes = servicio.listarPendientes();
```

## Estructura del Proyecto

```
tdd-en-proyectos-de-java-sadie27/
│
├── src/
│   ├── main/
│   │   ├── java/com/tt1/test/
│   │   │   ├── App.java                    # Clase principal
│   │   │   ├── ToDo.java                   
│   │   │   ├── Servicio.java               # Lógica de negocio principal
│   │   │   ├── Repositorio.java            
│   │   │   ├── IRepositorio.java           # Interfaz del repositorio
│   │   │   ├── IDB.java                    # Interfaz de base de datos
│   │   │   ├── DBStub.java                 # Stub de la BD
│   │   │   ├── IMailer.java                # Interfaz de servicio de email
│   │   │   └── MailerStub.java             # Stub de mailer
│   │   └── resources/
│   │       └── application.properties       # Configuración de la aplicación
│   │
│   └── test/
│       └── java/com/tt1/test/
│           ├── mock/
│           │   ├── MockDB.java              # Mock de DB
│           │   ├── MockMailer.java          # Mock de Mailer
│           │   └── MockRepositorio.java     # Mock de Repositorio
│           │
│           ├── ToDoTest.java                # Tests de ToDo
│           ├── RepositorioTest.java         # Tests del repositorio
│           ├── ServicioTest.java            # Tests unitarios del servicio
│           ├── ServicioIntegrationTest.java # Tests de integración
│           ├── DBStubTest.java              # Tests del stub de DB
│           ├── MailerStubTest.java          # Tests del stub de Mailer
│           └── Test_Apartado5.java
│
├── pom.xml                                  # Configuración de Maven
├── LICENSE                                  # Licencia del proyecto
└── README.md                                
```

## Metodología TDD

Este proyecto sigue los principios de TDD:

1. **Red**: Escribir un test que falle
2. **Green**: Escribir el código mínimo para que pase
3. **Refactor**: Mejorar el código manteniendo los tests en verde

### Tipos de Tests Implementados

1. **Tests Unitarios**: Prueban componentes individuales de forma aislada
   - `ToDoTest`: Valida la clase ToDo
   - `RepositorioTest`: Verifica los metodos del repositorio
   - `ServicioTest`: Prueba la lógica de negocio con mocks

2. **Tests con Stubs**: Usan implementaciones simplificadas de dependencias
   - `DBStubTest`: Prueba el stub de DB
   - `MailerStubTest`: Prueba el stub de email

3. **Tests de Integración**: Verifican el funcionamiento conjunto de componentes
   - `ServicioIntegrationTest`

## Tecnologías Utilizadas

- **Java 21**: Lenguaje de programación
- **Maven**: Gestión de dependencias y construcción
- **JUnit 5**: Framework de testing

## Licencia

Este proyecto está bajo la licencia especificada en el archivo LICENSE.
