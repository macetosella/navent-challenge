# Navent-challenge
# Ejercicio Técnico Navent

## Resuesta al punto 2

### Teniendo en cuenta que la tabla Pedido tenga muchos registros y columnas habría que:
- Para los BLOB sería posible utilizar una tabla aparte que referencie a la tabla Pedidos y se maneje a nivel aplicación su obtención de manera lazy.
  En la página de MySQL se recomienda incluso considerar utilizar otro motor, en diferente instancia, para algún campo BLOB debido al costo que tiene obtener información de dicho campo y que tendría consecuencias de performance a nivel lectura e implicancias en la red. Incluso se menciona que que se puede requerir a nivel físico una lectura secuencial grande que es más conveniente en discos tradicionales que en los de estado sólido (SDD). 
- Definir correctamente los tipos de datos según la necesidad concreta. 
  Por ejemplo los numéricos tener en cuenta en qué rangos pueden manejarse para utilizar menos bits por valor si no es necesario.
- Retornar en las consultas, en select los campos que se necesitan realmente para disminuir accesos I/O.
- Generar índices para los campos involucrados en consultas dentro de where, aunque es bueno considerar dejar de lado los campos que tienen un alto número de nulls ya que puede incrementar el espacio del índice en algunos motores.
- Intentar utilizar DTOs para los servicios involucrados, retornando los campos necesarios para evitar responses con mucha información y que la red no se sobrecargue. Esto se implementa en esta sulción entregada.
- Generar tablas con datos sumarizados o un cubo y modificar la responsabilidad de aplicaciones que interactuan con los pedidos. Para lo que es aplicaciones de consultas gerenciales utilizar posiblemente un Data Warehouse.
- En caso de alto tráfico si se necesita utilizar muchas conexiones al motor se puede considerar en hacer réplicas de la base de datos y tener un balanceador de carga.
