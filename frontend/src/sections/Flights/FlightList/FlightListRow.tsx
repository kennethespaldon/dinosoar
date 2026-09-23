import type { Flight } from "../../../types/flight.ts";

function FlightListRow({ flight, setSelectedFlight }: { flight: Flight, setSelectedFlight: React.Dispatch<React.SetStateAction<Flight | null>> }) {
    const [year, day, month] = flight.date.split('-');

    return (
        <div className='flight-list-row' onClick={() => setSelectedFlight(flight)}>
            <div className='flight-list-col flight-list-col-date'>{`${day}-${month}-${year}`}</div>
            <div className='flight-list-col flight-list-col-student'>{ flight.student.firstName } { flight.student.lastName }</div>
            <div className='flight-list-col flight-list-col-aircraft'>{ flight.aircraft.tailNumber }</div>
            <div className='flight-list-col flight-list-col-cross-country'>{ flight.crossCountry ? 'Yes' : 'No' }</div>
            <div className='flight-list-col flight-list-col-night-flight'>{ flight.nightFlight ? 'Yes' : 'No' }</div>
            <div className='flight-list-col flight-list-col-flight-time'>{ flight.duration } { flight.duration > 1 ? 'hrs' : 'hr' }</div>
        </div>
    );
}

export default FlightListRow;