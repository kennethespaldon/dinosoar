interface AddFlightButtonProps {
    showAddFlightForm: boolean,
    setShowAddFlightForm: React.Dispatch<React.SetStateAction<boolean>>
}

function AddFlightButton({ showAddFlightForm, setShowAddFlightForm }: AddFlightButtonProps) {
    return <button className='add-flight-button' onClick={() => setShowAddFlightForm(!showAddFlightForm)}>+ Add Flight</button>
}

export default AddFlightButton;