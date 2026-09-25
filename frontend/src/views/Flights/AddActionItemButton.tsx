function AddActionItemButton({ addActionItem }: { addActionItem: (e: React.MouseEvent) => void }) {
    return (
        <button className='add-action-item-button' onClick={addActionItem}>
            + Add Action Item
        </button>
    );
}

export default AddActionItemButton;