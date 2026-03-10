package com.tt1.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MailerStubTest {

    private MailerStub mailer;

    @BeforeEach
    void setUp() {
        mailer = new MailerStub();
    }

    @Test
    void testEnviarCorreoRetornaTrue() {
        boolean resultado = mailer.enviarCorreo("test@test.com", "Hola");

        assertTrue(resultado);
    }

    @Test
    void testEnviarCorreoConDireccionVacia() {
        boolean resultado = mailer.enviarCorreo("", "Mensaje");

        assertTrue(resultado);
    }
}
