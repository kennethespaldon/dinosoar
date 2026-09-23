import {type ChangeEvent} from "react";
import './TextArea.css';

interface TextAreaProps {
    htmlFor: string,
    label: string,
    name?: string,
    id: string,
    cols: number,
    rows: number,
    value: string,
    onChange: (e: ChangeEvent<HTMLTextAreaElement>) => void,
    className?: string
}

function TextArea({ htmlFor, label, name, id, cols, rows, value, onChange, className }: TextAreaProps) {
    return (
        <div className='text-area-wrapper'>
            <label htmlFor={htmlFor} className='text-area-label'>{label}</label>
            <textarea name={name} id={id} cols={cols} rows={rows} value={value} onChange={onChange} className={`text-area ${className}`}></textarea>
        </div>
    );
}

export default TextArea;