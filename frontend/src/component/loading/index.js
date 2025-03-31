import { memo } from 'react';
import './style.scss';

const Loading = () => {
    return (
        <div class="global-loader d-flex justify-content-center align-items-center position-fixed top-50 start-50 translate-middle">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>
    )
}

export default memo(Loading)