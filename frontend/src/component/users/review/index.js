import { userAvatar } from 'assets';
import { memo, useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import ReviewApi from 'services/reviewApi';
import "./index.scss";
import formatDatetime from 'utils/formatDatetime';
import { SuccessToast, ErrorToast } from 'component/notifi';
import { ToastContainer } from 'react-toastify';
import Swal from 'sweetalert2';

const ReviewList = ({ tourId }) => {
    const [reviews, setReviews] = useState([]);
    const [avgRating, setAvgRating] = useState(0);
    const [ratingStats, setRatingStats] = useState({});

    const fetchReview = async () => {
        try {
            const response = await ReviewApi.getAll(tourId);

            if (response?.code === 2001) {
                setReviews(response?.result);
            }
        } catch (error) {
            console.error("Failed to fetch reviews: ", error);
        }
    }

    useEffect(() => {
        fetchReview();
    }, [tourId])

    useEffect(() => {
        if (reviews.length > 0) {
            const total = reviews.reduce((sum, review) => sum + review.rating, 0);
            setAvgRating(total/reviews.length);

            const ratingCount = reviews.reduce((acc, review) => {
                acc[review.rating] = (acc[review.rating] || 0) + 1;
                return acc;
            }, {});

            setRatingStats(ratingCount); 
        }
    }, [reviews]);

    const handleDelete = async (id) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: "Bạn có chắc chắn xóa đánh giá này không?",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await ReviewApi.delete(id);

                if (response?.code === 2002) {
                    SuccessToast("Xóa đánh giá thành công.")
                    fetchReview();
                }
                else {
                    ErrorToast("Xóa đánh giá không thành công.")
                }
            } catch (error) {
                console.error("Failed to delete review: ", error);
                ErrorToast("Đã xảy ra lỗi không xác định! Vui lòng thử lại.")
            }
        }
    }

    return (
        <div className="review">
            <h3>Đánh giá của khách hàng</h3>
            <div className="clients-reviews bgc-black mt-30 mb-60">
                <div className="left" data-aos="fade-left" data-aos-duration="1500" data-aos-offset="50">
                    <b>{avgRating}</b>
                    <span>({reviews.length} đánh giá)</span>
                    <div className="ratting">
                        {[...Array(5)].map((_, i) => (
                            <i
                                key={i}
                                className={i < Math.floor(avgRating) ? "fas fa-star" : "far fa-star"}
                            ></i>
                        ))}
                    </div>
                </div>

                {reviews.length > 0 && [5, 4, 3, 2, 1].map((star, index) => (
                    <div key={index} className="left breakdown" data-aos="fade-left" data-aos-duration="1500" data-aos-offset="50">
                        <b>{star}</b>
                        <span>({ratingStats[star] || 0} đánh giá)</span>
                        <div className="ratting">
                            {[...Array(5)].map((_, i) => (
                                <i
                                    key={i}
                                    className={i < star ? "fas fa-star" : "far fa-star"}
                                ></i>
                            ))}
                        </div>
                    </div>
                ))}
            </div>

            <h3>Ý kiến của khách hàng</h3>
            <div className="comments mt-30 mb-60" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                {reviews.length > 0 && (
                    reviews.map((item, index) => {
                        return (
                            <div key={index} className="comment-body">
                                <div className="author-thumb">
                                    <img src={userAvatar} alt="avatar" />
                                </div>
                                <div className="content">
                                    <h6>{item.fullName ?? `Khách hàng ${++index}`}</h6>
                                    <div className="ratting">
                                        {[...Array(5)].map((_, i) =>
                                            i < item.rating ? (
                                                <i key={i} className="fas fa-star"></i>
                                            ) : (
                                                <i key={i} className="far fa-star"></i>
                                            )
                                        )}
                                    </div>
                                    <span className="time">{formatDatetime(item.timeStamp)}</span>
                                    <p>{item.comment}</p>
                                </div>

                                {item.customer === true && (
                                    <div className="control">
                                        <button type="button" onClick={() => handleDelete(item.id)}>
                                            <i className="fa-regular fa-trash-can"></i>
                                        </button>
                                    </div>
                                )}
                            </div>
                        )
                    })
                )}
            </div>

            <ToastContainer />
        </div>
    );
};

export default memo(ReviewList);