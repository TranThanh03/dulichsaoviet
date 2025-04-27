import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const CustomerApi = {
    getAll: (params) => {
        return axiosInstanceAdmin.get("/api/v1/customers", {
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/customers/${id}`);
    },
    infor: () => {
        return axiosInstance.get("/api/v1/customers/infor", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/v1/customers", data);
    },
    update: (data) => {
        return axiosInstance.put(`/api/v1/customers`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    changePassword: (data) => {
        return axiosInstance.put(`/api/v1/customers/password`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/customers/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    latest: () => {
        return axiosInstanceAdmin.get(`/api/v1/customers/latest`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    activate: (id) => {
        return axiosInstance.patch(`/api/v1/customers/activate/${id}`);
    },
};

export default CustomerApi;