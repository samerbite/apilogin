Feature: Api login funcionalidad

  Scenario Outline: Soy un usuario nuevo y deseo registrarme
    When solicito crear cuenta de usuario con correo <correo> y password <password>
    Then obtengo un codigo de respuesta 201

    Examples:
    | correo      | password  |
    |"g@mail.com" | "Abcd1234"|