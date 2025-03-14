import React, { useState, useEffect, useCallback } from "react";
import Swal from "sweetalert2";
import "./index.scss";
import { GuideApi } from "api";
import formatDatetime from "utils/formatDatetime";
import { Link } from "react-router-dom";

const GuidePage = () => {
    const [guides, setGuides] = useState([]);
    const [search, setSearch] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    const fetchGuides = useCallback(async () => {
        try {
            const response = await GuideApi.getAll({ page: currentPage, size: pageSize });

            if (response?.code === 1968) {
                setGuides(response.result.content);
                setTotalPages(response.result.totalPages);
            }
        }
        catch (error) {
            console.error("Failed to fetch guides: ", error);
        }
        finally {
            setLoading(true);
        }
    }, [currentPage, pageSize]);

    useEffect(() => {
        fetchGuides();
    }, [fetchGuides]);

    const handleDelete = async (id) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn xóa hướng dẫn viên <b>${id}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await GuideApi.delete(id);

                if (response?.code === 1964) {
                    fetchGuides();
                    Swal.fire("Thành công", "Xóa hướng dẫn viên thành công", "success");
                }
                else if (response?.code === 1026) {
                    Swal.fire("Lỗi", "Hướng dẫn viên này đang có lịch đặt!", "error");
                }
                else {
                    Swal.fire("Lỗi", "Không thể xóa hướng dẫn viên", "error");
                }
            } catch (error) {
                Swal.fire("Lỗi", "Không thể xóa hướng dẫn viên", "error");
            }
        }
    };

    const filteredGuides = guides.filter(guide => {
        const query = search.toLowerCase();

        return (
            guide.fullName?.toLowerCase().includes(query) ||
            guide.id?.toLowerCase().includes(query) ||
            guide.phone?.includes(query)
        );
    });

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="guide-manage-page">
            <h2 id="title">Danh sách hướng dẫn viên</h2>
            <div className="control">
                <Link to="/manage/guides/create"><button>Thêm</button></Link>
                <form onSubmit={(e) => e.preventDefault()}>
                    <input
                        type="search"
                        placeholder="Nhập mã, tên, sdt hướng dẫn viên"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                    <button type="submit">Tìm</button>
                </form>
            </div>
            <div className="main">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã HDV</th>
                            <th>Họ tên</th>
                            <th>Ngày sinh</th>
                            <th>Giới tính</th>
                            <th>Số điện thoại</th>
                            <th>Ảnh</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredGuides.length > 0 ? (
                            filteredGuides.map((guide, index) => (
                                <tr key={guide.id}>
                                    <td>{index + 1}</td>
                                    <td>{guide.id}</td>
                                    <td>{guide.fullName}</td>
                                    <td>{formatDatetime(guide.dateOfBirth)}</td>
                                    <td>{guide.sex}</td>
                                    <td>{guide.phone || "N/A"}</td>
                                    <td>
                                        <img className="avatar" 
                                             src={`${guide.avatar || "/assets/users/img/guide/no-image.jpg"}`}
                                             alt="Ảnh HDV" />
                                    </td>
                                    <td>
                                        <Link to={`/manage/guides/edit/${guide.id}`}><button>Sửa</button></Link>
                                        <button id="btn-delete" onClick={() => handleDelete(guide.id)}>Xóa</button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="8">Không có dữ liệu</td>
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

export default GuidePage;