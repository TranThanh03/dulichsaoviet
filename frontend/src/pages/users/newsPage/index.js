import { memo } from 'react';
import { Link } from 'react-router-dom';
import { featuredTravel, travelNews1, travelNews2, travelNews3 } from 'assets/users';
import './detail.scss';

const NewsPage = () => {
    return (
        <div className='news-page'>
            <h1>Tin tức du lịch</h1>
            
            <section className="featured-news">
                <h2>Tin nổi bật</h2>
                <article className="featured-article">
                    <img src={featuredTravel} alt="Ảnh tin tức nổi bật" />
                    <h3>Khám phá 5 bãi biển đẹp nhất Việt Nam năm 2024</h3>
                    <p>Từ Phú Quốc đến Nha Trang, hãy cùng chúng tôi khám phá những bãi biển tuyệt đẹp và còn nguyên sơ của Việt Nam...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
            </section>

            <section className="news-list">
                <h2>Tin tức mới nhất</h2>
                <article className="news-item">
                    <img src={travelNews1} alt="Ảnh tin tức 1" />
                    <h3>Hướng dẫn du lịch Sapa mùa lúa chín</h3>
                    <p>Khám phá vẻ đẹp của những thửa ruộng bậc thang vàng óng ở Sapa vào mùa thu...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
                <article className="news-item">
                    <img src={travelNews2} alt="Ảnh tin tức 2" />
                    <h3>Top 10 nhà hàng đáng thử ở Hội An</h3>
                    <p>Khám phá ẩm thực đặc sắc của phố cổ Hội An qua danh sách 10 nhà hàng tuyệt vời nhất...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
                <article className="news-item">
                    <img src={travelNews3} alt="Ảnh tin tức 3" />
                    <h3>Cập nhật: Quy định mới về visa du lịch Việt Nam</h3>
                    <p>Những thay đổi quan trọng trong chính sách visa du lịch Việt Nam năm 2024...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
                <article className="news-item">
                    <img src={travelNews1} alt="Ảnh tin tức 1" />
                    <h3>Hướng dẫn du lịch Sapa mùa lúa chín</h3>
                    <p>Khám phá vẻ đẹp của những thửa ruộng bậc thang vàng óng ở Sapa vào mùa thu...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
                <article className="news-item">
                    <img src={travelNews2} alt="Ảnh tin tức 2" />
                    <h3>Top 10 nhà hàng đáng thử ở Hội An</h3>
                    <p>Khám phá ẩm thực đặc sắc của phố cổ Hội An qua danh sách 10 nhà hàng tuyệt vời nhất...</p>
                    <Link to="/news/detail">Đọc thêm</Link>
                </article>
            </section>

            <section className="news-categories">
                <h2>Chuyên mục</h2>
                <ul>
                    <li><Link to="#">Điểm đến hot</Link></li>
                    <li><Link to="#">Ẩm thực du lịch</Link></li>
                    <li><Link to="#">Mẹo vặt cho du khách</Link></li>
                    <li><Link to="#">Văn hóa & Lễ hội</Link></li>
                    <li><Link to="#">Khuyến mãi tour</Link></li>
                    <li><Link to="#">Trải nghiệm du lịch</Link></li>
                </ul>
            </section>

            <section className="newsletter">
                <h2>Đăng ký nhận tin</h2>
                <p>Nhận những thông tin du lịch mới nhất và ưu đãi hấp dẫn!</p>
                    <input type="email" placeholder="Nhập email của bạn" />
                    <button type="button">Đăng ký</button>
            </section>
        </div>
    )
}

export default memo(NewsPage);