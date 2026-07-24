package com.app.dualshare.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public record ErrorResponse
        (
                String message,
                int status,
                String timestamp,
                String phat
        ) {

}
