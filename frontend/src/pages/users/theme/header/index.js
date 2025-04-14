import React, { memo, useState, useEffect, useRef, useMemo } from "react";
import { Link, useNavigate, useLocation } from "react-router-dom";
import { FaUserCircle, FaAngleDown, FaSearch, FaRegClock } from "react-icons/fa";
import { logo } from "assets";
import { AuthApi } from "services";
import getToken from "utils/getToken";
import "./style.scss";

const Header = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [authenticated, setAuthenticated] = useState(false);
    const [isShow, setShow] = useState(false);
    const [placeholder, setPlaceholder] = useState("Tìm kiếm tour");
    const navigate = useNavigate();
    const location = useLocation();
    const [activeIndex, setActiveIndex] = useState(null);
    const prevIndexRef = useRef(0);

    const menuItems = useMemo(
        () => [
            { path: "/", label: "Trang chủ" },
            { path: "/about", label: "Giới thiệu", topic: "/about" },
            { path: "/tour/index", label: "Tours", topic: "/tour" },
            { path: "/destinations/index", label: "Điểm đến", topic: "/destinations"},
            { path: "/news/index", label: "Tin tức", topic: "/news" },
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

    useEffect(() => {
        const fetchAuth = async () => {
            try {
                const token = getToken();

                if (token) {
                    const response = await AuthApi.introspect();
                    if (response?.code === 9998) {
                        setAuthenticated(response?.result);
                    }
                }
            } catch (error) {}
        };

        fetchAuth();
    }, []);

    const handleLogout = async () => {
        try {
            const response = await AuthApi.logout();

            if (response.code === 9997) {
                window.location.href = "/";
            }
        } catch (error) {
            console.error("Logout error:", error);
        }
    };

    const handleSearch = (e) => {
        e.preventDefault();
        if (searchQuery.trim() !== "") {
            navigate(`/tours/search?p=${encodeURIComponent(searchQuery)}`);
        } else {
            setPlaceholder("Vui lòng nhập tour...");
        }
    };

    return (
        <header className="shadow-sm bg-white py-2 user-header">
            <div className="container-fluid d-flex align-items-center justify-content-between">

                <div className="d-flex align-items-center">
                    <img src={logo} alt="Logo" id="img-logo"/>
                    <span className="fw-bold fs-4 ms-2 title">Sao Việt</span>
                </div>

                <ul className="nav rounded-pill bg-light px-3 py-1">
                    {menuItems.map((item, index) => (
                        <li key={index} className="nav-item">
                            <Link className={`nav-link ${activeIndex === index ? "active" : ""}`} to={item.path}>{item.label}</Link>
                        </li>
                    ))}
                </ul>

                <div className="d-flex align-items-center gap-3">
                    <form onSubmit={handleSearch} className="d-flex align-items-center bg-light rounded-pill px-3 py-1 search-group">
                        <input
                            type="text"
                            className="form-control bg-transparent"
                            placeholder={placeholder}
                            value={searchQuery}
                            onChange={(e) => setSearchQuery(e.target.value)}
                        />
                        <button type="submit" className="btn p-0 text-dark">
                            <FaSearch />
                        </button>
                    </form>

                    <Link to="/calendars/index" className="text-dark fs-5">
                        <FaRegClock size={26} />
                    </Link>

                    <div className="dropdown">
                        <button
                            className="btn btn-link p-0 text-dark d-flex align-items-center"
                            onClick={() => setShow(!isShow)}
                        >
                            <FaUserCircle size={28} />
                            <FaAngleDown className="ms-1" />
                        </button>
                        {isShow && (
                            <ul className="dropdown-menu dropdown-menu-end show mt-2">
                                {authenticated ? (
                                    <>
                                        <li><Link className="dropdown-item" to="/customer/infor" onClick={() => setShow(!isShow)}>Thông tin</Link></li>
                                        <li><Link className="dropdown-item" to="/customer/password" onClick={() => setShow(!isShow)}>Mật khẩu</Link></li>
                                        <li><button className="dropdown-item" onClick={handleLogout}>Đăng xuất</button></li>
                                    </>
                                ) : (
                                    <>
                                        <li><Link className="dropdown-item" to="/auth/login" onClick={() => setShow(!isShow)}>Đăng nhập</Link></li>
                                        <li><Link className="dropdown-item" to="/auth/register" onClick={() => setShow(!isShow)}>Đăng ký</Link></li>
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