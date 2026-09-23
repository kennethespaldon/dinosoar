const headerTitles = [
    'Date',
    'Student',
    'Aircraft',
    'Cross Country',
    'Night Flight',
    'Flight Time'
]

function FlightHistory() {
    return (
        <div className='flight-list-row flight-list-row-header'>
            {headerTitles.map(title => (
                <div
                    className={`
                        flight-list-col 
                        flight-list-col-${title.toLowerCase().replaceAll(' ', '-')}
                        flight-list-col-${title.toLowerCase().replaceAll(' ', '-')}-header`
                    }>
                    { title }
                </div>
            ))}
        </div>
    );
}

export default FlightHistory;