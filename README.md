# REST
Proyecto a modo de ejemplo que expone una API *REST*, especificación *JAX-RS* e implementación *RESTEasy*
Para simular una BD se utiliza un *cache* el cual es cargado al deployar la aplicación con objetos del tipo *Person*.

El proyecto cuenta con una bateria de test TDD para probar cada servicio expuesto

## Servicios
### POST

    /rest-jax-rs-resteasy/rest/persons

    {
    "name":"Diego", 
     "lastName":"Gonzalez", 
     "streetAddress":"Rambla", 
     "age":"32", 
     "cellPhone":"099123456"
    }

###  GET

    /rest-jax-rs-resteasy/rest/persons/15

### PUT

    /rest-jax-rs-resteasy/rest/persons
    
    {
        "name":"Diego", 
         "lastName":"Gonzalez", 
         "streetAddress":"Rambla (Uruguay)", 
         "age":"32", 
         "cellPhone":"099987654"
    }
    
### DELETE

    /rest-jax-rs-resteasy/rest/persons/15

## Resumen

- *JEE*
**Java 8**
**EJB**

- *Cache*
**Ehcache**

- *TDD*
**Junit**

- *Servidor de aplicaciones*
**wildfly-16.0.0.Final**
