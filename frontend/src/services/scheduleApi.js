import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const AssignmentApi = {
    getAll: (params) => {
        return axiosInstanceAdmin.get("/api/v1/schedules", { 
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/schedules/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/schedules", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/schedules/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getByTourId: (id) => {
        return axiosInstance.get(`/api/v1/schedules/tour/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    getScheduleTourById: (id) => {
        return axiosInstance.get(`/api/v1/schedules/schedule-tour/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
};

export default AssignmentApi;