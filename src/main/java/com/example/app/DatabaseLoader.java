package com.example.app;

import com.example.app.myhelsinki.JPlace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final PlacesRepository repository;

    public final static String MyHelsinkiURL = "https://open-api.myhelsinki.fi/v1/places/";

    @Autowired
    public DatabaseLoader(PlacesRepository repository){
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {

        System.out.println("**********************START***********************");
        try {
            MyReader reader = new MyReader();
            RestTemplate restTemplate = new RestTemplate();
            String uri = getFilteringTags(reader);
            final JPlace jplace = getData(uri, restTemplate);
            this.saveData(jplace);
        } catch (Exception e) { System.out.println("Problems with connection. Exception:" + e); }

        System.out.println("**********************DONE***********************");
    }

    public JPlace getData(String uri, RestTemplate restTemplate) throws Exception {

        ResponseEntity<JPlace> response = restTemplate.getForEntity(uri, JPlace.class);

        if(response.getStatusCode() == HttpStatus.OK) {
            final JPlace jplace = response.getBody();
            return jplace;
        } else {
            System.out.println("WARNING. Problems with response: "+response.getStatusCode());
            throw new Exception(response.getStatusCode().toString());
        }
    }
    public void saveData(JPlace jplace) throws Exception {
        /**save data from MyHelsinki */
        for(var data:jplace.getData()){
            this.repository.save(new Place(data));
        }
    }

    public String getFilteringTags(MyReader reader) {
        String uri = MyHelsinkiURL;
        String tags = reader.getLine();
        if(!tags.isEmpty())
        {
            if (!tags.contains(",")){
                tags = tags.replace(" ", ",");
            } else {
                tags = tags.replace(" ", "");
            }
            while (tags.contains(",,")) {tags = tags.replace(",,", ",");}
            uri+="?tags_filter="+tags + "&";
        } else {
            uri+="?";}
        uri +="limit=10";
        System.out.println(uri);
        return uri;
    }
}