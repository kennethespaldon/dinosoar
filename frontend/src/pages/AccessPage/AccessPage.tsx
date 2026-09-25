import AccessForm from './AccessForm/AccessForm.tsx';
import './AccessPage.css';
import {useState} from "react";
import {useQueryClient} from "@tanstack/react-query";
import {Navigate} from "react-router-dom";

function AccessPage() {
    const [formMode, setFormMode] = useState('login');
    const queryClient = useQueryClient();

    if (queryClient.getQueryData(['currentUser'])) {
        return <Navigate to='/' replace />;
    }

    return (
        <div className='access-page'>
            {formMode === 'login' &&
                <h1 className='access-page-login-title'>
                    Welcome
                    <span className='hide-on-mobile'>, <span className='access-page-span'>aviator</span></span>.
                </h1>
            }
            <AccessForm formMode={formMode} setFormMode={setFormMode}/>
        </div>
    )
}

export default AccessPage;