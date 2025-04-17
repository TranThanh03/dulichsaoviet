import { memo, useState, useEffect } from 'react';
import { TourApi } from 'services';
import TourCard from 'component/users/tour/TourCard';
import TourSidebar from 'component/users/tour/TourSidebar';
import Pagination from 'component/pagination';

const TourPage = () => {
    const [tours, setTours] = useState([
        {
        image: "https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg",
        title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
        rating: 4.2,
        duration: "4 ngày 3 đêm",
        reviews: 46,
        price: 2790000,
        },
        {
        image: "https://res.cloudinary.com/doie0qiiq/image/upload/v1740853584/saoviet/awvf8iefn2wvldkbhuxv.webp",
        title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
        rating: 4,
        duration: "2 ngày 1 đêm",
        reviews: 43,
        price: 3490000
        },
        {
        image: "https://res.cloudinary.com/doie0qiiq/image/upload/v1740854102/saoviet/jwel4yzvsnkhpodz1pab.jpg",
        title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
        rating: 5,
        duration: "4 ngày 3 đêm",
        reviews: 45,
        price: 3990000
        },
        {
        image: "https://res.cloudinary.com/doie0qiiq/image/upload/v1740853584/saoviet/awvf8iefn2wvldkbhuxv.webp",
        title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
        rating: 5,
        duration: "4 ngày 3 đêm",
        reviews: 46,
        price: 2790000
        },
        {
        image: "https://via.placeholder.com/300x200?text=Da+Nang",
        title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
        rating: 5,
        duration: "2 ngày 1 đêm",
        reviews: 43,
        price: 3490000
        },
        {
        image: "https://via.placeholder.com/300x200?text=Hue",
        title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
        rating: 5,
        duration: "4 ngày 3 đêm",
        reviews: 45,
        price: 3990000
        },
        {
        image: "https://via.placeholder.com/300x200?text=Phu+Quoc",
        title: "BIỂN ĐẢO (N3D) PHÚ QUỐC (Khởi hành mới ngày)",
        rating: 5,
        duration: "4 ngày 3 đêm",
        reviews: 46,
        price: 2790000
        },
        {
        image: "https://via.placeholder.com/300x200?text=Da+Nang",
        title: "ĐÀ NẴNG - CÙ LAO CHÀM - HỘI AN - BÀ NÀ (TẾT ÂM LỊCH 2025)",
        rating: 5,
        duration: "2 ngày 1 đêm",
        reviews: 43,
        price: 3490000
        },
        {
        image: "https://via.placeholder.com/300x200?text=Hue",
        title: "ĐÀ NẴNG - HỘI AN - BÀ NÀ - HUẾ",
        rating: 5,
        duration: "4 ngày 3 đêm",
        reviews: 45,
        price: 3990000
        }
    ]);
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages] = useState(1);

    return (
        <section className="tour-page">
            <div className="container mx-auto px-4">
                <div className="flex flex-wrap -mx-4 pt-5">
                
                    <TourSidebar />

                    <div className="w-full lg:w-3/4 px-4">
                        <div className="flex justify-between items-center mb-6">
                            <div className="text-sm fw-bold">Tìm thấy {tours.length} tour</div>
                            <div className="flex items-center space-x-4">
                                <span className="text-sm fw-bold">Sắp xếp theo</span>
                                <select className="border rounded px-2 py-1 text-sm">
                                <option value="new">Mới nhất</option>
                                <option value="old">Cũ nhất</option>
                                <option value="high-to-low">Cao đến thấp</option>
                                <option value="low-to-high">Thấp đến cao</option>
                                </select>
                            </div>
                        </div>

                        <div
                            className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6 mb-3"
                            data-aos="fade-up"
                            data-aos-delay="50"
                            data-aos-duration="1500"
                            data-aos-offset="50"
                        >
                            {tours.map((tour, index) => (
                                <TourCard key={index} tour={tour} />
                            ))}
                        </div>

                        <Pagination
                            currentPage={currentPage}
                            totalPages={totalPages}
                            onPageChange={setCurrentPage}
                        />
                    </div>
                </div>
            </div>
        </section>
    );
};

export default memo(TourPage);