import { memo, useState, useEffect } from 'react';
import { TourApi } from 'services';
import TourCard from 'component/users/tour/TourCard';
import TourSidebar from 'component/users/tour/TourSidebar';
import Pagination from 'component/pagination';
import { tourBanner } from 'assets';
import Banner from 'component/banner';
import "./index.scss";

const TourPage = () => {
    const [tours, setTours] = useState([
        {
            tourId: 1,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
            rating: 4.2,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 46,
            quantity: 20,
            destination: "Phú Quốc",
            price: 2790000,
            priceAdult: 2790000,
        },
        {
            tourId: 2,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740853584/saoviet/awvf8iefn2wvldkbhuxv.webp"],
            title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
            rating: 4,
            duration: "2 ngày 1 đêm",
            time: "2 ngày 1 đêm",
            reviews: 43,
            quantity: 30,
            destination: "Đà Nẵng",
            price: 3490000,
            priceAdult: 3490000,
        },
        {
            tourId: 3,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
            rating: 5,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 45,
            quantity: 25,
            destination: "Huế",
            price: 3990000,
            priceAdult: 3990000,
        },
        {
            tourId: 1,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
            rating: 4.2,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 46,
            quantity: 20,
            destination: "Phú Quốc",
            price: 2790000,
            priceAdult: 2790000,
        },
        {
            tourId: 2,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740853584/saoviet/awvf8iefn2wvldkbhuxv.webp"],
            title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
            rating: 4,
            duration: "2 ngày 1 đêm",
            time: "2 ngày 1 đêm",
            reviews: 43,
            quantity: 30,
            destination: "Đà Nẵng",
            price: 3490000,
            priceAdult: 3490000,
        },
        {
            tourId: 3,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
            rating: 5,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 45,
            quantity: 25,
            destination: "Huế",
            price: 3990000,
            priceAdult: 3990000,
        },
        {
            tourId: 1,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
            rating: 4.2,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 46,
            quantity: 20,
            destination: "Phú Quốc",
            price: 2790000,
            priceAdult: 2790000,
        },
        {
            tourId: 2,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740853584/saoviet/awvf8iefn2wvldkbhuxv.webp"],
            title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
            rating: 4,
            duration: "2 ngày 1 đêm",
            time: "2 ngày 1 đêm",
            reviews: 43,
            quantity: 30,
            destination: "Đà Nẵng",
            price: 3490000,
            priceAdult: 3490000,
        },
        {
            tourId: 3,
            images: ["https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg"],
            title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
            rating: 5,
            duration: "4 ngày 3 đêm",
            time: "4 ngày 3 đêm",
            reviews: 45,
            quantity: 25,
            destination: "Huế",
            price: 3990000,
            priceAdult: 3990000,
        },
    ]);

    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages] = useState(1);

    return (
        <div className="tour-page">
            <Banner title={"Tours"} image={tourBanner} />

            <section className="tour-grid-page py-30 rel z-1">
                <div className="container">
                    <div className="row">
                        <TourSidebar />

                        <div className="col-lg-9">
                            <div className="d-flex justify-content-between align-items-center mb-4">
                                <div className="fw-bold small">Tìm thấy {tours.length} tours</div>

                                <div className="d-flex align-items-center gap-3">
                                    <span className="fw-bold small">Sắp xếp theo</span>
                                    <select className="form-select form-select-sm w-auto">
                                    <option value="default">Mặc định</option>
                                    <option value="new">Mới nhất</option>
                                    <option value="old">Cũ nhất</option>
                                    <option value="high-to-low">Cao đến thấp</option>
                                    <option value="low-to-high">Thấp đến cao</option>
                                    </select>
                                </div>
                            </div>


                            <div className="tour-grid-wrap">
                                <div className="row" id="tours-container">
                                    {tours.length > 0 && (
                                        tours.map((item, index) => (
                                            <TourCard key={index} tour={item} />
                                        ))
                                    )}
                                </div>

                                <Pagination
                                        currentPage={currentPage}
                                        totalPages={totalPages}
                                        onPageChange={setCurrentPage}
                                    />
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    );
};

export default memo(TourPage);