import { memo } from 'react';
import './style.scss';

const Loading = () => {
    return (
        <div className="global-loader">
            <div className="loader">
                <span></span>
                <span></span>
                <span></span>
            </div>
        </div>
    )
}

export default memo(Loading)