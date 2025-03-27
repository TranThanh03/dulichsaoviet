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
    getTourListNew: () => {
        return axiosInstance.get("/api/v1/tours/new");
    },
    getByCategoryId: (id, page = 0, size = 6) => {
        return axiosInstance.get(`/api/v1/tours/category/${id}`, {
            params: { page, size },
        });
    },
    getToursByAssignment: () => {
        return axiosInstanceAdmin.get(`/api/v1/tours/assignment`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
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
    getCategory: () => {
        return axiosInstance.get("/api/v1/tours/category");
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
    }
};

export default TourApi;