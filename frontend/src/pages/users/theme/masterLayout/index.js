import { memo } from 'react';
import Header from '../header';
import Navbar from '../navbar';
import Footer from '../footer';
import { useLocation } from 'react-router-dom';

const MasterLayout = ({ children, ...props }) => {
    const location = useLocation();
    const path = location.pathname;

    const isValidPath = !path.includes("/auth") && !path.includes("/error");

    return (
        <div className="page-saoviet" {...props}>
            {isValidPath && <Header />}
            {isValidPath && <Navbar/>}
            {children}
            {isValidPath && <Footer />}
        </div>
    );
};

export default memo(MasterLayout);