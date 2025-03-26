import { memo, useState, useEffect, createContext } from "react";
import Header from "../header";
import Footer from "../footer";
import { useLocation, useNavigate } from "react-router-dom";
import { AuthApi } from "services";
import getToken from "utils/getToken";

export const AuthContext = createContext(null);

const MasterLayout = ({ children }) => {
    const location = useLocation();
    const path = location.pathname;
    const navigate = useNavigate();

    const isLoginPage = path === "/manage/auth/login";
    const isValidPath = !path.includes("/manage/auth") && !path.includes("/error");

    const [fullName, setFullName] = useState(null);
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

                    if (response?.result?.valid) {
                        setFullName(response.result.fullName);
                        setIsLoading(false);
                    } else {
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
        <AuthContext.Provider value={{ fullName }}>
            <div className="page-saoviet">
                {!isLoginPage && isValidPath && <Header fullName={fullName} />}
                {children}
                {!isLoginPage && isValidPath && <Footer />}
            </div>
        </AuthContext.Provider>
    );
};

export default memo(MasterLayout);