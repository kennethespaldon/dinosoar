import LogoutButton from './LogoutButton.tsx';
import { Navigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';

function Home() {
    const queryClient = useQueryClient();
    console.log(queryClient.getQueryData(['currentUser']))

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