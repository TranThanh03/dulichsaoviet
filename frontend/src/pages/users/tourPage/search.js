import { memo, useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import { TourApi } from 'services';

const SearchPage = () => {
    const [tours, setTours] = useState([]);
    const location = useLocation();
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const queryParams = new URLSearchParams(location.search);
        const searchQuery = queryParams.get('p');

        const fetchTourList = async () => {
            try {
                const response = await TourApi.searchTours(searchQuery, currentPage, pageSize);

                if (response?.code === 1981) {
                    setTours(response?.result?.content);
                    setTotalPages(response?.result?.totalPages);
                }
            } 
            catch (error) {
                console.error("Failed to fetch tours:", error);
            }
            finally {
                setLoading(true);
            }
        };

        if (searchQuery) {
            fetchTourList();
        }
    }, [location.search, currentPage]);

    if (!isLoading) {
        return (
            <div style={{height: 1000}}></div>
        );
    }

    return (
        <div className="tour-page">
            
        </div> 
    );
}

export default memo(SearchPage);