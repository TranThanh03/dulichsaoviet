import { memo } from 'react';
import { Link } from 'react-router-dom';
import { pShape3, tourCta } from 'assets';
import PriceFilter from './PriceFilter';
import "./TourSidebar.scss";

const TourSidebar = () => {
    return (
        <div className="tour-sidebar-custom col-lg-3 col-md-6 col-sm-10 rmb-75">
            <div className="shop-sidebar">
                <div className="div_filter_clear pb-2">
                    <button className="clear_filter" name="btn_clear">
                        <a href="/tour/index">Clear</a>
                    </button>
                </div>

                <PriceFilter maxPrice={20000000} onChangeRange={(range) => {console.log(range) }}/>

                <div className="widget widget-activity" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="widget-title">Điểm đến</h6>
                    <ul className="radio-filter">
                        <li>
                            <input className="form-check-input" type="radio" name="domain" id="b" value="b" />
                            <label htmlFor="b">Miền Bắc <span>7</span></label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="domain" id="t" value="t" />
                            <label htmlFor="t">Miền Trung <span>8</span></label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="domain" id="n" value="n" />
                            <label htmlFor="n">Miền Nam <span>9</span></label>
                        </li>
                    </ul>
                </div>

                <div className="widget widget-reviews" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="widget-title">Đánh giá</h6>
                    <ul className="radio-filter">
                        <li>
                            <input className="form-check-input" type="radio" name="filter_star" id="5star" value="5" />
                            <label htmlFor="5star">
                                <span className="ratting">
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                </span>
                            </label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="filter_star" id="4star" value="4" />
                            <label htmlFor="4star">
                                <span className="ratting">
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star white"></i>
                                </span>
                            </label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="filter_star" id="3star" value="3" />
                            <label htmlFor="3star">
                                <span className="ratting">
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                </span>
                            </label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="filter_star" id="2star" value="2" />
                            <label htmlFor="2star">
                                <span className="ratting">
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                </span>
                            </label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="filter_star" id="1star" value="1" />
                            <label htmlFor="1star">
                                <span className="ratting">
                                    <i className="fas fa-star"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                    <i className="fas fa-star white"></i>
                                </span>
                            </label>
                        </li>
                    </ul>
                </div>

                <div className="widget widget-duration" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="widget-title">Thời gian</h6>
                    <ul className="radio-filter">
                        <li>
                            <input className="form-check-input" type="radio" name="duration" id="3ngay2dem" value="2" />
                            <label htmlFor="3ngay2dem">2 ngày 1 đêm</label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="duration" id="4ngay3dem" value="3" />
                            <label htmlFor="4ngay3dem">3 ngày 2 đêm</label>
                        </li>
                        <li>
                            <input className="form-check-input" type="radio" name="duration" id="5ngay4dem" value="4" />
                            <label htmlFor="5ngay4dem">4 ngày 3 đêm</label>
                        </li>
                    </ul>
                </div>
            </div>

            <div className="widget widget-cta mt-30" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                <div className="content text-white">
                    <span className="h6">Khám phá Việt Nam</span>
                    <h3>Điểm đến du lịch tốt nhất</h3>
                    <Link to="/destinations/index" className="theme-btn style-two bgc-secondary">
                        <span data-hover="Khám phá ngay">Khám phá ngay</span>
                        <i className="fal fa-arrow-right"></i>
                    </Link>
                </div>

                <div className="image">
                    <img src={tourCta} alt="CTA" />
                </div>

                <div className="cta-shape">
                    <img src={pShape3} alt="Shape" />
                </div>
            </div>
        </div>
    );
};

export default memo(TourSidebar);

