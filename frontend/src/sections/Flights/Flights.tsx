import type {Flight} from "../../types/flight.ts";
import {useState} from "react";
import FlightList from "./FlightList/FlightList.tsx";
import FlightLog from "./FlightLog/FlightLog.tsx";
import AddFlightButton from "./AddFlightForm/AddFlightButton.tsx";
import AddFlightFormWrapper from "./AddFlightForm/AddFlightFormWrapper.tsx";

function Flights() {
    const [ selectedFlight, setSelectedFlight ] = useState<Flight | null>(null);
    const [ showAddFlightForm, setShowAddFlightForm ] = useState(false);

    if (selectedFlight) {
        return (
            <div>
                {/*create a "back" button that calls setSelectedFlight(null). Therefore, rendering the Flights List*/}
                <button onClick={() => setSelectedFlight(null)}>Flights List</button>
                <FlightLog selectedFlight={selectedFlight} />
            </div>
        );
    }

    return (
        <div className='flights'>
            <AddFlightFormWrapper showAddFlightForm={showAddFlightForm} setShowAddFlightForm={setShowAddFlightForm} />
            <FlightList setSelectedFlight={setSelectedFlight} />
            <AddFlightButton showAddFlightForm={showAddFlightForm} setShowAddFlightForm={setShowAddFlightForm} />
        </div>
    );
}

export default Flights;