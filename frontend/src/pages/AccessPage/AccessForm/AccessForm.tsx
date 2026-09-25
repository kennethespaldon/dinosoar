import React from 'react';
import './AccessForm.css';
import LoginForm from "../LoginForm.tsx";
import RegistrationForm from "../RegistrationForm.tsx";

interface AccessFormPropsType {
    formMode: string,
    setFormMode: React.Dispatch<React.SetStateAction<string>>
}

function AccessForm({ formMode, setFormMode }: AccessFormPropsType) {
    if (formMode === 'register') {
        return (
            <div className='access-form-wrapper'>
                <h1 className='access-form-title'>Create an account</h1>
                <RegistrationForm />
                <p className='or-label'>OR</p>
                <button className='secondary-btn' onClick={() => setFormMode('login')}>I already have an account</button>
            </div>
        );
    }

    return (
        <div className='access-form-wrapper'>
            <LoginForm />
            <p className='or-label'>OR</p>
            <button className='secondary-btn' onClick={() => setFormMode('register')}>Create an account</button>
        </div>
    );
}

export default AccessForm
