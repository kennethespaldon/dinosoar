package com.dinosoar.backend.auth.accesscode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "access_codes")
@Getter
@Setter
public class AccessCode {

    @Id
    @Column
    private String code;

    public AccessCode() {

    }

    public AccessCode(String code) {
        this.code = code;
    }
}
