import type {UseQueryOptions} from "@tanstack/react-query";

type QueryOptions<TData = unknown, TError = Error> = Partial<Omit<UseQueryOptions<TData, TError>, 'queryKey' | 'queryFn'>>;

export type { QueryOptions }