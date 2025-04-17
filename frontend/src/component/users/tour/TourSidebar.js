import { memo, useState } from 'react';
import "./TourSidebar.scss";

const TourSidebar = () => {
    const [priceRange, setPriceRange] = useState([0, 20000000]);

    const handlePriceChange = (e) => {
        const value = parseInt(e.target.value);
        setPriceRange([0, value]);
        e.target.style.setProperty('--value', `${(value / 20000000) * 100}%`);
    };
    
    return (
        <div className="tour-sidebar mt-5 w-full lg:w-1/4 px-4 mb-12 lg:mb-0" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1500" data-aos-offset="50">
            <div className="bg-white p-6 rounded-lg shadow-custom">
                <div className="flex justify-between items-center mb-6 justify-content-end">
                    <button className="bg-red-500 text-white px-4 py-1 rounded">Clear</button>
                </div>
                
                <div className="mb-6 widget" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="text-lg font-semibold">Lọc theo giá</h6>
                    <input
                        type="range"
                        min="0"
                        max="20000000"
                        value={priceRange[1]}
                        onChange={handlePriceChange}
                        className="w-full custom-range"
                        style={{ '--value': '100%' }}
                    />
                    <p className="mt-2 text-sm text-gray-600">
                        Giá {priceRange[0].toLocaleString()} - {priceRange[1].toLocaleString()} VND
                    </p>
                </div>

                <div className="mb-6 widget" data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="text-lg font-semibold mb-4">Điểm đến</h6>
                    <ul className="space-y-2">
                        <li className="flex items-center">
                            <input type="radio" name="domain" id="id_mien_bac" className="mr-2" />
                            <label htmlFor="id_mien_bac" className="text-sm">Miền Bắc <span className="text-gray-500">(7)</span></label>
                        </li>
                        <li className="flex items-center">
                            <input type="radio" name="domain" id="id_mien_trung" className="mr-2" />
                            <label htmlFor="id_mien_trung" className="text-sm">Miền Trung <span className="text-gray-500">(8)</span></label>
                        </li>
                        <li className="flex items-center">
                            <input type="radio" name="domain" id="id_mien_nam" className="mr-2" />
                            <label htmlFor="id_mien_nam" className="text-sm">Miền Nam <span className="text-gray-500">(8)</span></label>
                        </li>
                    </ul>
                </div>

                <div data-aos="fade-up" data-aos-duration="1500" data-aos-offset="50">
                    <h6 className="text-lg font-semibold mb-4">Đánh giá</h6>
                    <ul className="space-y-2">
                        {[5, 4, 3, 2, 1].map((star) => (
                            <li key={star} className="flex items-center">
                                <input type="radio" name="filter_star" id={`${star}star`} className="mr-2" />
                                <label htmlFor={`${star}star`} className="flex items-center">
                                    {[...Array(5)].map((_, i) => (
                                        <i key={i} className={`fas fa-star ${i < star ? 'text-yellow-400' : 'text-gray-300'}`}></i>
                                    ))}
                                </label>
                            </li>
                        ))}
                    </ul>
                </div>
            </div>
        </div>
    );
};

export default memo(TourSidebar);

