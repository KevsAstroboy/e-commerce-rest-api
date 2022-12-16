package com.proj.ecommerce.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Error {

    Boolean hasError;
    String message;

    public Error(Boolean hasError, String message) {
        super();
        this.hasError = hasError;
        this.message = message;
    }


}
