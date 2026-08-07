import { AUTH_API_BASE_PATH } from "../constants.ts";

const getCurrentUser = async () => {
    const response = await fetch(`${AUTH_API_BASE_PATH}/current-user`);

    if (!response.ok) {
        throw new Error('Failed to fetch current user.');
    }

    return response.json();
};

const getCsrfToken = async () => {
    const response = await fetch(`${AUTH_API_BASE_PATH}/csrf-token`);

    if (!response.ok) {
        throw new Error('Failed to fetch csrf token.');
    }

    return response.json();
};

export default {
    getCurrentUser,
    getCsrfToken,
}