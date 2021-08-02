package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHoursTranslated {
    private OpeningHours[] hours;

    public OpeningHoursTranslated(){ super (); }

    public OpeningHoursTranslated(OpeningHours[] hours){
        this.hours=hours;
    }

}