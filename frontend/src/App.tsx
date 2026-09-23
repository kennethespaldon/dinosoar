import './App.css';
import { Navigate, Outlet } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';

function App() {
    const queryClient = useQueryClient();

    if (queryClient.getQueryData(['currentUser'])) {
        return <Navigate to='/home' />;
    }

    return (
        <div className='app-page'>
            <Outlet />
        </div>
    );
}

export default App
