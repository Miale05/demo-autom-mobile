Feature: Test de iOS virtual

    @ios
    Scenario: Validar ingreso exitoso
        Given el usuario esta en la aplicacion movil ios
        When el usuario ingresa a la opcion generales
        Then el usuario deberia ver otras opciones