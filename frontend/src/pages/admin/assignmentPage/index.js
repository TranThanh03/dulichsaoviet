import { useState, useEffect, useCallback } from "react";
import Swal from "sweetalert2";
import { ScheduleApi } from "services";
import AddScheduleForm from "./insert";
import formatDatetime from "utils/formatDatetime";
import "./index.scss";
import formatCurrency from "utils/formatCurrency";

const SchedulePage = () => {
    const [assignments, setSchedules] = useState([]);
    const [search, setSearch] = useState("");
    const [showForm, setShowForm] = useState(false);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);
    
    const fetchSchedules = useCallback(async () => {
        try {
            const response = await ScheduleApi.getAll({ page: currentPage, size: pageSize });

            if (response?.code === 1978) {
                setSchedules(response.result.content);
                setTotalPages(response.result.totalPages);
            }
        }
        catch (error) {
            console.error("Failed to fetch assignments: ", error);
        }
        finally {
            setLoading(true);
        }
    }, [currentPage, pageSize]);

    useEffect(() => {
        fetchSchedules();
    }, [fetchSchedules]);

    const handleDelete = async (id, assignmentCode) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn xóa lịch phân công <b>${assignmentCode}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await ScheduleApi.delete(id);

                if (response?.code === 1975) {
                    fetchSchedules();
                    Swal.fire("Thành công", "Xóa lịch phân công thành công", "success");
                }
                else if (response?.code === 1026) {
                    Swal.fire("Lỗi", "Lịch phân công này đang có lịch đặt!", "error");
                }
                else {
                    Swal.fire("Lỗi", "Không thể xóa lịch phân công", "error");
                }
            } catch (error) {
                Swal.fire("Lỗi", "Không thể xóa lịch phân công", "error");
            }
        }
    };

    const filteredSchedules = assignments.filter((assignment) =>
        assignment.assignmentCode?.toLowerCase().includes(search.toLowerCase()) ||
        assignment.tourId?.toLowerCase().includes(search.toLowerCase()) ||
        assignment.guideId?.toLowerCase().includes(search.toLowerCase())
    );

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="assignment-manage-page">
            <h2 id="title">Danh sách phân công</h2>
            <div className="control">
                <button onClick={() => setShowForm(true)}>Thêm</button>
                <form onSubmit={(e) => e.preventDefault()}>
                    <input 
                        type="search" 
                        placeholder="Nhập mã phân công, mã tour, mã hướng dẫn viên" 
                        value={search} 
                        onChange={(e) => setSearch(e.target.value)}
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
                            <th>Mã phân công</th>
                            <th>Mã Tour</th>
                            <th>Mã HDV</th>
                            <th>Số người</th>
                            <th>Ngày khởi hành</th>
                            <th>Ngày kết thúc</th>
                            <th>Giá HDV</th>
                            <th>Trạng thái</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredSchedules.length > 0 ? (
                            filteredSchedules.map((assignment, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{assignment.assignmentCode}</td>
                                    <td>{assignment.tourId}</td>
                                    <td>{assignment.guideId}</td>
                                    <td>{assignment.numberOfPeople}/<b>{assignment.totalPeople}</b></td>
                                    <td className="start-date">{formatDatetime(assignment.startDate)}</td>
                                    <td className="end-date">{formatDatetime(assignment.endDate)}</td>
                                    <td style={{color: "red"}}>{formatCurrency(assignment.guidePrice)}</td>
                                    <td className={assignment.status === "Đang diễn ra" ? "ongoing" : "ended"}>
                                        {assignment.status}
                                    </td>
                                    <td>
                                        {assignment.status === "Đang diễn ra" && (
                                            <button onClick={() => handleDelete(assignment.id, assignment.assignmentCode)} style={{ color: "red" }}>Xóa</button>
                                        )}
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="10">Không có dữ liệu</td>
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

            {showForm && <AddScheduleForm onClose={() => setShowForm(false)} onAdded={fetchSchedules} />}
        </div>
    );
};

export default SchedulePage;