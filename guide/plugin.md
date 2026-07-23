# Guía para la Creación e Integración de Plugins en JettraFlux

Los plugins en JettraFlux permiten extender la funcionalidad de un proyecto de nivel superior (como JettraFluxCenter) de manera modular. Cada plugin es un proyecto Java Maven independiente que puede contener sus propias páginas, componentes y recursos.

## 1. Crear un Plugin JettraFlux

Para crear un nuevo plugin, debes seguir estas pautas:

### Estructura del Proyecto
- **Proyecto Maven**: El plugin debe ser un proyecto Maven que empaquete un archivo `.jar`.
- **Dependencias**: Debe incluir las dependencias de JettraFlux y JettraServer. Generalmente **no** debe depender del proyecto de nivel superior para evitar dependencias circulares, al menos que el proyecto exponga una API separada.
- **Clases Base**: Las páginas (`@Page`) y widgets del plugin deben extender de las interfaces o clases bases proporcionadas por `io.jettra.flux.pages.FluxBaseHandler` o tener sus propias implementaciones autónomas (por ejemplo, su propio `TemplatePage`).

### Archivos de Propiedades
Para evitar colisiones con los archivos de propiedades del proyecto principal o de otros plugins, los archivos de propiedades del plugin deben tener el prefijo `plugin-`.

**Ejemplo:**
- `plugin-example_en.properties`
- `plugin-example_es.properties`

### Archivo de Manifiesto (`plugin-manifiest.md`)
Cada plugin debe incluir un archivo de manifiesto (comúnmente llamado `plugin-manifiest.md` o similar) documentando sus detalles:
- Nombre, Versión, Group ID, Artifact ID.
- Propósito del plugin.
- Instrucciones de integración para incluirlo en un proyecto principal.

## 2. Integrar un Plugin en un Proyecto

Una vez que el plugin esté desarrollado y construido (`mvn clean install`), puedes integrarlo en el proyecto principal (por ejemplo, JettraFluxCenter).

### Añadir la Dependencia
Abre el archivo `pom.xml` del proyecto principal y añade la dependencia hacia el `.jar` del plugin:

```xml
<dependency>
    <groupId>com.jettraflux.example</groupId>
    <artifactId>JettraFluxPluginExample</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

### Auto-Descubrimiento de JettraFlux
No necesitas registrar manualmente cada página. Siempre que las clases del plugin estén correctamente anotadas (con `@Page` y otras anotaciones de Jettra) y el motor las escanee en el classpath, JettraFlux descubrirá y registrará automáticamente las rutas y funcionalidades provistas por el plugin.

### Consideraciones sobre Recursos y Propiedades
El motor cargará automáticamente las propiedades y clases que estén disponibles en el Classpath. Si necesitas cargar propiedades específicas, asegúrate de que el prefijo `plugin-<nombre>` se mantenga para no sobrescribir configuraciones críticas del proyecto central (`jettra-config.properties`, `messages_es.properties`, etc.).
