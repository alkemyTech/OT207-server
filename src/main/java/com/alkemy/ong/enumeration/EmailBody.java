package com.alkemy.ong.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailBody {

    NEW_USER("Agradecemos que quieras formar parte de la comunidad Somos Más, tu usuario fue registrado con éxito en nuestra plataforma. Ante cualquier consulta, no dudes en contactarnos."),
    NEW_CONTACT("Muchas gracias por escribirnos! Somos Más se contactará con usted muy pronto.");

    private final String body;
}
