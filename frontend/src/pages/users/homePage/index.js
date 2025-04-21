import React, { useState, useEffect, memo } from 'react';
import './style.scss';
import { hAuthor1, hAuthor2, hAuthor3, hBoxTwo, hCta1, hCta2, hCta3, hShape1, hShape2, hShape3, hShape4, hShape5, hShape6, hShape7, slide1, slide2, slide3, slide4, slide5, tourCta } from 'assets';
import SlideShow from 'component/users/home/SlideShow';
import SearchForm from 'component/users/home/SearchForm';
import AnimatedCounter from 'component/counter';
import { Link } from 'react-router-dom';

const slides = [slide1, slide2, slide3, slide4, slide5];

const HomePage = () => {
    return (
        <div className="home-page">
            <SlideShow slides={slides} interval={3000} />

            <SearchForm />

            <div className="space-custom bgc-black"></div>

            <section class="destinations-area bgc-black pt-60 pb-70">
                <div class="container-fluid">
                    <div class="row justify-content-center">
                        <div class="col-lg-12">
                            <div class="section-title text-white text-center counter-text-wrap mb-70" data-aos="fade-up"
                                data-aos-duration="1500" data-aos-offset="50">
                                <h2>Khám phá kho báu việt nam cùng Sao Việt</h2>
                                <p>Website<AnimatedCounter end={345} duration={2} className="plus mx-2" />phổ biến nhất mà bạn sẽ nhớ</p>
                            </div>
                        </div>
                    </div>
                    <div class="row justify-content-center">
                        {/* @foreach ($tours as $tour)
                            <div class="col-xxl-3 col-xl-4 col-md-6" style="margin-bottom: 30px">
                                <div class="destination-item block_tours" data-aos="fade-up" data-aos-duration="1500"
                                    data-aos-offset="50">
                                    <div class="image">
                                        <div class="ratting"><i class="fas fa-star"></i> {{ number_format($tour->rating, 1) }}</div>
                                        <a href="#" class="heart"><i class="fas fa-heart"></i></a>
                                        <img src="{{ asset('admin/assets/images/gallery-tours/' . $tour->images[0] . '') }}"
                                            alt="Destination">
                                    </div>
                                    <div class="content">
                                        <span class="location"><i class="fal fa-map-marker-alt"></i>{{ $tour->destination }}</span>
                                        <h5><a href="{{ route('tour-detail', ['id' => $tour->tourId]) }}">{{ $tour->title }}</a>
                                        </h5>
                                        <span class="time">{{ $tour->time }}</span>
                                    </div>
                                    <div class="destination-footer">
                                        <span class="price"><span>{{ number_format($tour->priceAdult, 0, ',', '.') }}</span> VND /
                                            người</span>
                                        <a href="{{ route('tour-detail', ['id' => $tour->tourId]) }}" class="read-more">Đặt ngay <i
                                                class="fal fa-angle-right"></i></a>
                                    </div>
                                </div>
                            </div>
                        @endforeach */}
                    </div>
                </div>
            </section>

            <section class="about-us-area py-100 rpb-90 rel z-1">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-5 col-lg-6">
                            <div class="about-us-content rmb-55" data-aos="fade-left" data-aos-duration="1500" data-aos-offset="50">
                                <div class="section-title mb-25">
                                    <h2>Du lịch với sự tự tin lý do hàng đầu để chọn công ty chúng tôi</h2>
                                </div>
                                <p className="text-indent">Chúng tôi sẽ nỗ lực hết mình để biến giấc mơ du lịch của bạn thành hiện thực những viên ngọc ẩn
                                    và những điểm tham quan không thể bỏ qua</p>
                                <div class="divider counter-text-wrap mt-45 mb-55">
                                    <span>
                                        Chúng tôi có <span><AnimatedCounter end={5} className="plus" /> năm</span> kinh nghiệm
                                    </span>
                                </div>
                                <div class="row">
                                    <div class="col-6">
                                        <div class="counter-item counter-text-wrap">
                                            <AnimatedCounter end={200} className="plus" />
                                            <span class="counter-title">Điểm đến phổ biến</span>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="counter-item counter-text-wrap">
                                            <AnimatedCounter end={5} duration={3.5} className="k-plus" />
                                            <span class="counter-title">Khách hàng hài lòng</span>
                                        </div>
                                    </div>
                                </div>
                                <Link to="/destinations/index" class="theme-btn mt-10 style-two">
                                    <span data-hover="Khám phá Điểm đến">Khám phá điểm đến</span>
                                    <i class="fal fa-arrow-right"></i>
                                </Link>
                            </div>
                        </div>
                        <div class="col-xl-7 col-lg-6" data-aos="fade-right" data-aos-duration="1500" data-aos-offset="50">
                            <div class="about-us-image">
                                <div class="shape">
                                    <img src={hShape1} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape2} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape3} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape4} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape5} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape6} alt="Shape" />
                                </div>
                                <div class="shape">
                                    <img src={hShape7} alt="Shape" />
                                </div>
                                <img src={tourCta} alt="About" />
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="popular-destinations-area rel z-1">
                <div class="container-fluid">
                    <div class="popular-destinations-wrap br-20 bgc-lighter pt-100 pb-70">
                        <div class="row justify-content-center">
                            <div class="col-lg-12">
                                <div class="section-title text-center counter-text-wrap mb-70" data-aos="fade-up"
                                    data-aos-duration="1500" data-aos-offset="50">
                                    <h2>Khám phá các điểm đến phổ biến</h2>
                                    <p>Website<AnimatedCounter end={567} duration={2} className="plus mx-2" />nghiệm phổ biến nhất</p>
                                </div>
                            </div>
                        </div>
                        <div class="container">
                            <div class="row justify-content-center">
                                {/* @php $count = 0; @endphp
                                @foreach ($toursPopular as $tour)
                                    @if ($count == 2 || $count == 3)
                                        <!-- Cột thứ 3 và thứ 4 sẽ là col-md-6 -->
                                        <div class="col-md-6 item ">
                                        @else
                                            <!-- Các cột còn lại sẽ là col-xl-3 col-md-6 -->
                                            <div class="col-xl-3 col-md-6 item ">
                                    @endif

                                    <div class="destination-item style-two" data-aos-duration="1500" data-aos-offset="50">
                                        <div class="image" style="max-height: 250px">
                                            <a href="#" class="heart"><i class="fas fa-heart"></i></a>
                                            <img src="{{ asset('admin/assets/images/gallery-tours/' . $tour->images[0]) }}"
                                                alt="Destination">
                                        </div>
                                        <div class="content">
                                            <h6 class="tour-title"><a
                                                    href="{{ route('tour-detail', ['id' => $tour->tourId]) }}">{{ $tour->title }}</a>
                                            </h6>
                                            <span class="time">{{ $tour->time }}</span>
                                            <a href="{{ route('tour-detail', ['id' => $tour->tourId]) }}" class="more"><i
                                                    class="fas fa-chevron-right"></i></a>
                                        </div>
                                    </div>

                                </div> <!-- Đóng div col-md-6 hoặc col-xl-3 col-md-6 -->

                                @php $count++; @endphp
                                @endforeach*/}
                            </div>
                        </div>
                    </div>
                </div>
            </section>


            <section class="features-area pt-100 pb-45 rel z-1">
                <div class="container">
                    <div class="row align-items-center">
                        <div class="col-xl-6">
                            <div class="features-content-part mb-55" data-aos="fade-left" data-aos-duration="1500" data-aos-offset="50">
                                <div class="section-title mb-60">
                                    <h2>Trải nghiệm du lịch tuyệt đỉnh mang đến sự khác biệt cho công ty chúng tôi</h2>
                                </div>
                                <div class="features-customer-box">
                                    <div class="image">
                                        <img src={hBoxTwo} alt="Features" />
                                    </div>
                                    <div class="content">
                                        <div class="feature-authors mb-15">
                                            <img src={hAuthor1} alt="Author" />
                                            <img src={hAuthor2} alt="Author" />
                                            <img src={hAuthor3} alt="Author" />
                                            <span>4k+</span>
                                        </div>

                                        <h6>5K+ Khách hàng hài lòng</h6>
                                        <div class="divider style-two counter-text-wrap my-25">
                                            <span><AnimatedCounter end={5} className="plus" /> năm</span>
                                        </div>
                                        <p className="text-indent">Chúng tôi tự hào cung cấp các hành trình được cá nhân hóa.</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-xl-6" data-aos="fade-right" data-aos-duration="1500" data-aos-offset="50">
                            <div class="row pb-25">
                                <div class="col-md-6">
                                    <div class="feature-item">
                                        <div class="icon"><i class="flaticon-tent"></i></div>
                                        <div class="content">
                                            <h5><Link to="#">Chinh phục cảnh quan Việt Nam</Link></h5>
                                            <p className="text-indent">Khám phá những cảnh đẹp hùng vĩ và tuyệt vời của đất nước Việt Nam.</p>
                                        </div>
                                    </div>
                                    <div class="feature-item">
                                        <div class="icon"><i class="flaticon-tent"></i></div>
                                        <div class="content">
                                            <h5><Link to="#">Trải nghiệm đặc sắc Việt Nam</Link></h5>
                                            <p className="text-indent">Trải nghiệm những hoạt động và lễ hội đặc trưng của văn hóa Việt.</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="feature-item mt-20">
                                        <div class="icon"><i class="flaticon-tent"></i></div>
                                        <div class="content">
                                            <h5><Link to="#">Khám phá di sản Việt Nam</Link></h5>
                                            <p className="text-indent">Khám phá các di sản thế giới và những kỳ quan thiên nhiên nổi tiếng.</p>
                                        </div>
                                    </div>
                                    <div class="feature-item">
                                        <div class="icon"><i class="flaticon-tent"></i></div>
                                        <div class="content">
                                            <h5><Link to="#">Vẻ đẹp thiên nhiên Việt Nam</Link></h5>
                                            <p className="text-indent">Chinh phục vẻ đẹp tự nhiên hoang sơ và kỳ vĩ của Việt Nam.</p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="cta-area pt-100 rel z-1">
                <div class="container-fluid">
                    <div class="row">
                        <div class="col-xl-4 col-md-6" data-aos="zoom-in-down" data-aos-duration="1500" data-aos-offset="50">
                            <div class="cta-item" style={{backgroundImage: `url(${hCta1})`}}>
                                <span class="category">Khám phá vẻ đẹp văn hóa Việt</span>
                                <h2>Tìm hiểu những giá trị văn hóa độc đáo của các vùng miền Việt Nam.</h2>
                                <Link to="/tour/index" class="theme-btn style-two bgc-secondary">
                                    <span data-hover="Khám phá">Khám phá</span>
                                    <i class="fal fa-arrow-right"></i>
                                </Link>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6" data-aos="zoom-in-down" data-aos-delay="50" data-aos-duration="1500" data-aos-offset="50">
                            <div class="cta-item" style={{backgroundImage: `url(${hCta2})`}}>
                                <span class="category">Bãi biển</span>
                                <h2>Bãi trong xanh dạt dào ở Việt Nam</h2>
                                <Link to="/tour/index" class="theme-btn style-two bgc-secondary">
                                    <span data-hover="Khám phá">Khám phá</span>
                                    <i class="fal fa-arrow-right"></i>
                                </Link>
                            </div>
                        </div>
                        <div class="col-xl-4 col-md-6" data-aos="zoom-in-down" data-aos-delay="100" data-aos-duration="1500" data-aos-offset="50">
                            <div class="cta-item" style={{backgroundImage: `url(${hCta3})`}}>
                                <span class="category">Thác nước</span>
                                <h2>Thác nước lớn nhất Việt Nam</h2>
                                <Link to="/tour/index" class="theme-btn style-two bgc-secondary">
                                    <span data-hover="Khám phá">Khám phá</span>
                                    <i class="fal fa-arrow-right"></i>
                                </Link>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default memo(HomePage);