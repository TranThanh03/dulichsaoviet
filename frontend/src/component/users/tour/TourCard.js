import { memo } from 'react';
import { Link } from 'react-router-dom';
import "./TourCard.scss";

const TourCard = ({ tour }) => {
  const renderStars = (rating) => {
    const stars = [];
    for (let i = 0; i < 5; i++) {
      stars.push(
        <i
          key={i}
          className={`fa-star ${i < rating ? 'fas text-yellow-400' : 'far text-gray-300'}`}
        ></i>
      );
    }
    return stars;
  };

  return (
    <div className="tour-page mb-[30px]">
      <div
        className="destination-item tour-grid bg-gray-50 rounded-lg shadow-md overflow-hidden h-full"
        data-aos="fade-up"
        data-aos-duration="1500"
        data-aos-offset="50"
      >
        <div className="image relative">
          <span className="badge absolute top-3 left-3 bg-pink-500 text-white text-xs font-semibold py-1 px-2 rounded">
            Featured
          </span>
          
          <img
            src={tour.image} 
            alt="image"
            className="w-full h-48 object-cover"
          />
          <div className="absolute bottom-3 right-3 flex space-x-1">
            {renderStars(tour.rating)}
          </div>
        </div>

        <div className="content p-4">
          <div className="destination-header flex justify-between items-center mb-2">
            <span className="location text-gray-500 text-sm">
              <i className="fas fa-map-marker-alt mr-1"></i>
              {tour.destination}
            </span>
          </div>

          <h6 className="text-lg font-semibold mb-2">
              {tour.title}
          </h6>

          <ul className="blog-meta flex space-x-4 text-gray-500 text-sm mb-3">
            <li>
              <i className="far fa-clock mr-1"></i>
              {tour.time}
            </li>
            <li>
              <i className="far fa-user mr-1"></i>
              {tour.quantity}
            </li>
          </ul>

          <div className="destination-footer flex justify-between items-center">
            <span className="price text-lg font-bold text-gray-800">
              <span>{tour.priceAdult}</span> <span className="text-gray-600">VND/người</span>
            </span>
            <Link
                to={`/tour/detail/${tour.tourId}`}
                className="theme-btn bg-white text-black border border-gray-300 rounded-full p-2"
                >
                <i className="fas fa-arrow-right"></i>
                </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default memo(TourCard);