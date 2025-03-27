import { memo } from 'react';
import { hotel1 } from 'assets';
import './style.scss';

const HotelDetailPage = () => {
    return (
        <div className='hotel-detail-page'>
            <div className="hotel-details">
                <h1>Chi tiết khách sạn</h1>
                <div className="hotel-info">
                    <img src={hotel1} alt="Ảnh khách sạn" className="hotel-image" />
                    <div className="hotel-text">
                        <p><strong>Mã khách sạn:</strong> <span id="hotel-id">KS001</span></p>
                        <h2 id="hotel-name">Khách sạn Sao Việt Luxury</h2>
                        <p><strong>Địa chỉ:</strong> <span id="hotel-address">123 Đường ABC, Quận XYZ, Hà Nội</span></p>
                        <div className="hotel-description">
                            <h3>Mô tả:</h3>
                            <p id="hotel-description">
                                Khách sạn Sao Việt Luxury là một khách sạn 5 sao sang trọng tọa lạc tại trung tâm thành phố Hà Nội. 
                                Với 200 phòng nghỉ được thiết kế tinh tế, nhà hàng đẳng cấp, hồ bơi trên tầng thượng và trung tâm 
                                spa, chúng tôi cam kết mang đến cho quý khách trải nghiệm lưu trú tuyệt vời nhất. Khách sạn cách 
                                các điểm tham quan nổi tiếng chỉ vài phút đi bộ, thuận tiện cho cả khách du lịch và khách công tác.
                            </p>
                        </div>
                    </div>
                </div>
                <div className="hotel-amenities">
                    <h3>Tiện nghi:</h3>
                    <ul>
                        <li>Wi-Fi miễn phí</li>
                        <li>Hồ bơi trên tầng thượng</li>
                        <li>Phòng tập gym</li>
                        <li>Nhà hàng và quầy bar</li>
                        <li>Dịch vụ đưa đón sân bay</li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default memo(HotelDetailPage);