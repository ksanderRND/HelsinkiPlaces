package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Location {
    private Addr address;

    public Location(){ super(); }

    public Location(Addr address){
        this.address=address;
    }
}