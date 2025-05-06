import { memo, useState, useEffect, createContext } from "react";
import Footer from "../footer";
import { useLocation, useNavigate } from "react-router-dom";
import { AuthApi } from "services";
import getToken from "utils/getToken";
import Sidebar from "../sidebar";

export const AuthContext = createContext(null);

const MasterLayout = ({ children }) => {
    const location = useLocation();
    const path = location.pathname;
    const navigate = useNavigate();

    const isLoginPage = path === "/manage/auth/login";
    const isValidPath = !path.includes("/manage/auth") && !path.includes("/error");

    const [authenticated, setAuthenticated] = useState(false);
    const [isLoading, setIsLoading] = useState(true);

    useEffect(() => {
        if (isLoginPage) {
            setIsLoading(false);
            return;
        }

        const fetchAuth = async () => {
            try {
                const tokenAdmin = getToken(true);

                if (tokenAdmin) {
                    const response = await AuthApi.introspectAdmin();

                    if (response?.code === 9995) {
                        setAuthenticated(response?.result);
                        setIsLoading(false);
                    }
                    else {
                        navigate("/manage/auth/login");
                    }
                }
                else {
                    navigate("/manage/auth/login");
                }
            } catch (error) {
                navigate("/manage/auth/login");
            }
        };

        fetchAuth();
    }, [navigate, isLoginPage]);

    if (isLoading) {
        return null;
    }

    return (
        <AuthContext.Provider value={{ authenticated }}>
            <div className="page-saoviet nav-md">
                <div className="container body">
                    <div className="main_container">
                        {!isLoginPage && isValidPath && <Sidebar authenticated={authenticated} />}
                        {children}
                        {!isLoginPage && isValidPath && <Footer />}
                    </div>
                </div>     
            </div>
        </AuthContext.Provider>
    );
};

export default memo(MasterLayout);