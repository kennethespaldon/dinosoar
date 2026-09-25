import MainMenuTab from "./MainMenuTab.tsx";
import type { User } from "../../../types/user.ts";
import {LibraryBig, TicketsPlane, UserGroup} from "lucide-react";

interface MenuProps {
    currentUser: User,
    selectedTab: string,
    setSelectedTab: React.Dispatch<React.SetStateAction<string>>
}

const instructorMenuTabs = [
    { title: 'Students', icon: <UserGroup /> },
    { title: 'Flights', icon: <TicketsPlane /> },
    { title: 'Resources', icon: <LibraryBig /> }
];

function MainMenu({ currentUser, selectedTab, setSelectedTab }: MenuProps) {
    return (
        <div className='main-menu'>
            <div className='main-menu-header'>Dinosoar</div>
            <div className='main-menu-tabs'>
                {currentUser.roles.includes('INSTRUCTOR') &&
                    instructorMenuTabs.map(tab => {
                        return <MainMenuTab tab={tab} selectedTab={selectedTab} setSelectedTab={setSelectedTab} />;
                    })
                }
            </div>
        </div>
    );
}

export default MainMenu;