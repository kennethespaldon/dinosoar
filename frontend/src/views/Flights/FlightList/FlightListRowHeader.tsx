import {CalendarDays, User, Plane, Map, Moon, Clock, type LucideIcon} from 'lucide-react';

const headerTitles = [
    {text: 'Date', icon: CalendarDays},
    {text: 'Student', icon: User},
    {text: 'Aircraft', icon: Plane},
    {text: 'Cross Country', icon: Map},
    {text: 'Night Flight', icon: Moon},
    {text: 'Flight Time', icon: Clock}
]

function FlightHistory() {
    return (
        <div className='flight-list-row flight-list-row-header'>
            {headerTitles.map(({ text, icon: Icon }: { text: string, icon: LucideIcon }) => (
                <div
                    className={`
                        flight-list-col 
                        flight-list-col-${text.toLowerCase().replaceAll(' ', '-')}
                        flight-list-col-${text.toLowerCase().replaceAll(' ', '-')}-header`
                    }>
                    <Icon className='flight-list-row-header-icon' size={20}/> { text }
                </div>
            ))}
        </div>
    );
}

export default FlightHistory;