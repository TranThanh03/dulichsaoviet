import React, { useState, memo } from 'react';
import PropTypes from 'prop-types';
import './slideTour.scss';
import formatCurrency from 'utils/formatCurrency';
import { Link } from 'react-router-dom';

const SlideTour = ({ data, itemsPerSlide, itemWidth }) => {
    const [currentSlide, setCurrentSlide] = useState(0);
    const totalSlides = data && Array.isArray(data) ? Math.ceil(data.length / itemsPerSlide) : 0;

    const goToSlide = (index) => setCurrentSlide(Math.max(0, Math.min(index, totalSlides - 1)));

    return (
        <div className="list-tour">
            <div className="title">
                <span>Tours mới nhất</span>
                <Link to="/tours/index">
                    <span>Xem thêm</span>
                </Link>
            </div>
            <div className="container">
                <div
                    className="content"
                    style={{
                        transform: `translateX(-${currentSlide * itemWidth}px)`,
                    }}
                >
                    {data && Array.isArray(data) && data.length > 0 ? (
                        data.map((item, index) => (
                            <div className="slide" key={index}>
                                <Link to={`/tours/detail/${item.id}`}>
                                    <div className="sub-list">
                                        <img src={`${item.image || '/assets/users/img/tour/no-image.jpg'}`} alt={item.name} />
                                        <h4>{item.name}</h4>
                                        <span style={{color: 'red'}}>{formatCurrency(item.price)}</span>
                                        <span id="orders">
                                            {item.orders && item.orders > 0 ? `${item.orders} lượt đặt` : null}
                                        </span>
                                    </div>
                                </Link>
                            </div>
                        ))
                    ) : (
                        <p>Danh sách tour đang trống!</p>
                    )}
                </div>
                <button
                    className="button-prev"
                    onClick={() => goToSlide(currentSlide - 1)}
                >
                    <span>&#10094;</span>
                </button>
                <button
                    className="button-next"
                    onClick={() => goToSlide(currentSlide + 1)}
                >
                    <span>&#10095;</span>
                </button>
            </div>
        </div>
    );
};

SlideTour.propTypes = {
    data: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            image: PropTypes.string.isRequired,
            name: PropTypes.string.isRequired,
            price: PropTypes.number.isRequired,
        })
    ).isRequired,
    itemsPerSlide: PropTypes.number.isRequired,
    itemWidth: PropTypes.number.isRequired,
};

export default memo(SlideTour);