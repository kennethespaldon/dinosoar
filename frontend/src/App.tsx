import './App.css';
import { Link, Navigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';

function App() {
    const queryClient = useQueryClient();

    if (queryClient.getQueryData(['currentUser'])) {
        return <Navigate to='/home' />;
    }

    return (
        <div>
            <Link to='/login'>Log in</Link>
        </div>
    );
}

export default App
