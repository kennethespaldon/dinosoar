import flightService from '../../../services/flightService.ts'
import React, { useEffect, useState } from "react";
import FlightListRow from "./FlightListRow.tsx";
import FlightHistoryRowHeader from "./FlightListRowHeader.tsx";
import './FlightList.css';
import type { Flight } from "../../../types/flight.ts";

function FlightList({ setSelectedFlight }: { setSelectedFlight: React.Dispatch<React.SetStateAction<Flight | null>>}) {
    const [flights, setFlights] = useState([]);
    useEffect(() => {
        (async () => {
            const flightsInDb = await flightService.getAllFlights();
            setFlights(flightsInDb);
        })();
    }, []);

    return (
        <div className='flight-list'>
            <FlightHistoryRowHeader />
            <ul className='flight-list-items'>
                {flights.map(flight => (
                    // TODO: Refactor backend to return FlightLog id in the FlightDTO so you can use it as a key here.
                    <FlightListRow flight={flight} setSelectedFlight={setSelectedFlight} />)
                )}
            </ul>
        </div>
    );
}

export default FlightList;