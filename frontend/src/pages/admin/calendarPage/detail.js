import { useState, useEffect, memo } from "react";
import "./detail.scss";
import { BookingApi } from "services";
import formatCurrency from "utils/formatCurrency";
import formatDatetime from "utils/formatDatetime";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { noImage } from "assets";

const CalendarDetailPage = () => {
    const [calendar, setCalendar] = useState([]);
    const [totalPrice, setTotalPrice] = useState(0);
    const { id } = useParams();
    const [isLoading, setLoading] = useState(false);
    const navigate = useNavigate();

    const paymentClassMap = {
        "Đã thanh toán": "paid",
        "Chưa thanh toán": "unpaid",
    };
    const statusClassMap = {
        "Đã xác nhận": "confirm",
        "Đã hủy": "cancel",
        "Đang xử lý": "processing",
    };

    useEffect(() => {
        const fetchCalendar = async () => {
            try {
                const response = await BookingApi.getById(id);

                if (response?.code === 1952 && response?.result) {
                    setCalendar(response.result);
                    setTotalPrice(response.result.tourPrice + response.result.guidePrice);
                }
            }
            catch (error) {
                console.error("Failed to fetch calendar: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchCalendar();
    }, [id]);

    const handleCancel = async (id, orderCode) => {
        const result = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn muốn hủy lịch đặt <b>${orderCode}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (result.isConfirmed) {
            try {
                const response = await BookingApi.cancelAdmin(id);

                if (response.code === 1955) {
                    Swal.fire({
                        title: "Thành công",
                        text: "Lịch đặt đã được hủy thành công",
                        icon: "success",
                        confirmButtonText: "Đóng",
                    });

                    setCalendar((prev) => ({
                        ...prev,
                        status: "Đã hủy",
                    }));
                } else {
                    Swal.fire({
                        title: "Lỗi",
                        text: response.message || "Hủy lịch đặt không thành công!",
                        icon: "error",
                        confirmButtonText: "Đóng",
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Lỗi",
                    text: "Đã xảy ra lỗi khi hủy lịch đặt!",
                    icon: "error",
                    confirmButtonText: "Đóng",
                });
            }
        }
    };

    const handleConfirm = async (id, orderCode) => {
        const result = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn muốn xác nhận lịch đặt <b>${orderCode}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (result.isConfirmed) {
            try {
                const response = await BookingApi.confirm(id);

                if (response.code === 1954) {
                    Swal.fire({
                        title: "Thành công",
                        text: "Lịch đặt đã được xác nhận thành công",
                        icon: "success",
                        confirmButtonText: "Đóng",
                    });

                    setCalendar((prev) => ({
                        ...prev,
                        status: "Đã xác nhận",
                    }));
                } else {
                    Swal.fire({
                        title: "Lỗi",
                        text: response.message || "Xác nhận lịch đặt không thành công!",
                        icon: "error",
                        confirmButtonText: "Đóng",
                    });
                }
            } catch (error) {
                Swal.fire({
                    title: "Lỗi",
                    text: "Đã xảy ra lỗi khi xác nhận lịch đặt!",
                    icon: "error",
                    confirmButtonText: "Đóng",
                });
            }
        }
    };

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="calendar-detail-page">
            <h2>Chi tiết lịch đặt</h2>
            
            <div className="info-section">
                <div>
                <h3>Thông tin lịch đặt</h3>
                <p><strong>Mã lịch đặt:</strong> {calendar.orderCode}</p>
                <p><strong>Thời gian đặt:</strong> {calendar.orderDatetime ? formatDatetime(calendar.orderDatetime) : ""}</p>
                <p><strong>Trạng thái:</strong> <span className={statusClassMap[calendar.status] || ""}>{calendar.status}</span></p>
                </div>
                
                <div>
                <h3>Thông tin khách hàng</h3>
                <p><strong>Họ tên:</strong> {calendar.userName}</p>
                <p><strong>Số điện thoại:</strong> {calendar.phone}</p>
                <p><strong>Email:</strong> {calendar.email}</p>
                </div>
            </div>
            
            <div className="tour-info">
                <h3>Thông tin Tour</h3>
                <div className="sub-tour">
                    <div className="info">
                        <img src={calendar.tourImage ? calendar.tourImage : noImage} alt={calendar.tourName} className="tour-image" />
                        
                        <div className="sub-info">
                            <p><strong>Mã tour:</strong> {calendar.tourCode}</p>
                            <p><strong>Tên tour:</strong> {calendar.tourName}</p>
                            <p><strong>Thời gian:</strong> <span>{calendar.startDate ? formatDatetime(calendar.startDate) : ""}</span> - <span>{calendar.endDate ? formatDatetime(calendar.endDate) : ""}</span></p>
                            <p><strong>Giá:</strong> <span className="red">{formatCurrency(calendar.tourPrice || 0)}</span></p>
                        </div>
                    </div>
                    <div className="guide-info">
                        <img src={calendar.guideAvatar ? calendar.guideAvatar : noImage} alt={calendar.guideName} className="guide-avatar" />
                        
                        <div className="sub-guide">
                            <p><strong>Mã hướng dẫn viên:</strong> {calendar.guideCode}</p>
                            <p><strong>Họ tên:</strong> {calendar.guideName}</p>
                            <p><strong>Giá:</strong> <span className="red">{formatCurrency(calendar.guidePrice || 0)}</span></p>
                        </div>
                    </div>
                </div>
                <div className="cost">
                    <div className="sub-cost">
                        <table>
                            <tbody>
                                <tr>
                                    <td><strong>Tổng tiền:</strong></td>
                                    <td><span>{formatCurrency(totalPrice)}</span></td>
                                </tr>
                                <tr>
                                    <td><strong>Số người:</strong></td>
                                    <td><span>{calendar.numberOfPeople}</span></td>
                                </tr>
                                <tr>
                                    <td><strong>Tổng thanh toán:</strong></td>
                                    <td><span className="red-bold">{formatCurrency(calendar.totalPrice || 0)}</span></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
            
            <div className="payment-info">
                <h3>Thông tin thanh toán</h3>
                <p><strong>Phương thức:</strong> {calendar.method}</p>
                <p><strong>Trạng thái:</strong> <span className={paymentClassMap[calendar.paymentStatus] || ""}>{calendar.paymentStatus}</span></p>
                <p><strong>Số tiền:</strong> <span className="red-bold">{formatCurrency(calendar.amount || 0)}</span></p>
                <p><strong>Thời gian thanh toán:</strong> {calendar.paymentDatetime ? formatDatetime(calendar.paymentDatetime) : ""}</p>
            </div>

            <div className="control">
                <button id="back" type="button" onClick={() => navigate("/manage/calendars/index")} className="btn btn-secondary">Quay về</button>
                {calendar.status === "Đang xử lý" && calendar.paymentStatus === "Đã thanh toán" ? (
                    <>
                        <button id="cancel" type="button" onClick={() => handleCancel(calendar.id, calendar.orderCode)}>Hủy</button>
                        <button id="confirm" type="button" onClick={() => handleConfirm(calendar.id, calendar.orderCode)}>Xác nhận</button>
                    </>
                ) : calendar.status === "Đang xử lý" && calendar.paymentStatus === "Chưa thanh toán" ? (
                    <button id="cancel" type="button" onClick={() => handleCancel(calendar.id, calendar.orderCode)}>Hủy</button>
                ) : null}
            </div>
        </div>
    );
};

export default memo(CalendarDetailPage);