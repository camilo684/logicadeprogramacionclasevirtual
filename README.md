# Lógica de programación - Clase virtual

Lógica II - Ingeniería de Software

## Contenido de la carpeta

| Archivo | Descripción |
|---|---|
| `Inversions.java` | Divide y vencerás: número de inversiones |
| `ChangeDP.java` | Programación dinámica: cambio de monedas con denominaciones 1, 3 y 4 |
| `ChangeGreedy.java` | Algoritmo voraz: cambio de monedas con denominaciones 1, 5 y 10 |

## Ejecución

Compilar:

```
javac -d bin *.java
```

### Divide y vencerás: `Inversions`

Lee `n` y la secuencia de enteros, imprime el número de inversiones calculado con divide y vencerás y luego ejecuta el stress testing contra la versión naive.

```
$ java -cp bin Inversions
5
2 3 9 2 9
2
Stress testing: 1000 pruebas OK
```

```
$ java -cp bin Inversions
8
8 7 6 5 4 3 2 1
28
Stress testing: 1000 pruebas OK
```

### Programación dinámica: `ChangeDP`

Prueba los dos ejemplos del enunciado y luego lee un valor de `money`.

```
$ java -cp bin ChangeDP
Sample 1: money = 2 -> 2
Sample 2: money = 34 -> 9
Ingrese money (1 a 1000): 1000
250
```

### Algoritmo voraz: `ChangeGreedy`

Prueba los dos ejemplos del enunciado, compara el enfoque 1 (aritmética básica) con el enfoque 2 (voraz) para todos los valores de 1 a 1000 y luego lee un valor de `m`.

```
$ java -cp bin ChangeGreedy
Sample 1: m = 2 -> 2
Sample 2: m = 28 -> 6
Enfoque 1 y Enfoque 2 coinciden para m de 1 a 1000
Ingrese m (1 a 1000): 28
6
```

## Tabla comparativa de las 3 técnicas

| Criterio | Divide y vencerás | Programación dinámica | Algoritmos voraces |
|---|---|---|---|
| Idea principal | Divide el problema en subproblemas independientes más pequeños, los resuelve recursivamente y combina sus soluciones | Resuelve primero los subproblemas más pequeños, guarda sus resultados en una tabla y los reutiliza para construir la solución de los mayores | En cada etapa toma la decisión que parece mejor en ese momento (óptimo local) sin reconsiderarla |
| Esquema | Top-down, recursivo | Bottom-up, iterativo (con memorización también puede ser top-down) | Iterativo por etapas |
| Subproblemas | Independientes, no se repiten | Solapados, se repiten muchas veces | No se generan subproblemas; solo se elige un candidato en cada paso |
| ¿Garantiza la solución óptima? | Sí, si el caso base y la combinación son correctos | Sí, si el problema cumple el principio de optimalidad de Bellman | No siempre; depende del problema (por ejemplo, del sistema monetario) y hay que demostrarlo |
| Eficiencia (tiempo) | Buena cuando la división es balanceada (n/2, n/2). Inversiones: O(n log n) frente a O(n²) de la versión naive | Evita recalcular subproblemas. Cambio de monedas: O(money · k), con k denominaciones. Fibonacci pasa de exponencial a O(n) | La más rápida de las tres. Cambio de monedas: O(k + m/10) con el enfoque voraz y O(1) con aritmética |
| Memoria (espacio) | Usa la pila del sistema por la recursión, O(log n) con división balanceada, más memoria auxiliar si la necesita. Inversiones: O(n) por el arreglo `aux` | Necesita la tabla de resultados, que puede ser grande. Cambio de monedas: O(money) por el arreglo `minCoins` | Mínima, O(1): solo guarda contadores y la solución que va construyendo |
| Ventajas | Simple, legible, fácil de depurar | Convierte soluciones exponenciales en polinómicas | Fácil de diseñar e implementar y muy eficiente |
| Inconvenientes | Mayor coste espacial por la pila; si los subproblemas se repiten puede ser ineficiente | Altos requerimientos de memoria; el espacio de parámetros debe ser discreto | Puede dar soluciones subóptimas; requiere demostrar que la selección lleva al óptimo global |
| Ejemplo en el repositorio | `Inversions.java` | `ChangeDP.java` | `ChangeGreedy.java` |
