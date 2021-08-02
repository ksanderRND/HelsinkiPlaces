import axios from 'axios'

const PLACE_REST_API_URL = 'http://localhost:8080/api/places';

class PlaceService{

    getPlaces(){
        return axios.get(PLACE_REST_API_URL)
    }
}

export default new PlaceService();