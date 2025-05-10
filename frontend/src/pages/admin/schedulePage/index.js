import { useState, useEffect, useCallback } from "react";
import Swal from "sweetalert2";
import { ScheduleApi } from "services";
import formatDatetime from "utils/formatDatetime";
import "./index.scss";
import formatCurrency from "utils/formatCurrency";
import { FaTrash, FaEdit, FaPlus, FaSearch } from "react-icons/fa";
import { ErrorToast, SuccessToast } from "component/notifi";
import { ToastContainer } from "react-toastify";
import Pagination from "component/pagination";
import { Link } from "react-router-dom";

const SchedulePage = () => {
    const [schedules, setSchedules] = useState([]);
    const [search, setSearch] = useState("");
    const [showForm, setShowForm] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 9;
    const [isLoading, setIsLoading] = useState(true);
    const statusClassMap = {
        "Chưa diễn ra": "not-started",
        "Đang diễn ra": "ongoing",
        "Đã kết thúc": "ended"
    };
    
    const fetchSchedules = useCallback(async () => {
        try {
            const response = await ScheduleApi.getAll({ 
                keyword: search.trim(),
                page: currentPage,
                size: pageSize,
            });

            if (response?.code === 1601) {
                setSchedules(response.result.content);
                setTotalPages(response.result.totalPages);
            } else if (response?.code === 1057) {
                ErrorToast('Thời gian phải có định dạng kiểu "dd-mm-yyyy".');
            }
        }
        catch (error) {
            console.error("Failed to fetch schedules: ", error);
        }
        finally {
            setIsLoading(false);
        }
    }, [search, currentPage, pageSize]);

    useEffect(() => {
        fetchSchedules();
    }, [currentPage, pageSize]);

    const handleSearch = () => {
        setCurrentPage(0);
        fetchSchedules();
    };

    const handleDelete = async (id, code) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn xóa lịch <b>${code}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await ScheduleApi.delete(id);

                if (response?.code === 1504) {
                    SuccessToast(`Xóa lịch ${code} thành công.`)
                    setSchedules(prevSchedules => prevSchedules.filter(schedule => schedule.id !== id));
                }
                else if (response?.code === 1049) {
                    ErrorToast(`Lịch ${code} đang có lịch đặt đang xử lý.`);
                }
                else {
                    ErrorToast(`Xóa lịch ${code} không thành công.`)
                }
            } catch (error) {
                console.log("Failed to delete schedule: ", error);
                ErrorToast("Đã xảy ra lỗi không xác định! Vui lòng thử lại sau.")
            }
        }
    };

    if (isLoading) {
        return (
            <div style={{height: 1000}}></div>
        );
    }

    return (
        <div className="schedule-manage-page">
            <div className="row">
                <div className="col-md-12 col-sm-12 ">
                    <div className="x_panel">
                        <div className="x_title">
                            <h2>Danh sách lịch trình</h2>
                            <Link to={"/manage/schedules/insert"}>
                                <span>
                                    <FaPlus className="me-1 mb-1" style={{ color: '#2A3F54', fontSize: '18px' }} />
                                    Thêm
                                </span>
                            </Link>

                            <div className="clearfix"></div>
                        </div>
                        <div className="x_content">
                            <div className="form-search">
                                <input
                                    type="search"
                                    placeholder="Nhập mã lịch, mã tour, ngày khởi hành"
                                    value={search}
                                    onChange={(e) => setSearch(e.target.value)}
                                />
                                <button type="button" onClick={handleSearch}>
                                    <FaSearch style={{ color: '#333', fontSize: '16px' }} />
                                </button>
                            </div>
                            <div className="clearfix"></div>

                            <div className="row">
                                <div className="col-sm-12">
                                    <div className="card-box table-responsive">
                                        <table className="table table-striped table-bordered" >
                                            <thead>
                                                <tr>
                                                    <th>STT</th>
                                                    <th>Mã lịch</th>
                                                    <th>Mã tour</th>
                                                    <th>Ngày khởi hành</th>
                                                    <th>Ngày kết thúc</th>
                                                    <th>Số người</th>
                                                    <th>Tổng người</th>
                                                    <th>Giá người lớn</th>
                                                    <th>Giá trẻ em</th>
                                                    <th>Trạng thái</th>
                                                    <th colSpan={2}>Thao tác</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {schedules.length > 0 && schedules.map((item, index) => (
                                                    <tr key={index}>
                                                        <td> {index + 1} </td>
                                                        <td> {item.code} </td>
                                                        <td> {item.tourCode} </td>
                                                        <td> {item.startDate ? formatDatetime(item.startDate) : ''} </td>
                                                        <td> {item.endDate ? formatDatetime(item.endDate) : ''} </td>
                                                        <td> {item.quantityPeople} </td>
                                                        <td> {item.totalPeople} </td>
                                                        <td className="color-red"> {item.adultPrice ? formatCurrency(item.adultPrice) : 0} </td>
                                                        <td className="color-red"> {item.childrenPrice ? formatCurrency(item.childrenPrice) : 0} </td>
                                                        <td className={statusClassMap[item.status] || ''}> 
                                                            {item.status}
                                                        </td>
                                                        <td>
                                                            {item.status === "Chưa diễn ra" && (
                                                                <Link to={`/manage/schedules/edit/${item.id}`}>
                                                                    <FaEdit style={{ color: '#26B99A', fontSize: '20px' }} />
                                                                </Link>
                                                            )}
                                                        </td>
                                                        <td>
                                                            {item.status !== "Đang diễn ra" && (
                                                                <button type="button" onClick={() => handleDelete(item.id, item.code)}>
                                                                    <FaTrash style={{ color: 'red', fontSize: '18px' }} />
                                                                </button>
                                                            )}
                                                        </td>
                                                    </tr>
                                                ))}
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>

                            <Pagination
                                currentPage={currentPage}
                                totalPages={totalPages}
                                onPageChange={setCurrentPage}
                            />
                        </div>
                    </div>
                </div>
            </div>

            <ToastContainer />
        </div>
    );
};

export default SchedulePage;