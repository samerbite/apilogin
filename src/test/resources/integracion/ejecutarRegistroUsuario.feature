Feature: Api login funcionalidad

  Scenario Outline: Soy un usuario nuevo y deseo registrarme
    When solicito crear cuenta de usuario con correo <correo> y password <password>
    Then obtengo un codigo de respuesta <status> y mensaje <codigoError>

    Examples:
    | correo           | password  | status | codigoError  |
    |"v@mail.com"      | "Abcd1234"| 201    | ""           |
    |"v@mail.com"      | "1234Abcd"| 400    | "Cod004"     |
    |"prueba@mail.com" | "11111111"| 400    | "Cod006"     |
    |"f.com"           | "Abcd1234"| 400    | "Cod005"     |
