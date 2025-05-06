import { memo, useState, useEffect, useRef, useMemo, useContext } from 'react';
import { userAvatar } from 'assets';
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

const Sidebar = () => {
    const [isShow, setShow] = useState(false);
    const { authenticated } = useContext(AuthContext);

    return (
        <div className="sidebar-manage">
            <div className="col-md-3 left_col">
                <div className="left_col scroll-view">
                    <div className="clearfix"></div>

                    <div className="profile clearfix">
                        <div className="profile_pic">
                            <img src={userAvatar} alt="avatar" className="img-circle profile_img" />
                        </div>
                        <div className="profile_info">
                            <span>Xin chào,</span>
                            <h2>Admin</h2>
                        </div>
                    </div>

                    <br />

                    <div id="sidebar-menu" className="main_menu_side hidden-print main_menu">
                        <div className="menu_section">
                            <h3>Tổng quan</h3>
                            <ul className="nav side-menu">
                                <li><a href="{{ route('admin.dashboard') }}"><i className="fa fa-dashboard"></i> Dashboard</a> </li>
                                <li><a href="{{ route('admin.admin') }}"><i className="fa fa-table"></i> Quản lý Admin</a> </li>
                                <li><a href="{{ route('admin.users') }}"><i className="fa fa-table"></i> Quản lý người dùng</a> </li>
                                <li><a><i className="fa fa-table"></i> Quản lý Tours<span className="fa fa-chevron-down"></span></a>
                                    <ul className="nav child_menu">
                                        <li><a href="{{ route('admin.page-add-tours') }}">Thêm Tours</a></li>
                                        <li><a href="{{ route('admin.tours') }}">Danh sách Tours</a></li>
                                    </ul>
                                </li>

                                <li><a href="{{ route('admin.booking') }}"><i className="fa fa-home"></i> Quản lý Booking</a> </li>
                                <li><a href="{{ route('admin.contact') }}"><i className="fa fa-envelope-o"></i> Liên hệ </a> </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <div className="top_nav">
                <div className="nav_menu">
                    <div className="nav toggle">
                        <a id="menu_toggle"><i className="fa fa-bars"></i></a>
                    </div>
                    <nav className="nav navbar-nav">
                        <ul className=" navbar-right">
                            <li className="nav-item dropdown open" style={{ paddingLeft: "15px"}}>
                                {/* <a href="javascript:;" className="user-profile dropdown-toggle" aria-haspopup="true" id="navbarDropdown"
                                    data-toggle="dropdown" aria-expanded="false">
                                    <img src="{{ asset('admin/assets/images/user-profile/avt_admin.jpg') }}" alt="">
                                    @if (session()->has('admin'))
                                        {{ session('admin') }}
                                    @endif
                                </a>
                                <div className="dropdown-menu dropdown-usermenu pull-right" aria-labelledby="navbarDropdown">
                                    <a className="dropdown-item" href="javascript:;"> Thông tin cá nhân</a>
                                    <a className="dropdown-item" href="{{ route('admin.logout') }}"><i
                                            className="fa fa-sign-out pull-right"></i> Đăng xuất</a>
                                </div> */}
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    );
};

export default memo(Sidebar);