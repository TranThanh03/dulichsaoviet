import React, { useState, useEffect, memo, useContext } from 'react';
import './style.scss';
import { OrderApi, TourApi, CustomerApi } from 'services';
import { AuthContext } from '../theme/masterLayout';
import formatCurrency from 'utils/formatCurrency';
import formatDatetime from 'utils/formatDatetime';

const HomePage = () => {
    const [data, setData] = useState({
        quantityUser: 0,
        quantityTour: 0,
        quantityGuide: 0,
        quantityOrder: 0,
        quantityTotalPrice: 0
    });
    const { fullName } = useContext(AuthContext);
    const [tours, setTours] = useState(null);
    const [users, setUsers] = useState(null);
    const [orders, setOrders] = useState(null);
    const [isLoading, setLoading] = useState(false);
    
    useEffect(() => {
        const fetchHomeInfo = async () => {
            try {
                const response = await OrderApi.info();

                if (response?.code === 1951) {
                    setData(response.result);
                }
            }
            catch (error) {
                console.error("Failed to fetch data: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        const fetchLatestOrders = async () => {
            try {
                const response = await OrderApi.latest();

                if (response?.code === 1950) {
                    setOrders(response.result);
                }
            }
            catch (error) {
                console.error("Failed to fetch orders: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        const fetchPopularTours = async () => {
            try {
                const response = await TourApi.popular();

                if (response?.code === 1980) {
                    setTours(response.result);
                }
            }
            catch (error) {
                console.error("Failed to fetch tours: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        const fetchLatestUsers = async () => {
            try {
                const response = await CustomerApi.latest();

                if (response?.code === 1993) {
                    setUsers(response.result);
                }
            }
            catch (error) {
                console.error("Failed to fetch users: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchHomeInfo();
        fetchLatestOrders();
        fetchPopularTours();
        fetchLatestUsers();
    }, []);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="home-manage-page">
            <div className="admin-dashboard">
                <div className="dashboard-header">
                    <h1>Trang quản trị Sao Việt</h1>
                    <p>Chào mừng bạn trở lại, {fullName}!</p>
                </div>

                <div className="dashboard-info">
                    <div className="dashboard-stats">
                        <div className="stat-card">
                            <h3>Khách hàng</h3>
                            <p>{data.quantityUser}</p>
                        </div>
                        <div className="stat-card">
                            <h3>Tours</h3>
                            <p>{data.quantityTour}</p>
                        </div>
                        <div className="stat-card">
                            <h3>Hướng dẫn viên</h3>
                            <p>{data.quantityGuide}</p>
                        </div>
                        <div className="stat-card">
                            <h3>Đơn đặt tour</h3>
                            <p>{data.quantityOrder}</p>
                        </div>
                        <div className="stat-card">
                            <h3>Tổng doanh thu</h3>
                            <p id="total-price">{formatCurrency(data.quantityTotalPrice)}</p>
                        </div>
                    </div>
                </div>

                <div className="dashboard-content">
                    <div className="recent-orders">
                        <h2>Đơn hàng mới</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Mã đơn hàng</th>
                                    <th>Khách hàng</th>
                                    <th>Tour</th>
                                    <th>Thời gian đặt</th>
                                    <th>Tổng tiền</th>
                                </tr>
                            </thead>
                            <tbody>
                                {Array.isArray(orders) && orders.length > 0 && (
                                    orders.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.orderId}</td>
                                            <td>{item.customerId}</td>
                                            <td>{item.tourName}</td>
                                            <td>{formatDatetime(item.orderDatetime)}</td>
                                            <td>{formatCurrency(item.totalPrice)}</td>
                                        </tr>
                                    ))
                                )}       
                            </tbody>
                        </table>
                    </div>

                    <div className="recent-users">
                        <h2>Khách hàng mới</h2>
                        <table>
                            <thead>
                                <tr>
                                    <th>Mã khách hàng</th>
                                    <th>Khách hàng</th>
                                    <th>Thời gian đăng ký</th>
                                </tr>
                            </thead>
                            <tbody>
                                {Array.isArray(users) && users.length > 0 && (
                                    users.map((item, index) => (
                                        <tr key={index}>
                                            <td>{item.userId}</td>
                                            <td>{item.fullName}</td>
                                            <td>{formatDatetime(item.registerTime)}</td>
                                        </tr>
                                    ))
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>

                <div className="highlighted-tours">
                    <h2>Tour nổi bật</h2>
                    {Array.isArray(tours) && tours.length > 0 && (
                        tours.map((item, index) => (
                            <ul key={index}>
                                <li>{item.name} <span id="orders">{item.orders} lượt đặt</span></li>
                            </ul>
                        ))
                    )}
                </div>
            </div>
        </div>
    );
};

export default memo(HomePage);