import { memo } from 'react';
import './style.scss';

const Footer = () => {
    return (
        <footer className="footer-manage pt-2 no-print">
            <div className="container">
                <div className="py-1 text-center">
                    <span className="fw-bold">© 2025 Sao Việt</span>
                </div>
            </div>
        </footer>
    );
};

export default memo(Footer);