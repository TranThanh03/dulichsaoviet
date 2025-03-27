import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const PaymentApi = {
    getByUserId: () => {
        return axiosInstance.get("/api/v1/payment/list", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    getStatus: (id) => {
        return axiosInstance.get(`/api/v1/payment/status/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    process: (data) => {
        return axiosInstance.post("/api/v1/payment/process", data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    processLater: (id, data) => {
        return axiosInstance.post(`/api/v1/payment/process/later/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
};

export default PaymentApi;