import React, { useEffect } from 'react';
import ReactDOM from 'react-dom/client';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import RouterCustom from './router';
import './style/style.scss';
import Loading from 'component/loading';
import AOS from 'aos';
import 'aos/dist/aos.css';
import '@fortawesome/fontawesome-free/css/all.min.css';

const root = ReactDOM.createRoot(document.getElementById('root'));

const App = () => {
    useEffect(() => {
        AOS.init({ 
            duration: 1500,
            once: false,
            mirror: true,
            easing: 'ease',
        });

        AOS.refresh();
    }, []);

    return (
        <>
            <RouterCustom />
            <Loading />
        </>
    )
};

root.render(<App />);
