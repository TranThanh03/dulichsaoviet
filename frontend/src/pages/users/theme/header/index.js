import { memo, useState, useEffect, useRef, useMemo } from "react";
import { logo, searchIcon, clockIcon, userIcon, background } from "assets";
import "./style.scss";
import { Link, useLocation, useNavigate } from "react-router-dom";
import { AuthApi } from "services";
import getToken from "utils/getToken";

const Navbar = () => {
    const location = useLocation();
    const [activeIndex, setActiveIndex] = useState(null);
    const prevIndexRef = useRef(0);

    const menuItems = useMemo(
        () => [
            { path: "/", label: "🏠Trang chủ" },
            { path: "/tours/category", label: "✈️Tours", topic: "/tours" },
            { path: "/guides/index", label: "🧑‍💼Hướng dẫn viên", topic: "/guides" },
            { path: "/hotels/index", label: "🏩Khách sạn", topic: "/hotels" },
            { path: "/news/index", label: "📰Tin tức", topic: "/news" },
        ],
        []
    );

    useEffect(() => {
        const currentIndex = menuItems.findIndex(
            (item) => location.pathname.startsWith(item.topic) || location.pathname === item.path
        );

        setActiveIndex(currentIndex === -1 ? prevIndexRef.current : currentIndex);
        prevIndexRef.current = currentIndex === -1 ? prevIndexRef.current : currentIndex;
    }, [location.pathname, menuItems]);

    return (
        <nav>
            <ul className="menu">
                {menuItems.map((item, index) => (
                    <li key={index} className={activeIndex === index ? "activeNav" : ""}>
                        <Link to={item.path}>{item.label}</Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
};

const handleLogout = async () => {
    try {
        const response = await AuthApi.logout();

        if (response.code === 9997) {
            document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
            window.location.href = "/";
        }
    } catch (error) {
        console.error("Failed logout:", error);
    }
};

const Header = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [fullName, setFullName] = useState();
    const [isShow, setShow] = useState(false);
    const navigate = useNavigate();
    const [placeholder, setPlaceholder] = useState("Tìm kiếm Tours");

    useEffect(() => {
        const fetchAuth = async () => {
            try {
                const token = getToken();

                if (token) {
                    const response = await AuthApi.introspect();

                    if (response?.result?.valid) {
                        setFullName(response.result.fullName);
                    }
                }
            } catch (error) {}
        };

        fetchAuth();
    }, []);

    const handleSearch = (e) => {
        e.preventDefault();

        if (searchQuery.trim() !== "") {
            navigate(`/tours/search?p=${encodeURIComponent(searchQuery)}`);
        } else {
            setPlaceholder("Vui lòng nhập tour cần tìm...");
        }
    };

    return (
        <div className="header" style={{ backgroundImage: `url(${background})` }}>
            <div className="header-container">
                <div className="logo">
                    <img src={logo} alt="logo" />
                    <p>Sao Việt</p>
                </div>
                <div className="search">
                    <form onSubmit={handleSearch} className="sub-search">
                        <input
                            type="text"
                            placeholder={placeholder}
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                        <button type="submit">
                            <img src={searchIcon} alt="Tìm kiếm" />
                        </button>
                    </form>
                </div>
                <div className="content-right">
                    <div className="icon-calendar">
                        <Link to="/calendars/index">
                            <img src={clockIcon} alt="Lịch đặt" />
                            <p>Lịch đặt</p>
                            <span id="notification-calendar">.</span>
                        </Link>
                    </div>
                    <div className="icon-account">
                        {fullName == null ? (
                            <Link to="/auth/login">
                                <img src={userIcon} alt="Đăng nhập" />
                                <p>Đăng nhập</p>
                            </Link>
                        ) : (
                            <div className="account-info">
                                <img src={userIcon} alt="Tài khoản" onClick={() => setShow(!isShow)} />
                                <p onClick={() => setShow(!isShow)}>{fullName}</p>
                                {isShow && (
                                    <ul id="slidebar">
                                        <li>
                                            <Link to="/users/infor">Thông tin khách hàng</Link>
                                        </li>
                                        <li>
                                            <Link to="/users/password">Mật khẩu</Link>
                                        </li>
                                        <li>
                                            <span onClick={handleLogout}>Đăng xuất</span>
                                        </li>
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