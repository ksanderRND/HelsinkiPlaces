import React from 'react';
import PlaceService from '../service/PlaceService';

class PlaceComponent extends React.Component {

    constructor(props){
        super(props)
        this.state = {
            places:[]
        }
    }

    componentDidMount(){
        PlaceService.getPlaces().then( (response)=> {
            this.setState({ places: response.data})
        });
    }

    render (){
        return(
            <div>
                <h1 className = "text-center"> Place List</h1>
                <table className = "table table-striped">
                    <thead>
                        <tr>
                            <td> Place Id</td>
                            <td> Place Name</td>
                            <td> Place Address</td>
                            <td> Place Description</td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.places.map(
                                place =>
                                <tr key = {place.id}>
                                    <td>{place.id}</td>
                                    <td>{place.name}</td>
                                    <td>{place.address}</td>
                                    <td>{place.description}</td>
                                </tr>
                            )
                        }
                    </tbody>

                </table>
            </div>
        )
    }
}

export default PlaceComponent