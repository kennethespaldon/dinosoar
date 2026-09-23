import TextArea from "../../../components/TextArea/TextArea.tsx";

interface FlightFeedbackProps {
    thingsDoneWell: string,
    setThingsDoneWell: React.Dispatch<React.SetStateAction<string>>,
    thingsToImprove: string,
    setThingsToImprove: React.Dispatch<React.SetStateAction<string>>,
    selectedTab: string
}

function FlightFeedback({ thingsDoneWell, setThingsDoneWell, thingsToImprove, setThingsToImprove, selectedTab }: FlightFeedbackProps) {
    return (
        <div className={`flight-feedback-wrapper ${selectedTab === 'Feedback' ? '' : 'hidden'}`}>
            <div className='flight-feedback'>
                <TextArea htmlFor='thingsDoneWell' label='Things Done Well' name='thingsDoneWell' id='thingsDoneWell' cols={50} rows={5}
                          value={thingsDoneWell}
                          onChange={(e) => setThingsDoneWell(e.target.value)} />

                <TextArea htmlFor='thingsToImprove' label='Things To Improve' name='thingsToImprove' id='thingsToImprove' cols={50} rows={5}
                          value={thingsToImprove}
                          onChange={(e) => setThingsToImprove(e.target.value)} />
            </div>
        </div>
    )
}

export default FlightFeedback;