import axiosInstance from "utils/axiosInstance";
import getToken from "utils/getToken";

const CheckoutApi = {
    process: (data) => {
        return axiosInstance.post("/api/v1/checkouts/process", data, {
            headers: {
                Authorization: `Bearer ${getToken()}`
            }
        });
    },
    momoCallback: (params) => {
        return axiosInstance.post(`/api/v1/checkouts/momo/callback?${params}`,
            {},
            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        );
    },
    vnpayCallback: (params) => {
        return axiosInstance.post(`/api/v1/checkouts/vnpay/callback?${params}`,
            {},
            {
                headers: {
                    Authorization: `Bearer ${getToken()}`
                }
            }
        );
    }
};

export default CheckoutApi;