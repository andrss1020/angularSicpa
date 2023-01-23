package com.backend.crud.util;


import java.net.URISyntaxException;

public class Validation {

    public static void IfIdExistThrowURISyntaxException(Long entityId, String entityName) throws URISyntaxException {
        if (entityId != null) {
            throw new IllegalArgumentException("Error");
        }
    }

    public static void IfIdNotExistThrowURISyntaxException(Long entityId, String entityName) throws URISyntaxException {
        if (entityId == null) {
            String errorKey = entityName + "IdNotFound";
            throw new IllegalArgumentException(errorKey);
        }
    }
}
