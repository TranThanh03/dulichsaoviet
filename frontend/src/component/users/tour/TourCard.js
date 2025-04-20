import { noImage } from 'assets';
import { memo } from 'react';
import { Link } from 'react-router-dom';

const TourCard = ({ tour }) => {
    const {
        images = [],
        destination,
        rating = 0,
        title,
        time,
        quantity,
        priceAdult,
        tourId
    } = tour;

    const formattedPrice = new Intl.NumberFormat('vi-VN').format(priceAdult);

    return (
        <div className="tour-card-custom col-xl-4 col-md-6">
            <div className="destination-item tour-grid style-three bgc-lighter block_tours equal-block-fix" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                <div className="image">
                    <i className="fas fa-heart heart" />
                    <img src={images[0] ? images[0] : noImage} alt="image" />
                </div>

                <div className="content equal-content-fix">
                    <div className="destination-header">
                        <span className="location">
                            <i className="fal fa-map-marker-alt"></i> {destination}
                        </span>

                        <div className="ratting">
                            {[...Array(5)].map((_, i) =>
                                i < rating ? (
                                    <i key={i} className="fas fa-star"></i>
                                ) : (
                                    <i key={i} className="far fa-star"></i>
                                )
                            )}
                        </div>
                    </div>
                    <h6>{title}</h6>
                    <ul className="blog-meta">
                        <li><i className="far fa-clock"></i> {time}</li>
                        <li><i className="far fa-user"></i> {quantity}</li>
                    </ul>
                    <div className="destination-footer">
                        <span className="price">
                            <span>{formattedPrice}</span> đ / người
                        </span>
                        <Link to={`/tour/detail/${tourId}`} className="theme-btn style-two style-three">
                            <i className="fal fa-arrow-right"></i>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default memo(TourCard);