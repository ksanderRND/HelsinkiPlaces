package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Names {

    private String fi;
    private String en;
    private String sv;
    private String zh;

    public Names() {
        super();
    }

    public Names(String fi, String en, String sv, String zh){
        this.fi=fi;
        this.en=en;
        this.sv=sv;
        this.zh=zh;
    }

    public String toString() {
        return ( fi==null? "": "\nfi: "+ fi) +
               (en==null? "": "\nen: "+ en) +
               (sv==null? "": "\nsv: "+ sv) +
               (zh==null? "": "\nzh: "+ zh) + "\n";
    }
}