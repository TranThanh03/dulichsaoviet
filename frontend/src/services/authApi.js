import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const AuthApi = {
    login: (data) => {
        return axiosInstance.post("/api/auth/login", data);
    },
    loginAdmin: (data) => {
        return axiosInstance.post("/api/auth/admin/login", data);
    },
    logout: () => {
        return axiosInstance.get("/api/auth/logout", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    logoutAdmin: () => {
        return axiosInstance.get("/api/auth/logout", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    introspect: () => {
        return axiosInstance.get("/api/auth/introspect", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    introspectAdmin: () => {
        return axiosInstance.get("/api/auth/admin/introspect", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
};

export default AuthApi;