import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const AssignmentApi = {
    getAll: (params) => {
        return axiosInstanceAdmin.get("/api/v1/assignments", { 
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/assignments/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/assignments", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/assignments/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getTourListByGuideId: (id) => {
        return axiosInstance.get(`/api/v1/assignments/guide/${id}`);
    },
    getGuideListByTourId: (id, page = 0, size = 6) => {
        return axiosInstance.get(`/api/v1/assignments/tour/${id}`, {
            params: { page, size },
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
};

export default AssignmentApi;