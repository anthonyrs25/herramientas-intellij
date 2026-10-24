# herramientas-intellij

API REST de productos con **Kotlin**, **Spring Boot 3.5** y **PostgreSQL**.
Materia: Herramientas Informáticas para el Despliegue de Diagramas — Instituto Superior Tecnológico Sudamericano.

## Tecnologías

- Kotlin 2.0.21 · JDK 21 · Spring Boot 3.5.16 · Maven
- PostgreSQL · Spring Data JPA · Flyway · Bean Validation

## Estructura

```
src/main/kotlin/suda/com/herramientasintellij/
├── controller/   # Recibe las peticiones HTTP
├── service/      # Lógica de negocio
├── repository/   # Acceso a la base de datos (JpaRepository)
└── model/        # Entidades (tablas)
src/main/resources/
├── application.yaml
└── db/migration/ # Migraciones de Flyway (V1, V2, ...)
```

## Cómo ejecutarlo

1. Crear la base de datos:
   ```
   psql -U postgres -c "CREATE DATABASE herramientasintellij;"
   ```
2. Definir la variable de entorno `DB_PASSWORD` con la contraseña de PostgreSQL
   (en IntelliJ: Run Configuration → Environment variables).
3. Ejecutar `HerramientasIntellijApplication`. Flyway crea la tabla y carga 3 productos de prueba.
4. La API queda en `http://localhost:8081`.

## Endpoints

| Método | Ruta | Descripción | Respuesta |
|---|---|---|---|
| GET | `/api/products/hola` | Saludo | 200 |
| GET | `/api/products/adios` | Despedida | 200 |
| GET | `/api/products/saludo-service` | Saludo desde el servicio | 200 |
| GET | `/api/products` | Listar productos | 200 |
| GET | `/api/products/{id}` | Buscar por id | 200 / 404 |
| POST | `/api/products` | Crear producto | 201 / 400 |
| PUT | `/api/products/{id}` | Actualizar producto | 200 / 400 / 404 |
| DELETE | `/api/products/{id}` | Eliminar producto | 204 / 404 |

Ejemplo de cuerpo JSON:

```json
{ "description": "Refrigerante", "price": 12.50, "stock": 20 }
```

## Declaración de uso de IA

Se utilizó Claude (Anthropic) como asistente para guiar la creación del proyecto paso a paso:
configuración de versiones, estructura en capas, migraciones, código de las clases y revisión de errores.
El proyecto fue construido, ejecutado y probado por el estudiante en su equipo.