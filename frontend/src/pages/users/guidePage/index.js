import { memo, useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { Link } from 'react-router-dom';
import { GuideApi } from 'services';
import "./index.scss";
import ageCalculate from 'utils/ageCalculation';
import formatEvaluate from 'utils/formatEvaluate';

const GuidePage = () => {
    const [guides, setGuides] = useState([]);
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const { id } = useParams();
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchGuideList = async () => {
            try {
                const response = await GuideApi.getAll({ page: currentPage, size: pageSize });
                setGuides(response.result.content);
                setTotalPages(response.result.totalPages);
            }
            catch (error) {
                console.error("Failed to fetch guides:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchGuideList();
    }, [id, currentPage]);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="guide-page">
            <h1>Hướng dẫn viên du lịch</h1>

            <section className="guide-list">
                <h2>Danh sách hướng dẫn viên</h2>
                <div className="guide-grid">
                    {guides.length > 0 ? (
                        guides.map((item) => ( 
                            <div className="guide-card" key={item.id}>
                                <Link to={`/guides/detail/${item.id}`}>
                                    <img src={`${item.avatar || '/assets/users/img/guide/no-image.jpg'}`} alt={item.fullName} />
                                    <h3>{item.fullName}</h3>
                                    <p>
                                        Thông tin: <span>{item.sex}</span> - 
                                        <span> {ageCalculate(item.dateOfBirth)} tuổi</span>
                                    </p>
                                    <p>Điện thoại: <span>{item.phone}</span></p>
                                    <p>Đánh giá: {formatEvaluate(item.evaluate)}</p>
                                </Link>
                            </div>
                        ))
                    ) : (
                        <p id="error-message">Danh sách hướng dẫn viên đang trống!</p>
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
        </div>
    );
};

export default memo(GuidePage);