# circuit-breaker
## Objetivo
Implementar el Patron Circuit Breaker para identificar posibles usos que se pueden realizar mas adelante.

## Descripción de la prueba
Se propone un flujo de 3 pasos para poder validar facilmente la solución. Inicialmente se consume un servicio al que se denominó proxy, este servicio tiene la responsabilidad de romper de manera aleatoria el servicio, y consumir el servicio final de obtención de paises.
![cbp-flujo](https://github.com/user-attachments/assets/7ff82cbf-a002-451a-8bf4-781a79cff3bd)

Pasos implementados para llevar a cabo la prueba
1. Clonar el proyecto
2. Importar el proyecto a un IDE, se recomienda Intellij, permite tener multiples versiones de java de manera facil.
3. Configurar java 17
4. Empaquetar el servicio
  mvn clean package -DskipTests
5. Subir el servicio
6. Consumir los siguientes servicios:
  * Servicio que permite consultar los paises y que tiene implementado el patron circuit breaker
    http://localhost:9090/countries
    ![image](https://github.com/user-attachments/assets/b362be01-6d98-4e5f-8728-4657f4e9717a)
   
  * Servicio que permite validar el estado del servicio, en este se puede visualizar el estado del circuit breaker
    http://localhost:9090/actuator/health
    ![image](https://github.com/user-attachments/assets/3dab270f-95d7-4781-8106-21a79d0d1a65)

## Tecnologías usadas en la prueba (especifique lenguajes, librerías)
* Se utilizó SDK17 
* Se utilizó Sprinboot 3.3.2
* Se utilizó Lombok
* para el manejo de Circuit Breaker se utilizó **resilience4j-spring-boot3**

## Resultados
Se logro implementar un metodo en el servicio /countries que sirve como interceptor del fallback que se genera cuando el servicio /proxy genera error.

## Conclusiones
Este patron de diseño permite controlar a nivel de software la resiliencia de un sistema frente a los fallos.

## Arquitectura de software
Se plantea realizar la solución con una Arquitectura Hexagonal basada en puertos y adaptadores.
![circuit-breaker-pattern](https://github.com/user-attachments/assets/455b87c8-332a-490f-a348-5fc1476cfbdc)
