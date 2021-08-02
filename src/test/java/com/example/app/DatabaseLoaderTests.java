package com.example.app;

import com.example.app.myhelsinki.JPlace;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class DatabaseLoaderTests {

    private static PlacesRepository repository = mock(PlacesRepository.class);
    private static RestTemplate restTemplate = mock(RestTemplate.class);
    private static MyReader reader = mock(MyReader.class);

    public static final DatabaseLoader dbloader(){
        return new DatabaseLoader(repository);
    }

/*    @BeforeAll
    public static void setup(){

    }
*/
    @Test
    public void getTagsHasNullInput() throws IOException {
        when(reader.getLine()).thenReturn("");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?limit=10"));
    }

    @Test
    public void getTagsHasOneInput(){
        when(reader.getLine()).thenReturn("tag1");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?tags_filter=tag1&limit=10"));
    }


    @Test
    public void getTagsHas2CorrectInputs(){
        /** Correct Input is a list of tags without spaces separated with comma*/
        when(reader.getLine()).thenReturn("tag1,tag2");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?tags_filter=tag1,tag2&limit=10"));
    }

    @Test
    public void getTagsHas2IncorrectInputsWithSpaceWithCommas(){
        /** Correct Input is a list of tags without spaces separated with comma*/
        when(reader.getLine()).thenReturn("tag1, tag2");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?tags_filter=tag1,tag2&limit=10"));
    }

    @Test
    public void getTagsHas2IncorrectInputsWithSpaceWithoutCommas(){
        /** Correct Input is a list of tags without spaces separated with comma*/
        when(reader.getLine()).thenReturn("tag1 tag2");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?tags_filter=tag1,tag2&limit=10"));
    }

    @Test
    public void getTagsHas2IncorrectInputsWithSpacesWithoutCommas(){
        /** Correct Input is a list of tags without spaces separated with comma*/
        when(reader.getLine()).thenReturn("tag1  tag2");
        assertEquals(dbloader().getFilteringTags(reader), (DatabaseLoader.MyHelsinkiURL+"?tags_filter=tag1,tag2&limit=10"));
    }

    @Test
    public void getDataReturnsJPlaceForCorrectRequest(){
        /** dbloader.getData() return correct JPlace.class when HttpStatus==OK */
        var jmock = mock(JPlace.class);
        when(restTemplate.getForEntity(DatabaseLoader.MyHelsinkiURL, JPlace.class)).thenReturn(new ResponseEntity<JPlace>(jmock, HttpStatus.OK));
        try{
            assertEquals(dbloader().getData(DatabaseLoader.MyHelsinkiURL, restTemplate), jmock);
        } catch (Exception e) {System.out.println("Exception "+e);}
    }

    @Test
    public void getDataReturnsResponseCodeAsExceptionForIncorrectRequest(){
        /** dbloader.getData() return correct exception when HttpStatus!=OK
        Here we use HttpStatus = 404 NOT FOUND*/
        var jmock = mock(JPlace.class);
        var response = new ResponseEntity<JPlace>(jmock, HttpStatus.NOT_FOUND);
        when(restTemplate.getForEntity(DatabaseLoader.MyHelsinkiURL+"limit=10", JPlace.class)).thenReturn(response);
        try{
            assertEquals(dbloader().getData(DatabaseLoader.MyHelsinkiURL+"limit=10", restTemplate), jmock);
        } catch (Exception e) {
            Exception expected = new Exception(response.getStatusCode().toString());
            var s1 = expected.toString();
            var s2 = e.toString();
            assertEquals(s1,s2);
        }
    }

}
