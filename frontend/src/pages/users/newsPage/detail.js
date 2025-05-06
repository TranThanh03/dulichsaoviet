import { memo } from 'react';
import { Link } from 'react-router-dom';
import { featuredTravel, contentImg1, contentImg2, popularPost1, popularPost2, popularPost3 } from 'assets/user';
import './style.scss';

const NewsDetailPage = () => {
    return (
        <div className='news-detail-page'>
            <article className="news-detail">
                <h1>Khám phá 5 bãi biển đẹp nhất Việt Nam năm 2024</h1>
                
                <div className="article-meta">
                    <span className="author">Tác giả: Nguyễn Văn A</span>
                    <span className="date">Ngày đăng: 15/01/2025</span>
                    <span className="category">Chuyên mục: Điểm đến hot</span>
                </div>
                
                <img src={ featuredTravel } alt="Ảnh bãi biển đẹp nhất Việt Nam" className="featured-image" /> 
                
                <div className="article-content">
                    <p>Từ Phú Quốc đến Nha Trang, hãy cùng chúng tôi khám phá những bãi biển tuyệt đẹp và còn nguyên sơ của Việt Nam...</p>
                    
                    <h2>1. Phú Quốc (Kiên Giang)</h2>
                    <img src={ contentImg1 } alt="Phú Quốc (Kiên Giang)" className="article-image" />
                    <p>Phú Quốc – một trong những địa phương sở hữu nhiều bãi biển đẹp nhất Việt Nam với khung cảnh thiên nhiên hoang sơ hùng vĩ. Khi đến đây,
                            du khách không chỉ được chiêm ngưỡng những bờ biển đẹp hút hồn mà còn xao xuyến bởi sự mến khách của người dân địa phương.
                            Mùa hè này chính là thời điểm thích hợp nhất để du khách có thể trải nghiệm được gần như tất cả các dịch vụ du lịch khi đến
                            Phú Quốc như tắm biển, mò san hô, cắm trại…
                        </p>
                    
                    <h2>2. "Mũi Né (Bình Thuận)</h2>
                    <img src={ contentImg2 } alt="Mũi Né (Bình Thuận)" className="article-image" />
                    <p>Mũi Né như được thiên nhiên ưu đãi ban tặng cho dải bờ biển dài gần 10km.
                            Nơi này nổi tiếng với cồn cát trắng mịn, nước biển xanh như ngọc và những khu nghỉ dưỡng sang trọng.
                            Mũi Né là địa điểm du lịch nổi tiếng với những hoạt động ngoài trời như bơi lội, lướt ván diều, đặc biệt là trượt cát. Nếu du khách là tín đồ thích sống ảo,
                            thích check-in thì Phan Thiết là địa điểm không thể bỏ lỡ trong mùa hè này vì nơi đây còn nhiều khu giữ được nét hoang sơ, hùng vĩ.
                        </p>
                    
                    <h2>Lời kết</h2>
                    <p>Việt Nam tự hào có nhiều bãi biển tuyệt đẹp, mỗi nơi đều mang một vẻ đẹp riêng...</p>
                </div>
                
                <div className="share-buttons">
                    <h3>Chia sẻ bài viết</h3>
                    <Link to="#" className="share-button facebook">Facebook</Link>
                    <Link to="#" className="share-button twitter">Twitter</Link>
                    <Link to="#" className="share-button linkedin">LinkedIn</Link>
                </div>
                
                <div className="related-articles">
                    <h3>Bài viết liên quan</h3>
                    <ul>
                        <li><Link to="#">Top 10 món ăn đường phố phải thử khi du lịch Việt Nam</Link></li>
                        <li><Link to="#">Hướng dẫn chi tiết: Cách đặt phòng khách sạn giá rẻ ở Việt Nam</Link></li>
                        <li><Link to="#">5 hoạt động thú vị không thể bỏ qua khi đến Phú Quốc</Link></li>
                    </ul>
                </div>
            </article>
            
            <aside className="sidebar">
                <section className="popular-posts">
                    <h3>Bài viết phổ biến</h3>
                    <ul>
                        <li>
                            <Link to="#">
                                <img src={ popularPost1 } alt="Ảnh bài viết phổ biến 1" />
                                <div className="popular-post-info">
                                    <h4>10 món ăn đường phố must-try ở Hà Nội</h4>
                                    <span className="post-date">15/01/2025</span>
                                </div>
                            </Link>
                        </li>
                        <li>
                            <Link to="#">
                                <img src={ popularPost2 } alt="Ảnh bài viết phổ biến 2" />
                                <div className="popular-post-info">
                                    <h4>Hướng dẫn du lịch Đà Lạt tiết kiệm</h4>
                                    <span className="post-date">10/01/2025</span>
                                </div>
                            </Link>
                        </li>
                        <li>
                            <Link to="#">
                                <img src={ popularPost3 } alt="Ảnh bài viết phổ biến 3" />
                                <div className="popular-post-info">
                                    <h4>5 resort sang trọng nhất ở Phú Quốc</h4>
                                    <span className="post-date">05/01/2025</span>
                                </div>
                            </Link>
                        </li>
                    </ul>
                </section>
            </aside>
        </div>
    )
}

export default memo(NewsDetailPage);