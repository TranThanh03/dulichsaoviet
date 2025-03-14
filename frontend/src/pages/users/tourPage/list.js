import { memo, useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { TourApi } from 'api';
import { TourList } from 'component/users';
import "./index.scss";

const TourListPage = () => {
    const [tours , setTours] = useState([]);
    const { id } = useParams();
    const navigate = useNavigate();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTourList = async () => {
            try {
                const response = await TourApi.getByCategoryId(id, currentPage, pageSize);

                if (response?.code === 1984) {
                    setTours(response?.result?.content);
                    setTotalPages(response?.result?.totalPages);
                }
            } 
            catch (error) {
                console.error("Failed to fetch tours:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchTourList();
    }, [id, navigate, currentPage]);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="tour-page">
            <TourList data={tours}/>

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
    )
}

export default memo(TourListPage);