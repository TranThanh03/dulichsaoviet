import { memo, useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './category.scss';
import { TourApi } from 'services';

const TourCategoryPage = () => {
    const [categories, setCategories] = useState([]);
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTourCategory = async () => {
            try {
                const response = await TourApi.getCategory();
                setCategories(response.result);
            }
            catch (error) {
                console.error("Failed to fetch tours category:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchTourCategory();
    }, []);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="tour-categories">
            <h1>Danh mục Tours du lịch</h1>
            <p>Khám phá các tour du lịch tuyệt vời được chúng tôi cung cấp</p>
            <div className="container">
                {categories && Array.isArray(categories) && categories.length > 0 ? (
                    categories.map((item, index) => (
                        <div className="category-item" key={index}>
                            <Link to={`/tours/category/${item.id}`}>
                                <img src={`/assets/users/img/tour/${item.image || 'no-image.jpg'}`} alt={`${item.name}`} />
                                <h4>{item.name}</h4>
                                <p>{item.description}</p>
                            </Link>
                        </div>
                    ))) : (
                        <p>Danh sách đang trống!</p>
                    )
                }
            </div>
        </div>
    )
}

export default memo(TourCategoryPage);