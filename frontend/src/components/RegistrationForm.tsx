import TextInput from "./TextInput/TextInput.tsx";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import useCurrentUserQuery from "../hooks/useCurrentUserQuery.ts";
import useCsrfTokenQuery from "../hooks/useCsrfTokenQuery.ts";

function RegistrationForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [accessCode, setAccessCode] = useState('');
    const navigate = useNavigate();
    const userQuery = useCurrentUserQuery({ enabled: false });
    const csrfTokenQuery = useCsrfTokenQuery();

    const submitForm = async (e: React.SyntheticEvent) => {
        e.preventDefault()

        const csrfTokenForRegistration = csrfTokenQuery.data;
        if (csrfTokenForRegistration === undefined) {
            throw new Error('Failed to fetch csrf token for login.');
        }

        const response = await fetch('/api/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfTokenForRegistration.token
            },
            body: JSON.stringify({ email, password, firstName, lastName, accessCode })
        });

        if (response.ok) {
            await userQuery.refetch();
            await csrfTokenQuery.refetch();
            navigate('/', { replace: true });
        }
    };

    return (
        <form onSubmit={submitForm} className='access-form'>
            <TextInput label='First name' htmlFor='firstName' type='text' id='firstName' state={firstName} setState={setFirstName} />
            <TextInput label='Last name' htmlFor='lastName' type='text' id='lastName' state={lastName} setState={setLastName} />
            <TextInput label='Email' htmlFor='email' type='email' id='email' state={email} setState={setEmail} />
            <TextInput label='Password' htmlFor='password' type='password' id='password' state={password} setState={setPassword} />
            <TextInput label='Access code' htmlFor='accessCode' type='text' id='accessCode' state={accessCode} setState={setAccessCode} />
            <button className='primary-btn'>Register</button>
        </form>
    );
}

export default RegistrationForm;