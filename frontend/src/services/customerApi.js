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
    inforAdmin: () => {
        return axiosInstanceAdmin.get("/api/v1/customers/infor", {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/v1/customers", data);
    },
    update: (id, data) => {
        return axiosInstance.put(`/api/v1/customers/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    changePassword: (id, data) => {
        return axiosInstance.put(`/api/v1/customers/password/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    updateAdmin: (id, data) => {
        return axiosInstanceAdmin.put(`/api/v1/customers/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    changePasswordAdmin: (id, data) => {
        return axiosInstanceAdmin.put(`/api/v1/customers/password/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
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
    activateByAdmin: (id) => {
        return axiosInstanceAdmin.patch(`/api/v1/customers/activate/${id}`);
    },
};

export default CustomerApi;