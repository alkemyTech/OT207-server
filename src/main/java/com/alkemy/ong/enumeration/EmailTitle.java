package com.alkemy.ong.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTitle {

    NEW_USER("Te damos la bienvenida a Somos Más!"),
    NEW_CONTACT("Formulario de contacto recibido");

    private final String title;
}
