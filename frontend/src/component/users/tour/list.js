import { memo } from 'react';
import { Link } from 'react-router-dom';
import './list.scss';
import formaterCurrency from 'utils/formatCurrency';
import PropTypes from 'prop-types';
import { sanitizeHtml } from 'utils/sanitizeHtml';

const TourList = ({ data }) => {
    return (
        <div className="tour-list">
            <h1>Danh sách các Tour</h1>
            <h3>Khám phá các tour cùng Sao Việt hấp dẫn với nhiều địa điểm du lịch tuyệt vời</h3>
            
            <div className="sub-list" >
                {data && Array.isArray(data) && data.length > 0 ? (
                    data.map((item, index) => (
                        <div className="tour-item" key={index}>
                            <Link to={`/tours/detail/${item.id}`} id="link-item">
                                <img src={`${item.image || '/assets/users/img/tour/no-image.jpg'}`} alt={`${item.name}`} />
                                <div className="tour-info">
                                    <h2>{item.name}</h2>
                                    <p id="orders">
                                        {item.orders && item.orders > 0 ? `${item.orders} lượt đặt` : null}
                                    </p>
                                    <p dangerouslySetInnerHTML={{ __html: sanitizeHtml(item.introduce) }}></p>
                                    <p id="cost"><strong>Giá: </strong>{formaterCurrency(item.price)}</p>
                                </div>
                            </Link>
                        </div>
                    ))): (
                        <div style={{ width: "100%", height: "650px", color: "red"}}>
                            <h2>Không tìm được Tour phù hợp!</h2>
                        </div>
                    )
                }
            </div>
        </div>
    )
}

TourList.propTypes = {
    data: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            image: PropTypes.string.isRequired,
            name: PropTypes.string.isRequired,
            introduce: PropTypes.string.isRequired,
            price: PropTypes.number.isRequired,
        })
    ).isRequired
};

export default memo(TourList);