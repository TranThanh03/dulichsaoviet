import { memo, useState, useEffect, useRef, useMemo } from "react";
import "./style.scss";
import { Link, useLocation } from "react-router-dom";

const Navbar = () => {
    const location = useLocation();
    const [activeIndex, setActiveIndex] = useState(null);
    const prevIndexRef = useRef(0);
    const [scrolled, setScrolled] = useState(false);

    const menuItems = useMemo(
        () => [
            { path: "/", label: "🏠Trang chủ" },
            { path: "/tours/category", label: "✈️Tours", topic: "/tours" },
            { path: "/destinations/index", label: "🚩Điểm đến", topic: "/destinations" },
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

    useEffect(() => {
        const handleScroll = () => {
            setScrolled(window.scrollY > 140);
        };

        window.addEventListener("scroll", handleScroll);

        return () => window.removeEventListener("scroll", handleScroll);
    }, []);

    return (
        <nav className={`menu ${scrolled ? "scrolled" : ""}`}>
            <ul>
                {menuItems.map((item, index) => (
                    <li key={index} className={activeIndex === index ? "activeNav" : ""}>
                        <Link to={item.path}>{item.label}</Link>
                    </li>
                ))}
            </ul>
        </nav>
    );
};

export default memo(Navbar);