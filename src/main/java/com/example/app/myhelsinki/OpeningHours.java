package com.example.app.myhelsinki;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHours {

    private String opens;
    private String closes;
    private int weekday_id;

    public OpeningHours() { super(); }

    public OpeningHours(int weekday_id, String opens, String closes){
        this.weekday_id=weekday_id;
        this.opens=opens;
        this.closes=closes;
    }

    public String toString(){
        return String.format("\n weekday: %s\n Open from: %s to: %s ", this.weekday(), opens, closes);
    }

    public String weekday(){
        switch(weekday_id) {
            case (1): return "Mon";
            case (2): return "Tue";
            case (3): return "Wed";
            case (4): return "Thu";
            case (5): return "Fri";
            case (6): return "Sat";
            case (7): return "Sun";
            default : return String.format( "Unknown day: %s", weekday_id);
        }
    }
}