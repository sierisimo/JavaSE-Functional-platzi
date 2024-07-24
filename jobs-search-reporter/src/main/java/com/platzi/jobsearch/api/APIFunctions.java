package com.platzi.jobsearch.api;

import feign.Feign;
import feign.gson.GsonDecoder;

public interface APIFunctions {
    /**
     * Para construir la llamada fon Feign, necesitamos generar un cliente de Feign,
     * esconder que internamente este es el cliente que se usa, nos facilita hacer facil
     * el reemplazo de la libreria para http en el futuro.
     *
     * @param api una Class de tipo T para construir nuestra api
     * @param url la URL base donde estaremos haciendo los requests
     * @param <T> el tipo de API que contruiremos
     * @return una instancia de T para usar como cliente de API
     */
    static <T> T buildAPI(Class<T> api, String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(api, url);
    }
}
