const flightMenuTitles = [
    'Details',
    'Focus',
    'Feedback'
];

function AddFlightMenu({ selectedTab, setSelectedTab }: { selectedTab: string, setSelectedTab: React.Dispatch<React.SetStateAction<string>>}) {
    return (
      <div className='add-flight-menu'>
          {flightMenuTitles.map(title => <div className={`flight-menu-tab flight-menu-tab-${selectedTab.toLowerCase()} ${selectedTab === title ? 'flight-menu-tab-selected' : ''}`} onClick={() => setSelectedTab(title)}>{title}</div>)}
      </div>
    );
}

export default AddFlightMenu;