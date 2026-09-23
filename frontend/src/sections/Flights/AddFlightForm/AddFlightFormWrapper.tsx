import {useState} from "react";
import AddFlightForm from "./AddFlightForm.tsx";
import AddFlightMenu from "./AddFlightMenu.tsx";

interface AddFlightFormWrapperProps {
    showAddFlightForm: boolean,
    setShowAddFlightForm: React.Dispatch<React.SetStateAction<boolean>>
}

function AddFlightFormWrapper({ showAddFlightForm, setShowAddFlightForm }: AddFlightFormWrapperProps) {
    const [ selectedTab, setSelectedTab ] = useState('Details');

    return (
        <div className={`${showAddFlightForm ? '' : 'hidden'}`}>
            <div className={`add-flight-form-wrapper`}>
                <AddFlightMenu selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
                <AddFlightForm selectedTab={selectedTab} setShowAddFlightForm={setShowAddFlightForm} />
            </div>
        </div>
    );
}

export default AddFlightFormWrapper;