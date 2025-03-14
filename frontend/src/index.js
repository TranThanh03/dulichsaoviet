import React from 'react';
import ReactDOM from 'react-dom/client';
import RouterCustom from './router';
import './style/style.scss';
import Loading from 'component/loading';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <>
        <RouterCustom />
        <Loading />
    </>
);