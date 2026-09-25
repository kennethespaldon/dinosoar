import TextInput from "../../../components/TextInput/TextInput.tsx";

interface FlightDetailsProps {
    studentId: number | null,
    setStudentId: React.Dispatch<React.SetStateAction<number | null>>,
    date: string,
    setDate: React.Dispatch<React.SetStateAction<string>>,
    tailNumber: string,
    setTailNumber: React.Dispatch<React.SetStateAction<string>>,
    flightTime: number,
    setFlightTime: React.Dispatch<React.SetStateAction<number>>,
    crossCountry: boolean,
    setCrossCountry: React.Dispatch<React.SetStateAction<boolean>>,
    nightFlight: boolean,
    setNightFlight: React.Dispatch<React.SetStateAction<boolean>>,
    selectedTab: string
}

function FlightDetails({
                           studentId, setStudentId,
                           date, setDate,
                           tailNumber, setTailNumber,
                           flightTime, setFlightTime,
                           crossCountry, setCrossCountry,
                           nightFlight, setNightFlight, selectedTab
}: FlightDetailsProps) {
    // Student ID, Date, Tail Number, Flight Time, Cross Country, Night Flight
    return (
        <div className={`flight-details-wrapper ${selectedTab === 'Details' ? '' : 'hidden'}`}>
            <div className='flight-details'>
                {/*<div>*/}
                {/*    <label htmlFor="studentId">Student ID</label>*/}
                {/*    <input type="text" inputMode='numeric' id='studentId' name='studentId'*/}
                {/*           value={studentId === null ? '' : Number(studentId)}*/}
                {/*           onChange={(e) => setStudentId(e.target.value === null ? null : Number(e.target.value))}*/}
                {/*    />*/}
                {/*</div>*/}
                <TextInput label='Student ID' htmlFor='student-id' type='text' id='student-id' state={studentId} setState={setStudentId} />

                {/*<div>*/}
                {/*    <label htmlFor="add-flight-date">Date</label>*/}
                {/*    <input*/}
                {/*        type='text'*/}
                {/*        id='add-flight-date'*/}
                {/*        name='date'*/}
                {/*        placeholder='MM-DD-YYYY'*/}
                {/*        value={date}*/}
                {/*        onChange={(e) => setDate(e.target.value)}*/}
                {/*    />*/}
                {/*</div>*/}
                <TextInput label='Date' htmlFor='date' type='text' id='date' state={date} setState={setDate} />

                {/*<div>*/}
                {/*    <label htmlFor='add-flight-tail-number'>Tail Number</label>*/}
                {/*    <input*/}
                {/*        type='text'*/}
                {/*        id='add-flight-tail-number'*/}
                {/*        name='tail-number'*/}
                {/*        value={tailNumber}*/}
                {/*        onChange={(e) => setTailNumber(e.target.value)}*/}
                {/*    />*/}
                {/*</div>*/}
                <TextInput label='Tail Number' htmlFor='tailNumber' type='text' id='tailNumber' state={tailNumber} setState={setTailNumber} />

                {/*<div>*/}
                {/*    <label htmlFor='add-flight-flight-time'>Flight Time</label>*/}
                {/*    <input*/}
                {/*        type='text'*/}
                {/*        inputMode='decimal'*/}
                {/*        id='add-flight-flight-time'*/}
                {/*        name='flight-time'*/}
                {/*        value={flightTime}*/}
                {/*        onChange={(e) => setFlightTime(Number(e.target.value))}*/}
                {/*    />*/}
                {/*</div>*/}
                <TextInput label='Flight Time' htmlFor='flight-time' type='number' id='flight-time' inputMode='decimal' state={flightTime} setState={setFlightTime} />


                {/*Must send boolean in form for cross country. 'yes' or 'no' strings are currently being sent.*/}
                <div className='add-flight-cross-country'>
                    <div>Cross Country</div>
                    <div>
                        <label htmlFor='add-flight-cross-country-yes'>Yes</label>
                        <input type="radio" id='add-flight-cross-country-yes' name='cross-country'
                               checked={crossCountry}
                               onChange={() => setCrossCountry(true)}/>
                    </div>
                    <div>
                        <label htmlFor='add-flight-cross-country-no'>No</label>
                        <input type="radio" id='add-flight-cross-country-no' name='cross-country'
                               checked={!crossCountry}
                               onChange={() => setCrossCountry(false)} />
                    </div>
                </div>

                {/*Must send boolean in form for night flight. 'yes' or 'no' strings are currently being sent.*/}
                <div className='add-flight-night-flight'>
                    <div>Night Fight</div>
                    <div>
                        <label htmlFor='add-flight-night-flight-yes'>Yes</label>
                        <input type="radio" id='add-flight-night-flight-yes' name='night-flight'
                               checked={nightFlight}
                               onChange={() => setNightFlight(true)}
                        />
                    </div>
                    <div>
                        <label htmlFor='add-flight-night-flight-no'>No</label>
                        <input type="radio" id='add-flight-night-flight-no' name='night-flight'
                               checked={!nightFlight}
                               onChange={() => setNightFlight(false)}
                        />
                    </div>
                </div>
            </div>
        </div>
    );
}

export default FlightDetails;