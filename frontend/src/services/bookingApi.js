import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const BookingApi = {
    getAll: (params) => {
        return axiosInstanceAdmin.get("/api/v1/bookings", {
            params,
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getById: (id) => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    getByCustomerId: () => {
        return axiosInstance.get("/api/v1/bookings/list", {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancel: (id) => {
        return axiosInstance.patch(`/api/v1/bookings/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    cancelAdmin: (id) => {
        return axiosInstanceAdmin.patch(`/api/v1/bookings/cancel/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    confirm: (id) => {
        return axiosInstanceAdmin.patch(`/api/v1/bookings/confirm/${id}`, {}, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    infoCount: () => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/info-count`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    latest: () => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/latest`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    popularTours: () => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/top-popular`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    statusCount: () => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/status-count`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    },
    getStatistics: (year) => {
        return axiosInstanceAdmin.get(`/api/v1/bookings/statistics/${year}`, {
            headers: {
                Authorization: `Bearer ${getToken(true)}`
            }
        });
    }
};

export default BookingApi;