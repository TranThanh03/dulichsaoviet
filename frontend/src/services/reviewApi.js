import axiosInstance from "utils/axiosInstance";
import axiosInstanceAdmin from "utils/axiosInstanceAdmin";
import getToken from "utils/getToken";

const ReviewApi = {
    getAll: (id) => {
        const token = getToken();
        const headers = token ? { Authorization: `Bearer ${token}` } : {};

        return axiosInstance.get(`/api/v1/reviews/${id}`, {
            headers
        });
    },
    create: (data) => {
        return axiosInstanceAdmin.post("/api/v1/reviews", data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    delete: (id) => {
        return axiosInstanceAdmin.delete(`/api/v1/reviews/${id}`, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
};

export default ReviewApi;