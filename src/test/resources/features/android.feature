Feature: Test de Android virtual

    @android
    Scenario: Validar ingreso exitoso
        Given el usuario esta en la aplicacion movil android
        When el usuario ingresa a la opcion conexion
        Then el usuario deberia ver la opcion internet