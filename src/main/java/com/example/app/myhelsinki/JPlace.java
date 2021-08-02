package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class JPlace {
    //private Meta meta;
    private Info[] data;

    public JPlace() { super(); }

    public JPlace(Info[] data) {
        this.data=data;
    }
}