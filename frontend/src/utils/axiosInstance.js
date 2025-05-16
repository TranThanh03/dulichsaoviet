import axios from 'axios';
import { setLoading } from './loading';

const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000,
    headers: { 'Content-Type': 'application/json' },
    withCredentials: true
});

const pendingRequests = new Set();

axiosInstance.interceptors.request.use(
    (config) => {
            config.metadata = { startTime: new Date().getTime() };
            const timer = setTimeout(() => {
                setLoading(true);
            }, 250);
            config.metadata.timer = timer;
            pendingRequests.add(config);

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

axiosInstance.interceptors.response.use(
    (response) => {
        if (response.config.metadata?.timer) {
            clearTimeout(response.config.metadata.timer);
        }

        pendingRequests.delete(response.config);

        if (pendingRequests.size === 0) {
            setLoading(false);
        }

        return response.data;
    },
    (error) => {
        if (error.config?.metadata?.timer) {
            clearTimeout(error.config.metadata.timer);
        }

        pendingRequests.delete(error.config);

        if (pendingRequests.size === 0) {
            setLoading(false);
        }

        if (error.response?.data?.code === 4445) {
            if (error.config.url.includes("/api/v1/auth/introspect")) {
                return Promise.reject(error.response || error.message);
            }

            window.location.href = "/error/404";
        }
        else if (error.code === "ERR_NETWORK") {
            window.location.href = "/error/500";
        }

        return Promise.reject(error.response || error.message);
    }
);

export default axiosInstance;