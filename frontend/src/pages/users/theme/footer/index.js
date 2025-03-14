import { memo } from 'react';
import  { locationIcon, envelopeIcon, ministryOfIndustryAndTradeIcon, facebookIcon, instagramIcon, zaloIcon, logo, phoneIcon } from 'assets/users';
import './style.scss';

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
                <p><a href="#">Tuyển dụng</a></p>
                <p><a href="#">Chính sách bảo mật</a></p>
                <p><a href="#">Điều khoản sử dụng</a></p>
                <p><a href="#">Liên hệ hợp tác</a></p>
                <p><a href="#">Câu hỏi thường gặp</a></p>
            </div>

            <div className="contact">
                <h4>Liên hệ hỗ trợ</h4>
                <p><a href="#"><img src={facebookIcon} alt="Facebook" />Facebook</a></p>
                <p><a href="#"><img src={instagramIcon} alt="Instagram" />Instagram</a></p>
                <p><a href="#"><img src={zaloIcon} alt="Zalo" />Zalo</a></p>
            </div>

            <div className="end">
                <hr />
                <span>© 2025 Sao Việt</span>
            </div>
        </div>
    );
};

export default memo(Footer);