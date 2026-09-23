import { FLIGHTS_API_BASE_PATH} from '../constants.ts';

const getAllFlights = async () => {
    const response = await fetch(FLIGHTS_API_BASE_PATH);
    return response.json();
};

export default { getAllFlights };