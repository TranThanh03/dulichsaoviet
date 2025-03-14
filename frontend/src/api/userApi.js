import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const UserApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/users", {
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/users/${id}`);
    },
    getByToken: () => {
        return axiosInstance.get("/api/users/infor", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    getByTokenAdmin: () => {
        return axiosInstance.get("/api/users/infor", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/users", data);
    },
    update: (id, data) => {
        return axiosInstance.put(`/api/users/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    changePassword: (id, data) => {
        return axiosInstance.put(`/api/users/password/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    updateAdmin: (id, data) => {
        return axiosInstance.put(`/api/users/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    changePasswordAdmin: (id, data) => {
        return axiosInstance.put(`/api/users/password/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstance.delete(`/api/users/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    latest: () => {
        return axiosInstance.get(`/api/users/latest`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    }
};

export default UserApi;