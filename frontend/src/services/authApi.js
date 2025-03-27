import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const AuthApi = {
    login: (data) => {
        return axiosInstance.post("/api/v1/auth/login", data);
    },
    loginAdmin: (data) => {
        return axiosInstanceAdmin.post("/api/v1/auth/admin/login", data);
    },
    logout: () => {
        return axiosInstance.get("/api/v1/auth/logout", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    logoutAdmin: () => {
        return axiosInstanceAdmin.get("/api/v1/auth/logout", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    introspect: () => {
        return axiosInstance.get("/api/v1/auth/introspect", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    introspectAdmin: () => {
        return axiosInstanceAdmin.get("/api/v1/auth/admin/introspect", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
};

export default AuthApi;