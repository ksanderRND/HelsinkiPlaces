package com.example.app;

import javax.persistence.*;
import com.example.app.myhelsinki.Info;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "places")
public class Place {

    private @Id @GeneratedValue Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    public Place() {}

    public Place(String name, String address, String description) {
        this.name = name;
        this.address = address;
        this.description = description;
    }

    public Place(Info data){
        this.name = data.getName().getEn();
        this.address = data.getLocation().getAddress().toString();
        this.description = makeDescription(data);
    }

    public static String makeDescription(Info data){
        var wd = LocalDateTime.now().getDayOfWeek().getValue();
        var t = LocalTime.now();
        var ex = data.getOpening_hours().getHours();
        for(var date: ex){
            if (date.getWeekday_id()==wd) {
                if (date.getOpens()==null || date.getCloses()==null) {
                    return "Closed Now";
                }
                var time_open = LocalTime.parse(date.getOpens());
                var time_close = LocalTime.parse(date.getCloses());
                var time_cur = t;
                if (time_cur.isAfter(time_open) && time_cur.isBefore(time_close)){
                    return  "OPEN NOW";
                } else {
                    return "Closed Now";
                }
            }
        }

        return "___";
    }

    @Override
    public String toString() {
        return "Place{\n" +
                "id =" + id +
                ", Name:'" + name + '\'' +
                ", Address: '" + address + '\'' +
                ", description: '" + description+ '\'' +
                "\n}";
    }
}