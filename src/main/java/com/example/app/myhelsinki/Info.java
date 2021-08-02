package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Info {

    private Names name;
    private Location location;
    private OpeningHoursTranslated opening_hours;

    public Info(){
        super();
    }

    public Info(Names name, Location location, OpeningHoursTranslated opening_hours){
        this.name = name;
        this.location=location;
        this.opening_hours=opening_hours;
    }

    public String toString() {
        return String.format("\n Event: %s\n Address: %s\n Open: %s\n", name.toString(), location.toString(), opening_hours.toString());
    }
}