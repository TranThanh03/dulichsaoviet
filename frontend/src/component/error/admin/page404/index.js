import { memo } from 'react';
import { Link } from 'react-router-dom';
import './style.scss';

const Page404 = () => {
    return (
        <div className="error-404-container">
            <div className="main">
                <div className="error-code">404</div>
                <p className="error-message">Không tìm thấy trang bạn đang tìm kiếm!</p>
                <Link to="/manage" className="back-home">Quay lại trang chủ</Link>
            </div>
        </div>
    )
}

export default memo(Page404)