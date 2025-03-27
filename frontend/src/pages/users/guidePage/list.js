import { memo, useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { AssignmentApi } from 'services';
import "./index.scss";
import ageCalculate from 'utils/ageCalculation';
import formatErevaluate from 'utils/formatEvaluate';
import formatCurrency from 'utils/formatCurrency';
import formatDatetime from 'utils/formatDatetime';
import { ScrollToTop } from 'utils/ScrollToTop';
import { noImage } from 'assets';

const GuideListPage = () => {
    const [guides, setGuides] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const { id } = useParams();
    const navigate = useNavigate();
    const [isLoading, setLoading] = useState(false);

    const handleClick = (e, item) => {
        e.preventDefault();
        
        navigate(`/guides/detail/${item.guideId}`);
    };

    useEffect(() => {
        const fetchGuideList = async () => {
            try {
                const response = await AssignmentApi.getGuideListByTourId(id, currentPage, 6);

                if (response?.code === 1976) {
                    setGuides(response.result.content);
                    setTotalPages(response.result.totalPages);
                }
            }
            catch (error) {
                console.error("Failed to fetch guides:", error);
                
                if (error?.status === 401 || error === 'Network Error') {
                    navigate("/auth/login");
                }
            }
            finally {
                setLoading(true);
            }
        };

        fetchGuideList();
    }, [id, navigate, currentPage]);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="guide-page">
            <ScrollToTop />
            <h1>Hướng dẫn viên du lịch</h1>
            <h2 className="message">Vui lòng chọn hướng dẫn viên du lịch</h2>

            <section className="guide-list">
                <h2>Danh sách hướng dẫn viên</h2>
                <div className="guide-grid">
                    {guides.length > 0 ? (
                        guides.map((item, index) => (
                            <Link to={`/orders/${item.id}`} key={index} className="guide-card">
                                <img src={item.guideAvatar ? item.guideAvatar : noImage} alt={item.guideName} />
                                <h3>{item.guideName}</h3>
                                <p>Thông tin: <span>{item.gender}</span> - <span>{ageCalculate(item.dateOfBirth)} tuổi</span></p>
                                <p>Đã đặt: <span>{item.numberOfPeople}</span>/<span className="red"><b>{item.totalPeople}</b></span></p>
                                <p>Ngày khởi hành: <span>{formatDatetime(item.startDate)}</span></p>
                                <p>Ngày kết thúc: <span>{formatDatetime(item.endDate)}</span></p>
                                <p>Đánh giá: {formatErevaluate(item.evaluate)}</p>
                                <p>Giá: <span className="red">{formatCurrency(item.guidePrice)}</span></p>
                                <p id="link-detail">
                                    <button type="button" onClick={(e) => handleClick(e, item)}>
                                        Xem chi tiết
                                    </button>
                                </p>
                            </Link>
                        ))
                    ) : (
                        <p id="error-message">Hiện tại, tour này chưa có hướng dẫn viên!</p>
                    )}
                </div>
            </section>

            <div className="pagination">
                <button disabled={currentPage === 0} onClick={() => setCurrentPage(currentPage - 1)}>
                    &lt;
                </button>
                <span>Trang {currentPage + 1} / {totalPages}</span>
                <button disabled={currentPage >= totalPages - 1} onClick={() => setCurrentPage(currentPage + 1)}>
                    &gt;
                </button>
            </div>

            <section className="why-choose">
                <h2>Tại sao chọn hướng dẫn viên của chúng tôi?</h2>
                <ul>
                    <li>Kiến thức sâu rộng về lịch sử, văn hóa và địa phương</li>
                    <li>Kỹ năng giao tiếp và thuyết trình xuất sắc</li>
                    <li>Linh hoạt và sáng tạo trong việc xử lý tình huống</li>
                    <li>Tận tâm và chu đáo với khách hàng</li>
                    <li>Thường xuyên được đào tạo và cập nhật kiến thức mới</li>
                </ul>
            </section>
        </div>
    );
};

export default memo(GuideListPage);