import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const PromotionApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/v1/promotions", { params });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/promotions/${id}`);
    },
    getList: () => {
        return axiosInstance.get("/api/v1/promotions/list");
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/promotions", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    update: (id, data) => {
        return axiosInstanceAdmin.put(`/api/v1/promotions/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/promotions/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
};

export default PromotionApi;