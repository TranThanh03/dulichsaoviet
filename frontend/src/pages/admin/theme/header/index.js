import { memo, useState, useEffect, useRef, useMemo, useContext } from 'react';
import { logo, userIcon, background } from 'assets';
import './style.scss';
import { Link, useLocation } from 'react-router-dom';
import { AuthApi } from 'services';
import { AuthContext } from '../masterLayout';

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

        if (response.code === 9997) {
            document.cookie = "tokenAdmin=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "/manage/auth/login";
        }
    } catch (error) {
        console.error("Failed logout:", error);
    }
};

const Header = () => {
    const [isShow, setShow] = useState(false);
    const { fullName } = useContext(AuthContext);

    return (
        <div className="header-manage" style={{ backgroundImage: `url(${background})` }}>
            <div className="header-container" id="manage-page">
                <div className="logo">
                    <img src={logo} alt="logo" />
                    <p>Sao Việt</p>
                </div>
                <div className="content-right">
                    <div className="icon-account">
                        {fullName != null && (
                            <div className="account-info">
                                <img src={userIcon} alt="Tài khoản" onClick={() => {setShow(!isShow)}} />
                                <p onClick={() => {setShow(!isShow)}}>{fullName}</p>
                                {isShow && (
                                    <ul id="slidebar">
                                        <li><Link to="/manage/infor">Thông tin quản trị</Link></li>
                                        <li><Link to="/manage/password">Mật khẩu</Link></li>
                                        <li><span onClick={ handleLogout }>Đăng xuất</span></li>
                                    </ul>
                                )}
                            </div>
                        )}
                    </div>
                </div>
            </div>

            <Navbar />
        </div>
    );
};

export default memo(Header);