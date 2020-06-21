Feature: registro de usuario sitio web

  Scenario: reistro de usuario exitoso.
    Given soy un cliente nuevo en sitio web
    When me quiero registrar con mi correo "f@mail.com" y password"Favr1986"
    Then el codigo de respuesta es 200