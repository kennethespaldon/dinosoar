import { useQuery } from "@tanstack/react-query";
import authService from '../services/authService.ts';
import type {QueryOptions} from "../types/shared.ts";
import type {CsrfToken} from '../types/auth.ts'

const useCsrfTokenQuery = (options?: QueryOptions<CsrfToken, Error>) => {
    return useQuery({
        queryKey: ['csrfToken'],
        queryFn: authService.getCsrfToken,
        ...options
    });
};

export default useCsrfTokenQuery