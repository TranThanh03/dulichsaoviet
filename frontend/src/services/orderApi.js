import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const OrderApi = {
    getAll: (params) => {
        return axiosInstanceAdmin.get("/api/v1/orders/all", {
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstanceAdmin.get(`/api/v1/orders/detail/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getByUserId: () => {
        return axiosInstance.get("/api/v1/orders/list", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancel: (id) => {
        return axiosInstance.post(`/api/v1/orders/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancelAdmin: (id) => {
        return axiosInstanceAdmin.post(`/api/v1/orders/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    confirm: (id) => {
        return axiosInstanceAdmin.post(`/api/v1/orders/confirm/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    info: () => {
        return axiosInstanceAdmin.get(`/api/v1/orders/info`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    latest: () => {
        return axiosInstanceAdmin.get(`/api/v1/orders/latest`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    statusCounts: () => {
        return axiosInstanceAdmin.get(`/api/v1/orders/counts`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getStatistics: () => {
        return axiosInstanceAdmin.get(`/api/v1/orders/statistics`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    }
};

export default OrderApi;