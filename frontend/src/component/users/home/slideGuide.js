import React, { useState, memo } from 'react';
import PropTypes from 'prop-types';
import './slideGuide.scss';
import { Link } from 'react-router-dom';
import ageCalculate from 'utils/ageCalculation';

const SlideGuide = ({ data, itemsPerSlide, itemWidth }) => {
    const [currentSlide, setCurrentSlide] = useState(0);
    const totalSlides = data && Array.isArray(data) ? Math.ceil(data.length / itemsPerSlide) : 0;

    const goToSlide = (index) => setCurrentSlide(Math.max(0, Math.min(index, totalSlides - 1)));

    return (
        <div className="list-guide">
            <div className="title">
                <span>Hướng dẫn viên nổi bật</span>
                <Link to="/guides/index">
                    <span>Xem thêm</span>
                </Link>
            </div>
            <div className="container">
                <div
                    className="content"
                    id="guide"
                    style={{
                        transform: `translateX(-${currentSlide * itemWidth}px)`,
                    }}
                >
                    {data && Array.isArray(data) && data.length > 0 ? (
                        data.map((item, index) => (
                            <div className="slide" key={index}>
                                <Link to={`/guides/detail/${item.id}`}>
                                    <div className="sub-list" id="sub-guide">
                                        <img id='avatar-guide' src={`${item.avatar || '/assets/users/img/guide/no-image.jpg'}`} alt={item.fullName} />
                                        <h4>{item.fullName}</h4>
                                        <span>{ageCalculate(item.dateOfBirth)} tuổi</span>
                                    </div>
                                </Link>
                            </div>
                        ))
                    ) : (
                        <p>Danh sách hướng dẫn viên đang trống!</p>
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

SlideGuide.propTypes = {
    data: PropTypes.arrayOf(
        PropTypes.shape({
            id: PropTypes.string.isRequired,
            avatar: PropTypes.string.isRequired,
            fullName: PropTypes.string.isRequired,
            dateOfBirth: PropTypes.oneOfType([
                PropTypes.string,
                PropTypes.instanceOf(Date)
            ]).isRequired,
        })
    ).isRequired,
    itemsPerSlide: PropTypes.number.isRequired,
    itemWidth: PropTypes.number.isRequired,
};

export default memo(SlideGuide);