# Serenity-BDD API Automation Framework

Framework de automatización de pruebas para APIs REST utilizando Serenity BDD, implementado con el patrón Screenplay. Este proyecto está diseñado para facilitar la escritura de pruebas legibles, mantenibles y escalables, aprovechando las capacidades de Serenity.

## 🏛️ Arquitectura

Este proyecto está construido con Serenity-BDD y Cucumber.

![Architecture](docs/arq-aut-api-serenity.jpg)

---

## 🏗️ Tecnologías Utilizadas

* Java 17 
* Serenity BDD 
* RestAssured 
* Cucumber 
* Gradle 
* Hamcrest Matchers

## 🚀 Ejecución de Pruebas

### Comando para ejecución

Corre el siguiente comando en la terminal:

```
gradlew clean test --tests "*.TestUserRunner" -Denvironment={ENV}
```

### Parámetros aceptados

`environment` (optional):

Especifica el ambiente a ejecutar las pruebas, depende del archivo `serenity.conf`.

## 📌 Notas Adicionales

* El proyecto está configurado para utilizar Cucumber como herramienta de definición de pruebas en lenguaje natural. 
* Se recomienda revisar y ajustar los archivos de configuración (`serenity.properties`, `build.gradle`) según las necesidades específicas del entorno de desarrollo y ejecución.