# EXAMEN MVVM

**Indice**
- Implementa una cuenta atrás 5...4...3...2...1
- Utiliza los Estados auxiliares para la cuenta atrás
- Configura un cuadro de texto para mostrar la cuenta atrás
- Cuando el usuario le da al "Start" empieza la cuenta atrás
- Si la cuenta atrás llega a uno y el usuario aun no acertó, la app vuelve al estado INICIO
- Plantea una mejora


# 1. Implementa una cuenta atrás 5...4...3...2...1
Para implementar la cuenta atras, pensé en hacer lo siguiente:
1. A través del código de los estados que tenías en tu rama, le cambié el texto y le puse más estados. Los numeros van del 5 al 1
2. Eso lo metemos en una curutina en la cual le metemos un delay y la cuenta va llegando del 5 al 1

# 2. Utiliza los Estados auxiliares para la cuenta atrás
Para la cuenta atrás utilizamos la enum class de estados y en una de las propiedades del enum, le puse un texto cada uno con el valor de un numero
1. En el viewModel tenemos un metodo que hace que la cuenta vaya del 5 al 1, el delay que establecí fue de 1 segundo


# 3. Configura un cuadro de texto para mostrar la cuenta atrás
Para configurar el cuadro de texto, hice lo siguiente
1. tengo una composable con el cuadro de texto en el que pone Cuenta: y al lado de momento está en blanco
2. por parámetro le pase una variable que está observando a un mutableLiveData que tengo en el viewModel que observa a su vez a un getter de la clase datos que me devuelve la cuenta atras
3. cada vez que el estado de la cuenta atras se cambie, el valor de la cuenta atras de la pantalla se cambiará también

# 4. Cuando el usuario le da al "Start" empieza la cuenta atrás

Le tenemos que meter en el onclick del boton de start
1. dentro de la funcion que crea el random, en la misma funcion que crea el random
2.  llamamos a la curutina de los estados auxiliares para que cambie el valor de la cuenta atras en la pantalla

# 5. Si la cuenta atrás llega a uno y el usuario aun no acertó, la app vuelve al estado INICIO
Dentro del metodo que comprueba si hemos acertado o fallado
1. tendríamos que meter una condición que implica que si la variable de la cuenta atras está en 1, se vuelva al estado inicial y el usuario tenga que volver a empezar la ronda
2. Dentro de la curutina hemos metido un metodo para comprobar si la variable ha llegado a 1.
3. Si ha llegado a 1, volvemos al estado inicial

# 6. Plantea una mejora
Mejoras que podemos hacer:
1. Podríamos mejorar la cuenta atrás para hacer que llegue al 0 en vez de al 1 y darle más tiempo al usuario
2. Podríamos hacer que la cuenta se aumente a medida de que el usuario acierte más rapido
3. Podríamos penalizar con el tiempo de manera que si el usuario falle, la cuenta vaya más rápido
4. Podríamos hacer a su vez que si el usuario acierta rápido, la cuenta se ralentice en la siguiente ronda


