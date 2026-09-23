import MenuTabs from "./MenuTabs.tsx";
import type { User } from "../../types/user.ts";

interface MenuProps {
    currentUser: User,
    selectedTab: string,
    setSelectedTab: React.Dispatch<React.SetStateAction<string>>
}

function Menu({ currentUser, selectedTab, setSelectedTab }: MenuProps) {
    return (
        <div className='main-menu'>
            <div className='main-menu-header'>Dinosoar</div>
            <MenuTabs currentUser={currentUser} selectedTab={selectedTab} setSelectedTab={setSelectedTab} />
        </div>
    );
}

export default Menu;