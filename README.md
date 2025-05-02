# Demo Automatización Mobile

Ejecutar proyecto:
- Levantar en una terminal al servidor local Appium
```bash
appium
```
- Levantar su dispositivo movil local ( iOS/Android )
- Editar las capabilities correspondientes en el archivo DriverManager.java
- Ejecutar el proyecto en otra terminal con:
```bash
mvn test -Dcucumber.filter.tags="@android"
```
 
