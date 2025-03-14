import React, { useState, useEffect, memo } from "react";
import "./style.scss";
import { OrderApi } from "api";
import formatCurrency from "utils/formatCurrency";
import formatDatetime from "utils/formatDatetime";
import { Link } from "react-router-dom";

const CalendarPage = () => {
    const [calendars, setCalendars] = useState([]);
    const [search, setSearch] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

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
        const fetchCalendars = async () => {
            try {
                const response = await OrderApi.getAll({ page: currentPage, size: pageSize });

                if (response?.code === 1953) {
                    setCalendars(response.result.content);
                    setTotalPages(response.result.totalPages);
                }
            }
            catch (error) {
                console.error("Failed to fetch calendars: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchCalendars();
    }, [currentPage, pageSize]);

    const filteredCalendars = calendars.filter((item) =>
        item.orderId?.toLowerCase().includes(search.toLowerCase()) ||
        item.customerId?.toLowerCase().includes(search.toLowerCase()) ||
        item.tourId?.toLowerCase().includes(search.toLowerCase()) ||
        item.guideId?.toLowerCase().includes(search.toLowerCase()) ||
        item.assignmentId?.toLowerCase().includes(search.toLowerCase())
    );

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="calendar-manage-page">
            <h2 id="title">Danh sách đơn đặt</h2>
            <div className="control">
                <form onSubmit={(e) => e.preventDefault()}>
                    <input 
                        type="search" 
                        placeholder="Nhập mã đơn đặt, mã khách hàng, mã tour, mã hdv, mã phân công" 
                        value={search} 
                        onChange={(e) => setSearch(e.target.value)}
                        autoComplete="off" 
                        required 
                    />
                    <button type="submit">Tìm</button>
                </form>
            </div>
            <div className="main">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã đơn đặt</th>
                            <th>Mã khách hàng</th>
                            <th>Mã tour</th>
                            <th>Mã HDV</th>
                            <th>Mã phân công</th>
                            <th>Tổng tiền</th>
                            <th>Trạng thái thanh toán</th>
                            <th>Thời gian đặt</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredCalendars.length > 0 ? (
                            filteredCalendars.map((item, index) => (
                                <tr key={item.id}>
                                    <td>{index + 1}</td>
                                    <td>{item.orderId}</td>
                                    <td>{item.customerId}</td>
                                    <td>{item.tourId}</td>
                                    <td>{item.guideId}</td>
                                    <td>{item.assignmentId}</td>
                                    <td style={{ color: 'red' }}>{formatCurrency(item.totalPrice || 0)}</td>
                                    <td className={paymentClassMap[item.paymentStatus] || ""}>{item.paymentStatus}</td>
                                    <td>{formatDatetime(item.orderDatetime || new Date())}</td>
                                    <td className={statusClassMap[item.status] || ""}>{item.status}</td>
                                    <td>
                                        <Link to={`/manage/calendars/detail/${item.id}`}>
                                            <button type="button">Chi tiết</button>
                                        </Link>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="11">Không có dữ liệu</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>

            <div className="pagination">
                <button disabled={currentPage === 0} onClick={() => setCurrentPage(currentPage - 1)}>
                    &lt;
                </button>
                <span>Trang {currentPage + 1} / {totalPages}</span>
                <button disabled={currentPage >= totalPages - 1} onClick={() => setCurrentPage(currentPage + 1)}>
                    &gt;
                </button>
            </div>
        </div>
    );
};

export default memo(CalendarPage);