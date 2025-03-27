import { memo } from 'react';
import  { locationIcon, envelopeIcon, ministryOfIndustryAndTradeIcon, facebookIcon, instagramIcon, zaloIcon, logo, phoneIcon } from 'assets';
import './style.scss';
import { Link } from 'react-router-dom';

const Footer = () => {
    return (
        <div className='footer'>
            <div className="infor">
                <h4>Sao Việt - Vivu ba miền</h4>
                <div className='sub'>
                    <img src={locationIcon} alt="Địa chỉ" />
                    <p>1 Hoàng Công Chất, Phú Diễn, Bắc Từ Liêm, Hà Nội</p>
                </div>
                <div className='sub'>
                    <img src={phoneIcon} alt="Số điện thoại" />
                    <p>0399999999</p>
                </div>
                <div className='sub'>
                    <img src={envelopeIcon} alt="Email" />
                    <p>support@saoviet.com</p>
                </div>
                <div>
                    <img src={ministryOfIndustryAndTradeIcon} alt="Bộ Công Thương" />
                </div>
            </div>

            <div className="policy">
                <img src={logo} alt="Logo Sao Việt" />
                <span>Sao Việt</span>
                <p><Link to="#">Tuyển dụng</Link></p>
                <p><Link to="#">Chính sách bảo mật</Link></p>
                <p><Link to="#">Điều khoản sử dụng</Link></p>
                <p><Link to="#">Liên hệ hợp tác</Link></p>
                <p><Link to="#">Câu hỏi thường gặp</Link></p>
            </div>

            <div className="contact">
                <h4>Liên hệ hỗ trợ</h4>
                <p><Link to="#"><img src={facebookIcon} alt="Facebook" />Facebook</Link></p>
                <p><Link to="#"><img src={instagramIcon} alt="Instagram" />Instagram</Link></p>
                <p><Link to="#"><img src={zaloIcon} alt="Zalo" />Zalo</Link></p>
            </div>

            <div className="end">
                <hr />
                <span>© 2025 Sao Việt</span>
            </div>
        </div>
    );
};

export default memo(Footer);