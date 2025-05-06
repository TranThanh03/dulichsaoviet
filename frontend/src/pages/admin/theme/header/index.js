import { memo, useState, useEffect, useRef, useMemo, useContext } from 'react';
import { logo } from 'assets/user';
import './style.scss';
import { Link, useLocation } from 'react-router-dom';
import { AuthApi } from 'services';
import { AuthContext } from '../masterLayout';
import { FaUserCircle, FaAngleDown } from 'react-icons/fa';

const Navbar = () => {
    const location = useLocation();
    const [activeIndex, setActiveIndex] = useState(null);
    const prevIndexRef = useRef(0);

    const menuItems = useMemo(() => [
        { path: "/manage", label: "Trang chủ" },
        { path: "/manage/customers/index", label: "Khách hàng", topic: "/customers" },
        { path: "/manage/tours/index", label: "Tours", topic: "/tours" },
        { path: "/manage/guides/index", label: "Hướng dẫn viên", topic: "/guides" },
        { path: "/manage/assignments/index", label: "Phân công", topic: "/assignments" },
        { path: "/manage/calendars/index", label: "Lịch đặt", topic: "/calendars" },
        { path: "/manage/dashboards/index", label: "Thống kê", topic: "/dashboards" },
    ], []);

    useEffect(() => {
        const currentIndex = menuItems.findIndex(item =>
            location.pathname.includes(item.topic) || location.pathname === item.path
        );
    
        setActiveIndex(currentIndex === -1 ? prevIndexRef.current : currentIndex);

        prevIndexRef.current = currentIndex === -1 ? prevIndexRef.current : currentIndex;
    }, [location.pathname, menuItems]);

    return (
        <nav>
            <ul className="menu">
                {menuItems.map((item, index) => (
                    <li
                    key={index}
                    className={activeIndex === index ? 'activeNav' : ''}
                    >
                        <Link to={item.path}>{item.label}</Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
};

const handleLogout = async () => {
    try {
        const response = await AuthApi.logoutAdmin();

        if (response.code === 9994) {
            window.location.href = "/manage/auth/login";
        }
    } catch (error) {
        console.error("Failed logout:", error);
    }
};

const Header = () => {
    const [isShow, setShow] = useState(false);
    const { authenticated } = useContext(AuthContext);

    return (
        <header className="header-manage-custom main-header header-one shadow-sm bg-white">
            <div className="header-upper py-10 rpy-0">
                <div className="container-fluid clearfix">
                    <div className="dropdown">
                        <button
                            className="btn btn-link p-0 text-dark d-flex align-items-center"
                            onClick={() => setShow(!isShow)}
                        >
                            <FaUserCircle size={30} />
                            <FaAngleDown className="ms-1" />
                        </button>
                        {isShow && (
                            <ul className="dropdown-menu dropdown-menu-end show mt-2">
                                {authenticated && (
                                    <>
                                        <li><Link className="dropdown-item" to="/customer/infor" onClick={() => setShow(false)}>Thông tin</Link></li>
                                        <li><Link className="dropdown-item" to="/customer/password" onClick={() => setShow(false)}>Mật khẩu</Link></li>
                                        <li><button className="dropdown-item" onClick={handleLogout}>Đăng xuất</button></li>
                                    </>
                                )}
                            </ul>
                        )}
                    </div>
                </div>
            </div>
        </header>
    );
};

export default memo(Header);