import { memo, useState, useEffect } from "react";
import { useNavigate, Link, useLocation } from "react-router-dom";
import "./message.scss";
import { PaymentApi } from "services";
import { failedSvg, successSvg } from "assets";

const MessagePage = () => {
    const [status, setStatus] = useState(null);
    const [countdown, setCountdown] = useState(5);
    const navigate = useNavigate();
    const location = useLocation();
    const [isLoading, setLoading] = useState(false);

    const getQueryParam = (param) => {
        const searchParams = new URLSearchParams(location.search);
        return searchParams.get(param);
    };

    const getAssignmentData = (extraData) => {
        if (!extraData) return { assignmentId: "", later: "" };

        const decodedData = decodeURIComponent(extraData);

        const assignmentMatch = decodedData.match(/assignmentId=([^;]*)/);
        const laterMatch = decodedData.match(/later=([^;]*)/);

        return {
            assignmentId: assignmentMatch ? assignmentMatch[1] : "",
            later: laterMatch ? laterMatch[1] : ""
        };
    };

    const transId = getQueryParam("transId");
    const extraData = getQueryParam("extraData");
    const { assignmentId, later } = getAssignmentData(extraData);

    useEffect(() => {
        const fetchPaymentStatus = async () => {
            try {
                const response = await PaymentApi.getStatus(transId);
                
                if (response?.result?.status) {
                    setStatus(response.result.status);
                }
            }
            catch (error) {
                console.error("Failed to fetch payment status:", error);
                // navigate("/error/404");
            }
            finally {
                setLoading(true);
            }
        };

        fetchPaymentStatus();
    }, [transId, navigate]);

    useEffect(() => {
        if (status === "success" || later === "true") {
            const interval = setInterval(() => {
                setCountdown((prev) => prev - 1);
            }, 1000);

            const timeout = setTimeout(() => {
                navigate("/calendars/index");
            }, 5000);

            return () => {
                clearInterval(interval);
                clearTimeout(timeout);
            };
        }
    }, [status, later, navigate]);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="order-message">
            <div className="message">
                {status === "success" ? (
                    <div className="success">
                        <img src={successSvg} alt="success"/>
                        <h2>Thanh toán thành công</h2>
                        <p>Cảm ơn bạn đã đặt tour với chúng tôi</p>
                        <p>Chuyển hướng đến danh sách lịch đặt sau <span style={{color: 'red'}}>{countdown}</span> giây</p>
                    </div>
                ) : status === "failed" ? (
                    later === "true" ? (
                        <div className="failed">
                            <img src={failedSvg} alt="failed"/>
                            <h2>Thanh toán thất bại</h2>
                            <p>Đã xảy ra lỗi trong quá trình thanh toán. Vui lòng thử lại!</p>
                            <p>Chuyển hướng đến danh sách lịch đặt sau <span style={{color: 'red'}}>{countdown}</span> giây</p>
                        </div>
                    ) : (
                        <div className="failed">
                            <img src={failedSvg} alt="failed"/>
                            <h2>Thanh toán thất bại</h2>
                            <p>Đã xảy ra lỗi trong quá trình thanh toán. Vui lòng thử lại!</p>
                            <Link to={`/orders/${assignmentId}`}>Đặt tour</Link>
                        </div>
                    )
                ) : null}
            </div>
        </div>
    );
};

export default memo(MessagePage);