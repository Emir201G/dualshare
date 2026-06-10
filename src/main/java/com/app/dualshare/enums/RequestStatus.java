package com.app.dualshare.enums;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


public enum RequestStatus {
    PENDING,
    ACCEPTED,
    REJECTED,
}