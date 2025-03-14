import { memo } from 'react';
import { Link } from 'react-router-dom';
import { hotel1, hotel2, hotel3 } from 'assets/users';
import './detail.scss';

const HotelPage = () => {
    return (
        <div className='hotel-page'>
            <h1>Danh sách khách sạn</h1>
            
            <div className="hotel-list">
                <div className="hotel-item">
                    <img src={ hotel1 } alt="Khách sạn 1" />
                    <h3>Khách sạn Sao Việt</h3>
                    <p>Địa chỉ: 123 Đường ABC, Quận XYZ, Hà Nội</p>
                    <p>Giá từ: 1,000,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel2 } alt="Khách sạn 2" />
                    <h3>Khách sạn Mặt Trời</h3>
                    <p>Địa chỉ: 456 Đường DEF, Quận UVW, Hồ Chí Minh</p>
                    <p>Giá từ: 1,200,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel3 } alt="Khách sạn 3" />
                    <h3>Khách sạn Biển Xanh</h3>
                    <p>Địa chỉ: 789 Đường GHI, Quận JKL, Đà Nẵng</p>
                    <p>Giá từ: 800,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel1 } alt="Khách sạn 1" />
                    <h3>Khách sạn Sao Việt</h3>
                    <p>Địa chỉ: 123 Đường ABC, Quận XYZ, Hà Nội</p>
                    <p>Giá từ: 1,000,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel2 } alt="Khách sạn 2" />
                    <h3>Khách sạn Mặt Trời</h3>
                    <p>Địa chỉ: 456 Đường DEF, Quận UVW, Hồ Chí Minh</p>
                    <p>Giá từ: 1,200,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel3 } alt="Khách sạn 3" />
                    <h3>Khách sạn Biển Xanh</h3>
                    <p>Địa chỉ: 789 Đường GHI, Quận JKL, Đà Nẵng</p>
                    <p>Giá từ: 800,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel1 } alt="Khách sạn 1" />
                    <h3>Khách sạn Sao Việt</h3>
                    <p>Địa chỉ: 123 Đường ABC, Quận XYZ, Hà Nội</p>
                    <p>Giá từ: 1,000,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel2 } alt="Khách sạn 2" />
                    <h3>Khách sạn Mặt Trời</h3>
                    <p>Địa chỉ: 456 Đường DEF, Quận UVW, Hồ Chí Minh</p>
                    <p>Giá từ: 1,200,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
                <div className="hotel-item">
                    <img src={ hotel3 } alt="Khách sạn 3" />
                    <h3>Khách sạn Biển Xanh</h3>
                    <p>Địa chỉ: 789 Đường GHI, Quận JKL, Đà Nẵng</p>
                    <p>Giá từ: 800,000 VNĐ/đêm</p>
                    <Link to="/hotels/detail" className="btn-detail">Xem chi tiết</Link>
                </div>
            </div>

            <div className="pagination">
                <Link to="#" className="active">1</Link>
                <Link to="#">2</Link>
                <Link to="#">3</Link>
                <Link to="#">4</Link>
                <Link to="#">5</Link>
                <Link to="#">Tiếp &raquo;</Link>
            </div>
        </div>
    )
}

export default memo(HotelPage);