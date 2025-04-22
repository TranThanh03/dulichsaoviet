import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const TourApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/v1/tours", { params });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/v1/tours/${id}`);
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/tours", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    update: (id, data) => {
        return axiosInstanceAdmin.put(`/api/v1/tours/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/tours/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    searchTours: (searchQuery, page = 0, size = 6) => {
        return axiosInstance.get(`/api/v1/tours/search?p=${searchQuery}`, {
            params: { page, size },
        });
    },
    popular: () => {
        return axiosInstanceAdmin.get(`/api/v1/tours/popular`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    filter: (data, params) => {
        return axiosInstance.post("/api/v1/tours/filter", data, {
            params: params,
        });
    }
};

export default TourApi;