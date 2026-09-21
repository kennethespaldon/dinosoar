import {useEffect, useState} from "react";
import type { ActionItem as ActionItemType, Note } from '../../../types/flight.ts';
import ActionItem from '../ActionItem.tsx';
import AddActionItemButton from "../AddActionItemButton.tsx";
import {FLIGHTS_API_BASE_PATH} from "../../../constants.ts";
import useCurrentUserQuery from "../../../hooks/useCurrentUserQuery.ts";
import useCsrfTokenQuery from "../../../hooks/useCsrfTokenQuery.ts";
import FlightDetails from "./FlightDetails.tsx";
import FlightFeedback from "./FlightFeedback.tsx";
import FlightFocus from "./FlightFocus.tsx";

interface AddFlightFormProps {
    showAddFlightForm: boolean,
    setShowAddFlightForm: React.Dispatch<React.SetStateAction<boolean>>
}

function NewFlightForm({ showAddFlightForm, setShowAddFlightForm }: AddFlightFormProps) {
    // can probably just use React Hook Form here
    const [ studentId, setStudentId ] = useState<number | null>(null);
    const [ date, setDate ] = useState('');
    const [ tailNumber, setTailNumber ] = useState('');
    const [ flightTime, setFlightTime ] = useState(0); // convert this to a number
    const [ crossCountry, setCrossCountry ] = useState(false);
    const [ nightFlight, setNightFlight ] = useState(false);
    const [ objective, setObjective ] = useState('');
    const [ actionItems, setActionItems ] = useState<Array<ActionItemType>>([]);
    const [ thingsDoneWell, setThingsDoneWell ] = useState('');
    const [ thingsToImprove, setThingsToImprove ] = useState('');
    const [ instructorId, setInstructorId ] = useState<number | null>(null); // get instructor id from currentUser in queryClient
    const [ nextFlightId, setNextFlightId ] = useState<number | null>(null);

    const userQuery = useCurrentUserQuery();
    const csrfTokenQuery = useCsrfTokenQuery();

    useEffect(() => {
        if (userQuery.data) {
            setInstructorId(userQuery.data.id);
        } else {
            setInstructorId(null);
        }
    }, [userQuery.data]);

    const handleSubmit: React.SubmitEventHandler<HTMLFormElement> = (t) => {
        // construct Request body here using e
    };

    const updateActionItem = (index: number, newText: string) => {
        // setActionItems([
        //     ...actionItems,
        //     actionItems[index] = {
        //         text: newText,
        //         notes: actionItems[index].notes
        //     }
        // ]);

        setActionItems([
            ...(actionItems.map((item, i) => i !== index ? item :
                {
                    text: newText,
                    notes: item.notes
                })
            )
        ]);
    };

    const addActionItem = (e: React.MouseEvent) => {
        e.preventDefault();

        setActionItems([
            ...actionItems,
            {
                text: '',
                notes: []
            }
        ]);
    };

    return (
        <form className={`add-flight-form ${showAddFlightForm ? '' : 'hidden'}`}>
            <FlightDetails
                studentId={studentId} setStudentId={setStudentId}
                date={date} setDate={setDate}
                tailNumber={tailNumber} setTailNumber={setTailNumber}
                flightTime={flightTime} setFlightTime={setFlightTime}
                crossCountry={crossCountry} setCrossCountry={setCrossCountry}
                nightFlight={nightFlight} setNightFlight={setNightFlight}
            />

            <FlightFocus
                objective={objective}
                setObjective={setObjective}
                actionItems={actionItems}
                addActionItem={addActionItem}
                updateActionItem={updateActionItem}
            />

            <FlightFeedback
                thingsDoneWell={thingsDoneWell} setThingsDoneWell={setThingsDoneWell}
                thingsToImprove={thingsToImprove} setThingsToImprove={setThingsToImprove}
            />

            <div>
                <label htmlFor="nextFlightId">Next Flight ID</label>
                <input type='text' id='nextFlightId' name='nextFlightId'
                       value={nextFlightId === null ? '' : Number(nextFlightId)}
                       onChange={(e) => setNextFlightId(e.target.value === '' ? null : Number(e.target.value))}
                />
            </div>

            {/*Create the request body using the input values. Student id should be entered by instructor. Get the instructor id from the client side.*/}
            {/*The resource ids of each resource should be stored in the resources already. */}
            <button onClick={(e) => {
                e.preventDefault();
                setShowAddFlightForm(false);
            }}>Cancel</button>
            <button onClick={async (e) => {
                e.preventDefault();

                const csrfToken = csrfTokenQuery.data;

                if (!csrfToken) {
                    throw new Error('Failed to retrieve CSRF token to create a new flight log!');
                }

                await fetch(`${FLIGHTS_API_BASE_PATH}`, {
                    method: 'POST',
                    body: JSON.stringify({
                        date,
                        aircraftTailNumber: tailNumber,
                        duration: flightTime,
                        crossCountry,
                        nightFlight,
                        objective,
                        actionItems,
                        thingsDoneWell,
                        thingsToImprove,
                        studentId,
                        instructorId,
                        nextFlightId
                    }),
                    headers: {
                        'Content-Type': 'application/json',
                        'X-CSRF-TOKEN': csrfToken.token
                    }
                });
            }}>Add</button>
        </form>
    )
}

export default NewFlightForm;