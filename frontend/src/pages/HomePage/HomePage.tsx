import LogoutButton from '../../components/LogoutButton.tsx';
import { Navigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';
import { useState } from "react";
import type { User } from "../../types/user.ts";
import MainContent from "./MainContent.tsx";
import './HomePage.css';
import Menu from "../../components/Menu/Menu.tsx";

function HomePage() {
    const [ selectedTab, setSelectedTab ] = useState<string>('none');
    const queryClient = useQueryClient();
    const currentUser: User | undefined = queryClient.getQueryData(['currentUser']);
    console.log(queryClient.getQueryData(['currentUser']))

    if (!currentUser) {
        return <Navigate to='/login' />;
    }

    return (
        <div className='home-page'>
            <Menu currentUser={currentUser} selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
            <MainContent currentUserRoles={currentUser.roles} selectedTab={selectedTab} />
            {/*<LogoutButton />*/}
        </div>
    );
}

export default HomePage;