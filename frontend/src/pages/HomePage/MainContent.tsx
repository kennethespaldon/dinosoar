import Flights from '../../views/Flights/Flights.tsx';

const mainContentComponents = {
    ADMIN: {

    },
    INSTRUCTOR: {
        'Students': null,
        'Flights': <Flights />,

    },
    STUDENT: {

    }
};

function MainContent({ currentUserRoles, selectedTab }: { currentUserRoles: string[], selectedTab: string}) {
    if (selectedTab == 'Flights') {
        return (
            <div className='main-content'>
                <div className='main-content-header'>{ selectedTab }</div>
                { currentUserRoles.includes('INSTRUCTOR') && mainContentComponents['INSTRUCTOR'][selectedTab] }
            </div>
        );
    }

    return (
        <div className='main-content'>
            <div className='main-content-header'>Welcome</div>
            <p className='main-content-placeholder'>Select a tab to view</p>
        </div>
    );
}

export default MainContent;