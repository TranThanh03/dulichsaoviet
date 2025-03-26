import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const AssignmentApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/assignments", { 
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/assignments/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/assignments", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstance.delete(`/api/assignments/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getTourListByGuideId: (id) => {
        return axiosInstance.get(`/api/assignments/guide/${id}`);
    },
    getGuideListByTourId: (id, page = 0, size = 6) => {
        return axiosInstance.get(`/api/assignments/tour/${id}`, {
            params: { page, size },
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
};

export default AssignmentApi;