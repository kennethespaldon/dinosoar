import { useQueryClient } from '@tanstack/react-query';
import { useNavigate } from 'react-router-dom';
import useCsrfTokenQuery from '../../../hooks/useCsrfTokenQuery.ts';

function LogoutButton() {
    const navigate = useNavigate();
    const queryClient = useQueryClient();
    const csrfTokenQuery = useCsrfTokenQuery();

    const logout = async () => {
        const csrfToken = csrfTokenQuery.data;
        if (csrfToken === undefined) {
            throw new Error('Failed to get csrf token.');
        }

        const response = await fetch('/api/auth/logout', {
            method: 'POST',
            headers: {
                'X-CSRF-TOKEN': csrfToken.token
            }
        });

        if (response.ok) {
            queryClient.setQueryData(['currentUser'], null);
            queryClient.setQueryData(['csrfToken'], null);
            navigate('/login', { replace: true });
        }
    };

    return (
        <button onClick={() => logout()}>Log out</button>
    );
}

export default LogoutButton;