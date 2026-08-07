import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './LoginForm.css';
import useCsrfTokenQuery from "../hooks/useCsrfTokenQuery.ts";
import useCurrentUserQuery from "../hooks/useCurrentUserQuery.ts";

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
            navigate('/home', { replace: true });
        }
    };

    return (
        <form onSubmit={submitForm} className='sign-in-form'>
            <div>
                <label htmlFor='email'>Email</label>
                <input
                    value={email}
                    onChange={e => setEmail(e.target.value)}
                    type='email'
                    id='email'
                    required
                />
            </div>

            <div>
                <label htmlFor='password'>Password</label>
                <input
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    type='password'
                    id='password'
                    required
                />
            </div>

            <button>Log in</button>
        </form>
    );
}

export default LoginForm
