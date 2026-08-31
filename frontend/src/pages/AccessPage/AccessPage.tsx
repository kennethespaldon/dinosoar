import AccessForm from '../../components/AccessForm/AccessForm.tsx';
import './AccessPage.css';
import {useState} from "react";

function AccessPage() {
    const [formMode, setFormMode] = useState('login');

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