import { memo, useState, useEffect } from 'react';
import { Link, useParams, useNavigate } from 'react-router-dom';
import './detail.scss';
import { TourApi } from 'services';
import formaterCurrency from 'utils/formatCurrency';
import { sanitizeHtml } from 'utils/sanitizeHtml';
import { noImage } from 'assets';
import ReviewList from "component/users/review/index";

const TourDetailPage = () => {
    const { id } = useParams();
    const [tour, setTour] = useState({});
    const navigate = useNavigate();
    const [review, setReviews] = useState({
        rating: 5,
        comment: ''
    });

    useEffect(() => {
        const fetchTour = async () => {
            try {
                const response = await TourApi.getById(id);

                if (response?.code === 1502) {
                    setTour(response?.result);
                } else {
                    navigate("/error/404");
                }
            } catch (error) {
                console.error("Failed to fetch tour: ", error);
                navigate("/error/404");
            }
        }

        fetchTour();
    }, [id])

    return (
        <div className="tour-detail-page">
            <div className="tour-gallery">
                <div className="container-fluid">
                    <div className="row gap-10 justify-content-center rel img-custom">
                        {tour.image && tour.image.length > 0 && (
                            Array.from({ length: 4 }).map((_, index) => {
                                const item = tour.image[index % tour.image.length];

                                return (
                                    <div className={`${index === 0 || index === 3 ? 'col-lg-6' : 'col-lg-4'} col-md-6`} key={index} 
                                        data-aos="fade-up" data-delay="2000" data-aos-duration="1500" data-aos-offset="50">

                                        <div className="gallery-item">
                                            <img src={item ?? noImage} alt="tour-image" />
                                        </div>
                                    </div>
                                );
                            })
                        )}
                    </div>
                </div>
            </div>

            <section className="tour-header-area pt-40 rel z-1">
                <div className="container">
                    <div className="row justify-content-between">
                        <div className="col-xl-6 col-lg-7">
                            <div className="tour-header-content mb-15" data-aos="fade-left" data-aos-duration="1500" data-aos-offset="50">
                                <span className="location d-inline-block mb-10"><i className="fal fa-map-marker-alt me-1"></i>{tour.destination}</span>
                                <div className="section-title pb-5">
                                    <h2>{tour.name}</h2>
                                </div>
                            </div>
                        </div>
                        <div className="col-xl-4 col-lg-5 text-lg-end" data-aos="fade-right" data-aos-duration="1500" data-aos-offset="50">
                            <div className="tour-header-social mb-10">
                                <Link to="#"><i className="far fa-share-alt"></i>Share tours</Link>
                                <Link to="#"><i className="fas fa-heart bgc-secondary"></i>Wish list</Link>
                            </div>
                        </div>
                    </div>
                    <hr className="mt-50 mb-70" />
                </div>
            </section>

            <section className="tour-details-page pb-100">
                <div className="container">
                    <div className="row">
                        <div className="col-lg-8">
                            <div className="tour-details-content">
                                <h3>Khám phá Tours</h3>
                                <p>{tour.description}</p>
                                <div className="row pb-55">
                                    <div className="col-md-6" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                        <div className="tour-include-exclude mt-30">
                                            <h5>Bao gồm</h5>
                                            <ul className="list-style-one check mt-25">
                                                <li><i className="far fa-check"></i> Dịch vụ đón và trả khách</li>
                                                <li><i className="far fa-check"></i> Một bữa ăn mỗi ngày</li>
                                                <li><i className="far fa-check"></i> Vé máy bay/vé tàu/vé tham quan (nếu có)</li>
                                                <li><i className="far fa-check"></i> Nước đóng chai trên xe buýt</li>
                                                <li><i className="far fa-check"></i> Hướng dẫn viên du lịch</li>
                                                <li><i className="far fa-check"></i> Bảo hiểm du lịch</li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div className="col-md-6" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                        <div className="tour-include-exclude mt-30">
                                            <h5>Không bao gồm</h5>
                                            <ul className="list-style-one mt-25">
                                                <li><i className="far fa-times"></i> Chi phí ăn uống, tham quan ngoài chương trình</li>
                                                <li><i className="far fa-times"></i> Đón và trả khách tại khách sạn</li>
                                                <li><i className="far fa-times"></i> Chi phí làm hộ chiếu, visa (nếu có)</li>
                                                <li><i className="far fa-times"></i> Thuế VAT</li>
                                                <li><i className="far fa-times"></i> Tiền tip cho hướng dẫn viên, tài xế</li>
                                                <li><i className="far fa-times"></i> Dịch vụ bổ sung</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <h3>Lịch trình</h3>
                            <div className="accordion-two mt-25 mb-60" id="faq-accordion-two">
                                {/* @php
                                    $day = 1;
                                @endphp
                                @foreach ($tourDetail->timeline as $timeline)
                                    <div className="accordion-item">
                                        <h5 className="accordion-header">
                                            <button className="accordion-button collapsed" data-bs-toggle="collapse"
                                                data-bs-target="#collapseTwo{{ $timeline->timeLineId }}">
                                                Ngày {{ $day++ }} - {{ $timeline->title }}
                                            </button>
                                        </h5>
                                        <div id="collapseTwo{{ $timeline->timeLineId }}" className="accordion-collapse collapse"
                                            data-bs-parent="#faq-accordion-two">
                                            <div className="accordion-body">
                                                <p>{!! $timeline->description !!}</p>
                                            </div>
                                        </div>
                                    </div>
                                @endforeach */}
                            </div>

                            <div id="partials_reviews">
                                <ReviewList tourId={id} />
                            </div>

                            <h3 className="{{ $checkDisplay }}">Thêm đánh giá</h3>
                            <form id="comment-form" className="comment-form bgc-lighter z-1 rel mt-30" name="review-form" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                <div className="comment-review-wrap">
                                    <div className="comment-ratting-item">
                                        <span className="title">Đánh giá:</span>
                                        <div className="ratting" id="rating-stars">
                                            <div className="ratting" id="rating-stars">
                                                {[1, 2, 3, 4, 5].map((value) => (
                                                    <i
                                                        key={value}
                                                        className={value <= review.rating ? "fas fa-star" : "far fa-star"}
                                                        data-value={value}
                                                        onClick={() => setReviews(prev => ({ ...prev, rating: value }))}
                                                    ></i>
                                                ))}
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <hr className="mt-30 mb-40" />
                                <h5>Để lại phản hồi</h5>
                                <div className="row gap-20 mt-20">
                                    <div className="col-md-12">
                                        <div className="form-group">
                                            <label for="message">Nội dung:</label>
                                            <textarea name="message" id="message" className="form-control" rows="5" required=""></textarea>
                                        </div>
                                    </div>
                                    <div className="col-md-12">
                                        <div className="form-group mb-0">
                                            <button type="submit" className="theme-btn bgc-secondary style-two" id="submit-reviews">
                                                <span data-hover="Gửi đánh giá">Gửi đánh giá</span>
                                                <i className="fal fa-arrow-right"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <div className="col-lg-4 col-md-8 col-sm-10 rmt-75">
                            <div className="blog-sidebar tour-sidebar">
                                <div className="widget widget-booking" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                                    <h5 className="widget-title fw-bold text-center">Đặt Tour</h5>
                                    <form>
                                        <div className="date mb-25">
                                            <b>Ngày bắt đầu</b>
                                            <input type="text" value="" name="startdate" disabled />
                                        </div>
                                        <hr />
                                        <div className="date mb-25">
                                            <b>Ngày kết thúc</b>
                                            <input type="text" value="" name="enddate" disabled />
                                        </div>
                                        <hr />
                                        <div className="time py-5">
                                            <b>Thời gian :</b>
                                            <p>$time</p>
                                            <input type="hidden" name="time" />
                                        </div>
                                        <hr className="mb-25" />
                                        <h6>Vé:</h6>
                                        <ul className="tickets clearfix">
                                            <li>
                                                Người lớn <span
                                                    className="price">$priceAdult VND
                                                </span>
                                            </li>
                                            <li>
                                                Trẻ em <span
                                                    className="price">priceChild VND
                                                </span>
                                            </li>
                                        </ul>
                                        <button type="submit" className="theme-btn style-two w-100 mt-15 mb-5">
                                            <span data-hover="Đặt ngay">Đặt ngay</span>
                                            <i className="fal fa-arrow-right"></i>
                                        </button>
                                    </form>
                                </div>

                                {/* @if (!empty($tourRecommendations))
                                    <div className="widget widget-tour" data-aos="fade-up" data-aos-duration="1500"
                                        data-aos-offset="50">
                                        <h6 className="widget-title">Tours tương tự</h6>
                                        @foreach ($tourRecommendations as $tour)
                                            <div className="destination-item tour-grid style-three bgc-lighter">
                                                <div className="image">
                                                    {{-- <span className="badge">10% Off</span> --}}
                                                    <img src="{{ asset('admin/assets/images/gallery-tours/' . $tour->images[0]) }}"
                                                        alt="Tour" style="max-height: 137px">
                                                </div>
                                                <div className="content">
                                                    <div className="destination-header">
                                                        <span className="location"><i className="fal fa-map-marker-alt"></i>
                                                            {{ $tour->destination }}</span>
                                                        <div className="ratting">
                                                            <i className="fas fa-star"></i>
                                                            <span>({{ $tour->rating }})</span>
                                                        </div>
                                                    </div>
                                                    <h6><a
                                                            href="{{ route('tour-detail', ['id' => $tour->tourId]) }}">{{ $tour->title }}</a>
                                                    </h6>
                                                </div>
                                            </div>
                                        @endforeach
                                    </div>
                                @endif */}

                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    )
}

export default memo(TourDetailPage);