import LogoutButton from '../components/LogoutButton.tsx';
import { Navigate } from 'react-router-dom';
import { useQueryClient } from '@tanstack/react-query';
import { useState } from "react";
import Menu from "../components/Menu.tsx";
import type { User } from "../types/user.ts";
import MainContent from "../components/MainContent.tsx";

function HomePage() {
    const [ selectedTab, setSelectedTab ] = useState<string>('none');
    const queryClient = useQueryClient();
    const currentUser: User | undefined = queryClient.getQueryData(['currentUser']);
    console.log(queryClient.getQueryData(['currentUser']))

    if (!currentUser) {
        return <Navigate to='/login' />;
    }

    return (
        <div>
            <div>
                <div>Dinosoar</div>
                <Menu currentUser={currentUser} selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
            </div>

            <MainContent currentUserRoles={currentUser.roles} selectedTab={selectedTab} />
            {/*<LogoutButton />*/}
        </div>
    );
}

export default HomePage;