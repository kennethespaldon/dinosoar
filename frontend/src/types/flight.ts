import { type User } from './user.ts';

interface Aircraft {
    id: number,
    tailNumber: string
}

interface Note {
    text: string
}

interface ActionItem {
    text: string,
    notes: Array<Note>
}

interface Flight {
    date: string,
    aircraft: Aircraft,
    duration: number,
    crossCountry: boolean,
    nightFlight: boolean,
    actionItems: Array<ActionItem>,
    thingsDoneWell: string,
    thingsToImprove: string,
    student: User,
    instructor: User,
    feedback: string
}

export type { Flight, ActionItem, Note, Aircraft };