import { memo } from 'react';
import Header from '../header';
import Footer from '../footer';
import { useLocation } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';

const MasterLayout = ({ children, ...props }) => {
    const location = useLocation();
    const path = location.pathname;

    const isValidPath = !path.includes("/auth") && !path.includes("/error") && !path.includes("/customers/activate");

    return (
        <div className="page-saoviet" {...props}>
            {isValidPath && <Header />}
            {<ToastContainer />}
            {children}
            {isValidPath && <Footer />}
        </div>
    );
};

export default memo(MasterLayout);