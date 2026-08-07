import {useQuery} from "@tanstack/react-query";
import authService from '../services/authService.ts';
import type { QueryOptions } from "../types/shared.ts";
import type { User } from '../types/user.ts';

const useCurrentUserQuery = (options?: QueryOptions<User, Error>) => {
    return useQuery({
        queryKey: ['currentUser'],
        queryFn: authService.getCurrentUser,
        ...options
    });
};

export default useCurrentUserQuery