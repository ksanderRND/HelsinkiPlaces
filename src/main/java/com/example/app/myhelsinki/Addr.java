package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Addr {
    private String street_address;
    private String postal_code;
    private String locality;

    public Addr() {
        super();
    }
    public Addr(String street_address, String postalCode, String locality){
        this.street_address=street_address;
        this.postal_code=postalCode;
        this.locality=locality;
    }

    public String toString() {
        return String.format("\n %s , %s, %s\n", street_address, postal_code, locality);
    }


}