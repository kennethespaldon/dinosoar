import React, { type Dispatch } from "react";
import './TextInput.css';

interface TextInputProps {
    label: string,
    htmlFor: string,
    type: string,
    id: string,
    state: any,
    setState: Dispatch<React.SetStateAction<any>>,
    inputMode?: "search" | "email" | "tel" | "text" | "url" | "none" | "numeric" | "decimal" | undefined
}

function TextInput({ label, htmlFor, type, id, state, setState, inputMode }: TextInputProps) {
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
                inputMode={inputMode}
                className='text-input'
            />
        </div>
    );
}

export default TextInput;