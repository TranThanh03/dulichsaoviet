import { memo, useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import './detail.scss';
import { AssignmentApi, GuideApi } from 'services';
import ageCalculate from 'utils/ageCalculation';
import formatEvaluate from 'utils/formatEvaluate';
import formatCurrency from 'utils/formatCurrency';
import formatDatetime from 'utils/formatDatetime';
import { sanitizeHtml } from 'utils/sanitizeHtml';
import { noImage } from 'assets';

const GuideDetailPage = () => {
    const [guide, setGuide] = useState([]);
    const [tours, setTours] = useState([]);
    const { id } = useParams();
    const navigate = useNavigate();
    const [isLoading, setLoading] = useState(false);

    useEffect(()=> {
        const fetchGuide = async () => {
            try {
                const response = await GuideApi.getById(id);
                
                if (response.code !== 1009 && response?.result) {
                    setGuide(response.result);
                }
                else {
                    navigate("/error/404");
                }
            }
            catch (error) {
                console.error("Failed to fetch guide:", error);
            }
            finally {
                setLoading(true);
            }
        };

        const fetchTours = async () => {
            try {
                const response = await AssignmentApi.getTourListByGuideId(id);
                setTours(response.result);
            }
            catch (error) {
                console.error("Failed to fetch tours:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchGuide();
        fetchTours();
    },[id, navigate])

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="guide-detail-page">
            <div className="guide-profile">
                <img src={guide.avatar ? guide.avatar : noImage} alt={guide.fullName} className="guide-image" />
                <div className="guide-info">
                    <h1>{guide.fullName}</h1>
                    <p><strong>Thông tin:</strong> {guide.gender} - {ageCalculate(guide.dateOfBirth)} tuổi</p>
                    <p><strong>Số điện thoại:</strong> {guide.phone}</p>
                    <p><strong>Email:</strong> {guide.email}</p>
                    <p><strong>Đánh giá:</strong> {formatEvaluate(guide.evaluate)}</p>
                </div>
            </div>

            <section className="guide-description">
                <h2>Giới thiệu</h2>
                <p id="description" dangerouslySetInnerHTML={{ __html: sanitizeHtml(guide.description) }}></p>
            </section>

            <section className="guide-booking">
                <h2>Đặt tour với {guide.fullName}</h2>
                {tours && Array.isArray(tours) && tours.length > 0 ? (
                    tours.map((item, index) => (
                        <div className="tour-infor" key={index}>
                            <Link to={`/tours/detail/${item.tourId}`}>
                                <img src={item.tourImage ? item.tourImage : noImage} alt={item.tourName} id="tour-image" />
                            </Link>
                            <h3>{item.tourName}</h3>
                            <p><b>Đã đặt: </b>{item.numberOfPeople}/<span className="red"><b>{item.totalPeople}</b></span></p>
                            <p><b>Ngày khởi hành: </b>{formatDatetime(item.startDate)}</p>
                            <p><b>Ngày kết thúc: </b>{formatDatetime(item.endDate)}</p>
                            <p id="tour-cost"><strong>Giá Tour:</strong> <span className="red">{formatCurrency(item.tourPrice)}</span></p>
                            <p><strong>Giá HDV:</strong> <span className="red">{formatCurrency(item.guidePrice)}</span></p>
                            {item.numberOfPeople < item.totalPeople && ( 
                                <Link to={`/orders/${item.id}`}>
                                    <button className="book-button">Đặt tour</button>
                                </Link>
                            )}
                        </div>   
                    ))) : (
                        <p className="red">Hướng dẫn viên này hiện chưa được phân công!</p>
                    )
                }
            </section>
        </div>
    )
}

export default memo(GuideDetailPage);