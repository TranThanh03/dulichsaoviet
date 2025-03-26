import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const GuideApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/guides", { params });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/guides/${id}`);
    },
    getByEvaluate: () => {
        return axiosInstance.get("/api/guides/evaluate");
    },
    getGuidesByAssignment: () => {
        return axiosInstance.get(`/api/guides/assignment`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/guides", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    update: (id, data) => {
        return axiosInstance.put(`/api/guides/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstance.delete(`/api/guides/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
};

export default GuideApi;