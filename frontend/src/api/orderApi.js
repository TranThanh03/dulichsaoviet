import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const OrderApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/orders/all", {
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/orders/detail/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getByUserId: () => {
        return axiosInstance.get("/api/orders/list", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancel: (id) => {
        return axiosInstance.post(`/api/orders/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancelAdmin: (id) => {
        return axiosInstance.post(`/api/orders/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    confirm: (id) => {
        return axiosInstance.post(`/api/orders/confirm/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    info: () => {
        return axiosInstance.get(`/api/orders/info`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    latest: () => {
        return axiosInstance.get(`/api/orders/latest`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    statusCounts: () => {
        return axiosInstance.get(`/api/orders/counts`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getStatistics: () => {
        return axiosInstance.get(`/api/orders/statistics`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    }
};

export default OrderApi;