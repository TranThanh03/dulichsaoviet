import { memo } from 'react';
import "./index.scss";

const Banner = ({ title, image }) => {
    return (
        <section class="banner-custom page-banner-area mt-1 pt-50 pb-35 rel z-1 bgs-cover" style={{ backgroundImage: `url(${image})` }}>
            <div class="container">
                <div class="banner-inner text-white">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb justify-content-center mb-20" data-aos="fade-right" data-aos-delay="200"
                            data-aos-duration="1500" data-aos-offset="50">
                            <li class="breadcrumb-item">Trang chủ</li>
                            <li class="breadcrumb-item active">{title}</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </section>
    );
};

export default memo(Banner);