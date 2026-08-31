import React, { type Dispatch } from "react";
import './TextInput.css';

interface TextInputProps {
    label: string,
    htmlFor: string,
    type: string,
    id: string,
    state: string,
    setState: Dispatch<React.SetStateAction<string>>
}

function TextInput({ label, htmlFor, type, id, state, setState }: TextInputProps) {
    return (
        <div className='text-input-wrapper'>
            <label htmlFor={htmlFor} className='text-input-label'>{label}</label>
            <input
                value={state}
                onChange={e => setState(e.target.value)}
                type={type}
                id={id}
                required
                autoComplete='off'
                className='text-input'
            />
        </div>
    );
}

export default TextInput;