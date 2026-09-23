import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.tsx';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { QueryClientProvider, QueryClient } from '@tanstack/react-query';
import AccessPage from "./pages/AccessPage/AccessPage.tsx";
import HomePage from './pages/HomePage/HomePage.tsx';

const queryClient = new QueryClient();

const router = createBrowserRouter([
    {
        path: '/',
        Component: App,
        children: [
            { index: true, element: <HomePage /> },
            { path: 'login', element: <AccessPage /> },
        ]
    }
    // Add path to 404 (NOT FOUND) pages using path: '*'
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <QueryClientProvider client={queryClient}>
          <RouterProvider router={router} />
      </QueryClientProvider>
  </StrictMode>
)
