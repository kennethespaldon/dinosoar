import TextInput from "../../components/TextInput/TextInput.tsx";
import React, {useState} from "react";
import {useNavigate} from "react-router-dom";
import useCurrentUserQuery from "../../hooks/useCurrentUserQuery.ts";
import useCsrfTokenQuery from "../../hooks/useCsrfTokenQuery.ts";

function LoginForm() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const navigate = useNavigate();
    const userQuery = useCurrentUserQuery({ enabled: false });
    const csrfTokenQuery = useCsrfTokenQuery();

    const submitForm = async (e: React.SyntheticEvent) => {
        e.preventDefault();
        const csrfTokenForLogin = csrfTokenQuery.data;
        if (csrfTokenForLogin === undefined) {
            throw new Error('Failed to fetch csrf token for login.');
        }

        const response = await fetch('/api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'X-CSRF-TOKEN': csrfTokenForLogin.token
            },
            body: JSON.stringify({ email, password })
        });

        if (response.ok) {
            await userQuery.refetch();
            await csrfTokenQuery.refetch();
            navigate('/', { replace: true });
        }
    };

    return (
        <form onSubmit={submitForm} className='access-form'>
            <TextInput label='Email' htmlFor='email' type='email' id='email' state={email} setState={setEmail} />
            <TextInput label='Password' htmlFor='password' type='password' id='password' state={password} setState={setPassword} />
            <button className='primary-btn'>Log in</button>
        </form>
    );
}

export default LoginForm;