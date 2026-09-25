import type {Flight} from "../../../types/flight.ts";
import {useState} from "react";

function FlightLog({ selectedFlight }: { selectedFlight: Flight | null}) {
    const [ tab, setTab ] = useState("About");
    return (
        <div>
            {/*<FlightLogMenu />*/}
            <div></div>

            {/*<FlightLogContent />*/}
            <div></div>
        </div>
    );
}

export default FlightLog;