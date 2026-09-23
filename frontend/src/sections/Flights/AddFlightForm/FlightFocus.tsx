import AddActionItemButton from "../AddActionItemButton.tsx";
import ActionItem from "../ActionItem.tsx";
import type { ActionItem as ActionItemType } from '../../../types/flight.ts';
import TextArea from "../../../components/TextArea/TextArea.tsx";

interface FlightFocusProps {
    objective: string,
    setObjective: React.Dispatch<React.SetStateAction<string>>,
    actionItems: Array<ActionItemType>,
    addActionItem: (e: React.MouseEvent) => void,
    updateActionItem: (index: number, newText: string) => void,
    selectedTab: string
}

function FlightFocus({ objective, setObjective, actionItems, addActionItem, updateActionItem, selectedTab }: FlightFocusProps) {
    return (
        <div className={`${selectedTab === 'Focus' ? '' : 'hidden'}`}>
            <div className='add-flight-form-focus'>
                <TextArea htmlFor='add-flight-objective' label='Objective(s)' name='objective' id='add-flight-objective' cols={50} rows={5}
                          value={objective}
                          onChange={(e) => setObjective(e.target.value)} />

                <div className='action-items-wrapper'>
                    <div className='action-items-title'>Action Items</div>
                    {/*<AddActionItemButton.tsx /> Whenever this button is clicked add the following textarea. There should be no labels here.*/}
                    {/*Each action item should have an AddNotesButton and each of thos should have an AddResourceButton*/}
                    {/*dont need to implement resources with notes yet though*/}
                    {/*When rendering list of textareas edit the name to be action-item-# */}
                    <div className='action-items'>
                        {actionItems.map((item, index) => <ActionItem item={item} index={index} updateActionItem={updateActionItem} />)}
                    </div>

                    <AddActionItemButton addActionItem={addActionItem} />
                </div>
            </div>
        </div>
    );
}

export default FlightFocus;