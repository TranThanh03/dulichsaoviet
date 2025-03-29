import React, { useState, useEffect, useCallback } from "react";
import Swal from "sweetalert2";
import "./index.scss";
import { TourApi } from "services";
import { Link } from "react-router-dom";
import formatCurrency from "utils/formatCurrency";
import { noImage } from "assets";

const TourPage = () => {
    const [tours, setTours] = useState([]);
    const [categories, setCategories] = useState({});
    const [search, setSearch] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchCategory = async () => {
            try {
                const response = await TourApi.getCategory();

                if (response?.code === 1986) {
                    const categoryMap = response.result.reduce((acc, cat) => {
                        acc[cat.id] = cat.name;
                        return acc;
                    }, {});

                    setCategories(categoryMap);
                }
            } catch (error) {
                console.error("Failed to fetch category: ", error);
            }
        }

        fetchCategory();
    }, []);

    const fetchTours = useCallback(async () => {
        try {
            const response = await TourApi.getAll({ page: currentPage, size: pageSize });

            if (response?.code === 1988) {
                setTours(response.result.content);
                setTotalPages(response.result.totalPages);
            }
        }
        catch (error) {
            console.error("Failed to fetch tours: ", error);
        }
        finally {
            setLoading(true);
        }
    }, [currentPage, pageSize]);

    useEffect(() => {
        fetchTours();
    }, [fetchTours]);

    const handleDelete = async (id, tourCode) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn xóa tour <b>${tourCode}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await TourApi.delete(id);

                if (response?.code === 1982) {
                    fetchTours();
                    Swal.fire("Thành công", "Xóa tour thành công", "success");
                }
                else if (response?.code === 1026) {
                    Swal.fire("Lỗi", "Tour này đang có lịch đặt!", "error");
                }
                else {
                    Swal.fire("Lỗi", "Không thể xóa tour", "error");
                }
            } catch (error) {
                Swal.fire("Lỗi", "Không thể xóa tour", "error");
            }
        }
    };

    const filteredTours = tours.filter(tour =>
        tour.name?.toLowerCase().includes(search.toLowerCase()) ||
        tour.id?.toLowerCase().includes(search.toLowerCase())
    );

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="tour-manage-page">
            <h2 id="title">Danh sách tour</h2>
            <div className="control">
                <Link to="/manage/tours/create"><button>Thêm</button></Link>
                <form onSubmit={(e) => e.preventDefault()}>
                    <input
                        type="search"
                        placeholder="Nhập mã, tên tour"
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
                            <th>Mã tour</th>
                            <th>Tour</th>
                            <th>Hình ảnh</th>
                            <th>Chủ đề</th>
                            <th>Giá</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredTours.length > 0 ? (
                            filteredTours.map((tour, index) => (
                                <tr key={tour.id}>
                                    <td>{index + 1}</td>
                                    <td>{tour.tourCode}</td>
                                    <td>{tour.name}</td>
                                    <td>
                                        <img className="image" src={tour.image ? tour.image : noImage} alt="ảnh tour" />
                                    </td>
                                    <td>{categories[tour.categoryId] || "Không xác định"}</td>
                                    <td className="bold-red">{formatCurrency(tour.price)}</td>
                                    <td>
                                        <Link to={`/manage/tours/edit/${tour.id}`}><button>Sửa</button></Link>
                                        <button 
                                            id="btn-delete" 
                                            onClick={() => handleDelete(tour.id, tour.tourCode)}
                                        >
                                            Xóa
                                        </button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="7">Không có dữ liệu</td>
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

export default TourPage;