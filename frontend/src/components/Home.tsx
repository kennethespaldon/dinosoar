import LogoutButton from './LogoutButton.tsx';
import { Navigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';

function Home() {
    const queryClient = useQueryClient();

    if (!queryClient.getQueryData(['currentUser'])) {
        return <Navigate to='/login' />;
    }

    return (
      <>
          <LogoutButton />
      </>
    );
}

export default Home;