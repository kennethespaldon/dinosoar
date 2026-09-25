import React from "react";
import type { ActionItem as ActionItemType } from "../../types/flight.ts";
import TextArea from "../../components/TextArea/TextArea.tsx";

interface ActionItemProps {
    item: ActionItemType,
    index: number,
    updateActionItem: (index: number, text: string) => any
}

function ActionItem({ item, index, updateActionItem }: ActionItemProps) {
    return (
        <div className='action-item'>
            {/*<textarea*/}
            {/*    value={item.text}*/}
            {/*    onChange={(e) => updateActionItem(index, e.target.value)}*/}
            {/*/>*/}
            <TextArea htmlFor={`action-item-${index}`} label={`Action Item ${index + 1}`} id={`action-item-${index}`} cols={10} rows={1} value={item.text} onChange={(e) => updateActionItem(index, e.target.value)} />
        </div>
    );
}

export default React.memo(ActionItem);