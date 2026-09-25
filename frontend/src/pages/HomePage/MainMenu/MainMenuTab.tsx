interface MainMenuTabProps {
    tab: { title: string, icon: React.ReactElement },
    selectedTab: string,
    setSelectedTab: React.Dispatch<React.SetStateAction<string>>
}

function MainMenuTab({ tab, selectedTab, setSelectedTab }: MainMenuTabProps) {
    return (
        <div onClick={() => {
            if (selectedTab === tab.title) {
                setSelectedTab('none');
            } else {
                setSelectedTab(tab.title)
            }
        }} className={`main-menu-tab ${selectedTab == tab.title ? 'main-menu-tab-selected' : '' }`}>{tab.icon} {tab.title}</div>
    );
}

export default MainMenuTab;