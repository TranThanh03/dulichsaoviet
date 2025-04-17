import React, { useState } from 'react';
import { FaMapMarkerAlt, FaCalendarAlt, FaSearch } from 'react-icons/fa';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import './searchForm.scss';

const SearchForm = () => {
    const [startDate, setStartDate] = useState(null);
    const [endDate, setEndDate] = useState(null);

    return (
        <section className="hero-area pt-5 pb-3" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1500" data-aos-offset="50">
            <form id="search_form">
                <div className="container search-form-container">
                    <div className="row align-items-center justify-content-center g-3">
                        <div className="col-md-3">
                            <div className="input-group">
                                <span className="input-group-text bg-white border-end-0">
                                    <FaMapMarkerAlt />
                                </span>
                                
                                <select name="destination" id="destination" className="form-select border-start-0">
                                    <option value="">Chọn điểm đến</option>
                                    <option value="dn">Đà Nẵng</option>
                                    <option value="cd">Côn Đảo</option>
                                    <option value="hn">Hà Nội</option>
                                    <option value="hcm">TP. Hồ Chí Minh</option>
                                    <option value="hl">Hạ Long</option>
                                    <option value="nb">Ninh Bình</option>
                                    <option value="pq">Phú Quốc</option>
                                    <option value="dl">Đà Lạt</option>
                                    <option value="qt">Quảng Trị</option>
                                    <option value="kh">Khánh Hòa (Nha Trang)</option>
                                    <option value="ct">Cần Thơ</option>
                                    <option value="vt">Vũng Tàu</option>
                                    <option value="qn">Quảng Ninh</option>
                                    <option value="la">Lào Cai (Sa Pa)</option>
                                    <option value="bd">Bình Định (Quy Nhơn)</option>
                                </select>
                            </div>
                        </div>

                        <div className="col-md-3 ms-1">
                            <div className="input-group">
                                <span className="input-group-text bg-white border-end-0">
                                    <FaCalendarAlt />
                                </span>
                                <DatePicker
                                    selected={startDate}
                                    onChange={(date) => setStartDate(date)}
                                    className="form-control border-start-0"
                                    placeholderText="Chọn ngày đi"
                                    dateFormat="dd/MM/yyyy"
                                />
                            </div>
                        </div>

                        <div className="col-md-3 me-2">
                            <div className="input-group">
                                <span className="input-group-text bg-white border-end-0">
                                    <FaCalendarAlt />
                                </span>
                                <DatePicker
                                    selected={endDate}
                                    onChange={(date) => setEndDate(date)}
                                    className="form-control border-start-0"
                                    placeholderText="Chọn ngày về"
                                    dateFormat="dd/MM/yyyy"
                                />
                            </div>
                        </div>

                        <div className="col-md-1">
                            <button type="submit" className="btn btn-primary w-100">
                                <FaSearch />
                            </button>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    );
};

export default SearchForm;