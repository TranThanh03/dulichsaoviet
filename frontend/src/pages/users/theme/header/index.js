import { memo, useState, useEffect } from "react";
import { logo, searchIcon, clockIcon, userIcon, background } from "assets";
import "./style.scss";
import { Link, useNavigate } from "react-router-dom";
import { AuthApi } from "services";
import getToken from "utils/getToken";

const handleLogout = async () => {
    try {
        const response = await AuthApi.logout();

        if (response.code === 9997) {
            window.location.href = "/";
        }
    } catch (error) {
        console.error("Failed logout:", error);
    }
};

const Header = () => {
    const [searchQuery, setSearchQuery] = useState("");
    const [authenticated, setAuthenticated] = useState(false);
    const [isShow, setShow] = useState(false);
    const navigate = useNavigate();
    const [placeholder, setPlaceholder] = useState("Tìm kiếm Tours");

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
                        {authenticated ? (
                            <div className="account-info">
                                <img src={userIcon} alt="Tài khoản" onClick={() => setShow(!isShow)} />
                                <p onClick={() => setShow(!isShow)}>Tài khoản</p>
                                {isShow && (
                                    <ul id="slidebar">
                                        <li>
                                            <Link to="/users/infor">Thông tin</Link>
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
                        ) : (
                            <div>
                                <img src={userIcon} alt="account" onClick={() => setShow(!isShow)} />
                                <p onClick={() => setShow(!isShow)}>Tài khoản</p>
                                {isShow && (
                                    <ul id="slidebar">
                                        <li>
                                            <Link to="/auth/login">Đăng nhập</Link>
                                        </li>
                                        <li>
                                            <Link to="/auth/register">Đăng ký</Link>
                                        </li>
                                    </ul>
                                )}
                            </div>
                        )}
                    </div>
                </div>
            </div>
        </div>
    );
};

export default memo(Header);