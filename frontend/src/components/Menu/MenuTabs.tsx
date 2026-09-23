import type { User } from "../../types/user.ts";
import {UserGroup, TicketsPlane, LibraryBig} from 'lucide-react';

const instructorMenuTabs = [
    { title: 'Students', icon: <UserGroup /> },
    { title: 'Flights', icon: <TicketsPlane /> },
    { title: 'Resources', icon: <LibraryBig /> }
];

function MenuTabs({ currentUser, selectedTab, setSelectedTab }: { currentUser: User, selectedTab: string, setSelectedTab: React.Dispatch<React.SetStateAction<string>> }) {

    // if (selectedTab == 'Flights') {
    //     return <FlightLogMenu setSelectedTab={setSelectedTab}/>;
    // }
    return (
        <div className='main-menu-tabs'>
            {currentUser.roles.includes('INSTRUCTOR') &&
                instructorMenuTabs.map(tab => {
                    // TODO: Turn this div into <MenuTab />
                    return <div onClick={() => {
                        if (selectedTab === tab.title) {
                            setSelectedTab('none');
                        } else {
                            setSelectedTab(tab.title)
                        }
                    }} className={`main-menu-tab ${selectedTab == tab.title ? 'main-menu-tab-selected' : '' }`}>{tab.icon} {tab.title}</div>;
                })
            }
        </div>
    );
}

export default MenuTabs;