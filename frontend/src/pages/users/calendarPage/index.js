import { memo, useEffect, useState } from 'react';
import './index.scss';
import { useNavigate, Link } from 'react-router-dom';
import Swal from 'sweetalert2';
import formatCurrency from 'utils/formatCurrency';
import formatDatetime from 'utils/formatDatetime';
import { noImage } from 'assets/user';
import { BookingApi, TourApi } from 'services';
import { ErrorToast, SuccessToast } from 'component/notifi';
import { ToastContainer } from 'react-toastify';

const CalendarPage = () => {
    const statusClassMap = {
        'Đang xử lý': 'pending',
        'Đã xác nhận': 'confirm',
        'Đã hủy': 'cancel'
    };

    const [toursPopular, setToursPopular] = useState([]);
    const [bookings, setBookings] = useState([]);
    const navigate = useNavigate();
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchPopularTours = async () => {
            setLoading(true);

            try {
                const response = await TourApi.threePopular();

                if (response?.code === 1508) {
                    setToursPopular(response?.result);                    
                }
            }
            catch (error) {
                console.error("Failed to fetch tours: ", error);
            }
            finally {
                setLoading(false);
            }
        };

        fetchPopularTours();
    }, []);

    useEffect(() => {
        const fetchBookings = async () => {
            setLoading(true);

            try {
                const response = await BookingApi.getByCustomerId();

                if (response?.code === 1801) {
                    setBookings(response?.result);
                }
            }
            catch (error) {
                console.error("Failed to fetch bookings: ", error);

                if (error?.status === 401) {
                    navigate("/auth/login");
                }
            }
            finally {
                setLoading(false);
            }
        };

        fetchBookings();
    }, []);

    const handleCancel = async (id, code) => {
        const result = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn muốn hủy lịch đặt <b>${code}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (result.isConfirmed) {
            try {
                const response = await BookingApi.cancel(id);

                if (response.code === 1803) {
                    SuccessToast("Lịch đặt đã được hủy thành công.");

                    setBookings((prev) =>
                        prev.map((item) =>
                            item.id === id ? { ...item, status: "Đã hủy" } : item
                        )
                    );
                } else {
                    ErrorToast(response.message || "Hủy lịch đặt không thành công.")
                }
            } catch (error) {
                console.log("Failed to fetch cancel: ", error);
                ErrorToast("Đã xảy ra lỗi khi hủy lịch đặt! Vui lòng thử lại sau.")
            }
        }
    };

    if (isLoading) {
        return (
            <div style={{height: 1000}}></div>
        );
    }

    return (
        <>
            <section className="calendar-page tour-list-page pt-50 pb-100 rel z-1">
                <div className="container">
                    <div className="row">
                        <div className="tour-list-custom col-lg-3 col-md-6 col-sm-10 rmb-75">
                            <div className="shop-sidebar mb-30">
                                <div className="widget widget-tour" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                    <h6 className="widget-title fw-bold">Tours phổ biến</h6>
                                    {toursPopular.length > 0 && (
                                        toursPopular.map((item, index) => (
                                            <div key={index} className="destination-item tour-grid style-three bgc-lighter">
                                                <div className="image">
                                                    <img src={item.image || noImage} alt="Tour" />
                                                </div>
                                                <div className="content">
                                                    <div className="destination-header">
                                                        <span className="location">
                                                            <i className="fal fa-map-marker-alt"></i>
                                                            {item.destination}
                                                        </span>
                                                        <div className="ratting">
                                                            {[...Array(5)].map((_, i) =>
                                                                i < item.rating ? (
                                                                    <i key={i} className="fas fa-star"></i>
                                                                ) : (
                                                                    <i key={i} className="far fa-star"></i>
                                                                )
                                                            )}
                                                        </div>
                                                    </div>
                                                    <h6>
                                                        <Link to={`/tour/detail/${item.id}`} className="fw-bold">{item.name}</Link>
                                                    </h6>
                                                </div>
                                            </div>
                                        ))
                                    )}
                                </div>
                            </div>
                        </div>

                        <div className="calendar col-lg-9" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                            {bookings.length > 0 && (
                                bookings.map((item, index) => (
                                    <div key={index} className="destination-item style-three bgc-lighter">
                                        <div className="image">
                                            <Link to={`/tour/detail/${item.tourId}`}>
                                                <span className={`badge ${statusClassMap[item.status] || ''}`}>
                                                    {item.status}
                                                </span>
                                                <img src={item.image ?? noImage} alt="Tour" />
                                            </Link>
                                        </div>
                                        <div className="content">
                                            <div className="destination-header">
                                                <div className="booking-code">
                                                    Mã lịch đặt:
                                                    <span className="ms-2">{item.code}</span>
                                                </div>
                                                <div className="booking-info">
                                                    <span className="location">
                                                        <i className="fal fa-map-marker-alt"></i>
                                                        {item.destination}
                                                    </span>
                                                    <div className="ratting">
                                                        {[...Array(5)].map((_, i) =>
                                                            i < item.rating ? (
                                                                <i key={i} className="fas fa-star"></i>
                                                            ) : (
                                                                <i key={i} className="far fa-star"></i>
                                                            )
                                                        )}
                                                    </div>
                                                </div>
                                            </div>

                                            <h5>
                                                <Link to={`/tour/detail/${item.tourId}`}>{item.tourName}</Link>
                                            </h5>

                                            <ul className="blog-meta">
                                                <li className="w-100">
                                                    <ul className="sub-meta">
                                                        <li>
                                                            <i className="fa-solid fa-calendar-days"></i>
                                                            {item.quantityDay ? `${item.quantityDay} ngày ${item.quantityDay-1} đêm` : ''}
                                                        </li>
                                                        <li style={{paddingRight: '31px'}}>
                                                            <i className="far fa-user"></i>
                                                            {item.people}
                                                        </li>
                                                    </ul>
                                                </li>
                                                <li className="w-100">
                                                    <ul className="sub-meta">
                                                        <li>
                                                            <i className="far fa-clock"></i>
                                                            {item.bookingTime ? formatDatetime(item.bookingTime) : ''}
                                                        </li>
                                                        <li style={{minWidth: '92px'}}>
                                                            <i className="fa-regular fa-credit-card"></i>
                                                            {item.method}
                                                        </li>
                                                    </ul>
                                                </li>
                                            </ul>

                                            <div className="destination-footer">
                                                <div>
                                                    <span className="price">
                                                        <span>{item.totalPrice ? formatCurrency(item.totalPrice) : 0}</span>
                                                    </span>
                                                </div>

                                                {item.status === 'Đang xử lý' || item.reviewed ? (
                                                    <div className="control">
                                                        {item.status === 'Đang xử lý' && (
                                                            <button className="theme-btn bg-red style-two style-three" onClick={() => handleCancel(item.id, item.code)}>
                                                                <span data-hover="Hủy">Hủy</span>
                                                            </button>
                                                        )}

                                                        {item.reviewed && (
                                                            <Link to={`/tour/detail/${item.tourId}?bookingId=${item.id}`} className="theme-btn bg-yellow style-two style-three">
                                                                <span data-hover="Đánh giá">Đánh giá</span>
                                                            </Link>
                                                        )}

                                                        <Link to={`/calendar/detail/${item.id}`} className="theme-btn style-two style-three float-right">
                                                            <span data-hover="Chi tiết">Chi tiết</span>
                                                            <i className="fal fa-arrow-right"></i>
                                                        </Link>
                                                    </div>
                                                ) : (
                                                    <div>
                                                        <Link to={`/calendar/detail/${item.id}`} className="theme-btn style-two style-three float-right">
                                                            <span data-hover="Chi tiết">Chi tiết</span>
                                                            <i className="fal fa-arrow-right"></i>
                                                        </Link>
                                                    </div>
                                                )}
                                            </div>
                                        </div>
                                    </div>
                                ))
                            )}
                        </div>
                    </div>
                </div>
            </section>

            <ToastContainer />
        </>
    );
};

export default memo(CalendarPage);