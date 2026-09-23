interface User {
    id: number,
    email: string,
    firstName: string,
    lastName: string,
    roles: string[],
    profileImageId: number
}

export type { User }