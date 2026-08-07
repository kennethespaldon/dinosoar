import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.tsx';
import LoginForm from './components/LoginForm.tsx';
import Home from './components/Home.tsx';
import './index.css';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import { QueryClientProvider, QueryClient } from '@tanstack/react-query';

const queryClient = new QueryClient();

const router = createBrowserRouter([
    { path: '/', element: <App /> },
    { path: '/login', element: <LoginForm /> },
    { path: '/home', element: <Home /> },
    // Add path to 404 (NOT FOUND) pages using path: '*'
]);

createRoot(document.getElementById('root')!).render(
  <StrictMode>
      <QueryClientProvider client={queryClient}>
          <RouterProvider router={router} />
      </QueryClientProvider>
  </StrictMode>
)
