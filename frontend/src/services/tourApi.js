import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const TourApi = {
    getAll: (params) => {
        return axiosInstance.get("/api/tours", { params });
    },
    getById: (id) => {
        return axiosInstance.get(`/api/tours/${id}`);
    },
    getTourListNew: () => {
        return axiosInstance.get("/api/tours/new");
    },
    getByCategoryId: (id, page = 0, size = 6) => {
        return axiosInstance.get(`/api/tours/category/${id}`, {
            params: { page, size },
        });
    },
    getToursByAssignment: () => {
        return axiosInstance.get(`/api/tours/assignment`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    create: (data) => {
        return axiosInstance.post("/api/tours", data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    update: (id, data) => {
        return axiosInstance.put(`/api/tours/${id}`, data, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    delete: (id) => {
        return axiosInstance.delete(`/api/tours/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getCategory: () => {
        return axiosInstance.get("/api/tours/category");
    },
    searchTours: (searchQuery, page = 0, size = 6) => {
        return axiosInstance.get(`/api/tours/search?p=${searchQuery}`, {
            params: { page, size },
        });
    },
    popular: () => {
        return axiosInstance.get(`/api/tours/popular`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    }
};

export default TourApi;