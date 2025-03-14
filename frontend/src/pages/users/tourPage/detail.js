import { memo, useState, useEffect } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import './detail.scss';
import { TourApi } from 'api';
import formaterCurrency from 'utils/formatCurrency';
import { sanitizeHtml } from 'utils/sanitizeHtml';

const TourDetailPage = () => {
    const [tour, setTour] = useState([]);
    const { id } = useParams();
    const navigate = useNavigate();
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTour = async () => {
            try {
                const response = await TourApi.getById(id);
                
                if (response.code !== 1009 && response?.result) {
                    setTour(response.result);
                }
                else {
                    navigate("/error/404");
                }
            }
            catch (error) {
                console.error("Failed to fetch tour:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchTour();
    }, [id, navigate]);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="tour-detail-page">
            <h1>{tour.name}</h1>
            <h3 id="orders">
                {tour.orders && tour.orders > 0 ? `${tour.orders} lượt đặt` : null}
            </h3>
            
            <div className="tour-info">
                <div className="tour-image">
                    <img src={`${tour.image || '/assets/users/img/tour/no-image.jpg'}`} alt={`${tour.name}`} />
                </div>
                <div className="tour-infor">
                    <h2 id="introduce-tour">Giới thiệu:</h2>
                    <span id="introduce" dangerouslySetInnerHTML={{ __html: sanitizeHtml(tour.introduce) }}></span>
                    <h2 id="price-tour">Giá Tour:</h2>
                    <p id="price">{formaterCurrency(tour.price)}</p>
                    <Link to={`/guides/list/${tour.id}`}><button className="book-now">Đặt ngay</button></Link>
                </div>
            </div>

            <div className="tour-schedule">
                <h2>Nội dung chi tiết</h2>
                <div className="tour-detail" dangerouslySetInnerHTML={{ __html: sanitizeHtml(tour.description) }}></div>
            </div>
        </div>
    )
}

export default memo(TourDetailPage);