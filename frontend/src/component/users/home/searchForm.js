import { memo } from "react";
import { FaMapMarkerAlt, FaCalendarAlt, FaSearch } from 'react-icons/fa';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import "./SearchForm.scss";

const SearchForm = () => {
    return (
        <div className="search-form-custom container py-4 d-flex justify-content-center rel z-2">
            <div className="bg-white rounded-4 shadow p-4 w-100 sub-form" data-aos="move-up-zoom-out" data-aos-duration="1500" data-aos-offset="50">
                <form id="search_form">
                    <div className="row g-4">
                        <div className="col-12 col-md-6 col-lg-3 div-custom" >
                            <label className="form-label fw-semibold">
                                <FaMapMarkerAlt className="me-1"/>Điểm đến
                            </label>

                            <select className="form-select">
                                <option value="">Chọn điểm đến</option>
                                <option value="dn">Đà Nẵng</option>
                                <option value="kh">Khánh Hòa (Nha Trang)</option>
                                <option value="hn">Hà Nội</option>
                                <option value="hcm">TP. Hồ Chí Minh</option>
                                <option value="hl">Hạ Long</option>
                                <option value="nb">Ninh Bình</option>
                                <option value="pq">Phú Quốc</option>
                                <option value="dl">Đà Lạt</option>
                                <option value="qt">Quảng Trị</option>
                                <option value="ct">Cần Thơ</option>
                                <option value="vt">Vũng Tàu</option>
                                <option value="qn">Quảng Ninh</option>
                                <option value="la">Lào Cai (Sa Pa)</option>
                                <option value="cd">Côn Đảo</option>
                                <option value="bd">Bình Định (Quy Nhơn)</option>
                            </select>
                        </div>

                        <div className="col-12 col-md-6 col-lg-3 div-custom div-startdate">
                            <label className="form-label fw-semibold">
                                <FaCalendarAlt className="me-1"/>Ngày khởi hành
                            </label>

                            <DatePicker
                                // selected={startDate}
                                // onChange={(date) => setStartDate(date)}
                                className="datetimepicker-custom"
                                placeholderText="Chọn ngày đi"
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>

                        <div className="col-12 col-md-6 col-lg-3 div-custom">
                            <label className="form-label fw-semibold">
                                <FaCalendarAlt className="me-1"/>Ngày kết thúc
                            </label>

                            <DatePicker
                                // selected={endDate}
                                // onChange={(date) => setEndDate(date)}
                                className="datetimepicker-custom"
                                placeholderText="Chọn ngày về"
                                dateFormat="dd/MM/yyyy"
                            />
                        </div>

                        <div className="col-12 col-md-6 col-lg-3 d-flex align-items-center justify-content-center">
                            <button type="submit" className="btn w-100 rounded-pill fw-bold btn-custom">
                                <FaSearch className="me-2" />Tìm kiếm
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
};

export default memo(SearchForm);
