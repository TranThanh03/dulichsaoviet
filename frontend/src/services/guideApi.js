import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const GuideApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/v1/guides", { params });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/guides/${id}`);
    },
    getByEvaluate: () => {
        return axiosInstance.get("/api/v1/guides/evaluate");
    },
    getGuidesByAssignment: () => {
        return axiosInstanceAdmin.get(`/api/v1/guides/assignment`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/guides", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    update: (id, data) => {
        return axiosInstanceAdmin.put(`/api/v1/guides/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/guides/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
};

export default GuideApi;