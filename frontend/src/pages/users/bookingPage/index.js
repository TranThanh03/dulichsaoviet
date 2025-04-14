import { memo, useState, useEffect } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import './index.scss';
import formatCurrency from 'utils/formatCurrency';
import { AssignmentApi, CustomerApi, PaymentApi } from 'services';
import formatDatetime from 'utils/formatDatetime';
import Swal from 'sweetalert2';
import { ScrollToTop } from 'utils/ScrollToTop';
import { noImage } from 'assets';

const BookingPage = () => {
    const [user, setUser] = useState({ fullName: "", phone: "", email: "" });
    const [assignment, setAssignment] = useState({
        tourName: "",
        tourImage: "",
        startDate: Date(),
        endDate: Date(),
        tourPrice: 0,
        guidePrice: 0,
        guideName: "",
        numberOfPeople: 0,
        totalPeople: 0,
        guideAvatar: ""
    });

    const { id } = useParams();
    const [people, setPeople] = useState(1);
    const [userId, setUserId] = useState(null);
    const [assignmentId, setAssignmentId] = useState(null);
    const [numberOfPeople, setNumberOfPeople] = useState(0);
    const [totalPeople, setTotalPeople] = useState(0);
    const [tourCost, setTourCost] = useState(0);
    const [guideCost, setGuideCost] = useState(0);
    const [totalCost, setTotalCost] = useState(tourCost + guideCost);
    const [totalPayment, setTotalPayment] = useState(totalCost * people);
    const [paymentMethod, setPaymentMethod] = useState('momo');
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ method: paymentMethod, amount: totalPayment });
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const resUser = await CustomerApi.getByToken();
                const resAssignment = await AssignmentApi.getById(id);

                if (resUser?.code === 1996) {
                    setUser(resUser.result);
                    setUserId(resUser.result.id);
                }

                if (resAssignment?.code === 1973 && resAssignment?.result) {
                    setAssignment(resAssignment.result);
                    setTourCost(resAssignment.result.tourPrice);
                    setGuideCost(resAssignment.result.guidePrice);
                    setNumberOfPeople(resAssignment.result.numberOfPeople);
                    setTotalPeople(resAssignment.result.totalPeople);
                    setAssignmentId(resAssignment.result.id);
                }
            }
            catch(error) {
                console.error("Failed to fetch data: " + error);

                if (error?.status === 401 || error === 'Network Error') {
                    navigate("/auth/login");
                }
            }
            finally {
                setLoading(true);
            }
        };

        fetchData();
    }, [id, navigate])

    useEffect(() => {
        setTotalCost(tourCost + guideCost);
    }, [tourCost, guideCost]);

    useEffect(() => {
        setTotalPayment(totalCost * people);
    }, [people, totalCost]);

    useEffect(() => {
        setFormData({
            userId: userId,
            assignmentId: assignmentId,
            numberOfPeople: people,
            method: paymentMethod,
            amount: totalPayment
        });
    }, [userId, assignmentId, people, paymentMethod, totalPayment]);

    const handleIncrease = () => {
        setPeople(prev => Math.min(prev + 1, 99));
    };

    const handleDecrease = () => {
        setPeople(prev => Math.max(prev - 1, 1));
    };

    const handleChange = (e) => {
        let value = e.target.value;
        let parsedValue = parseInt(value, 10);
        setPeople(parsedValue < 1 ? 1 : Math.min(parsedValue, 99));
    };

    const handleBlur = (e) => {
        let value = e.target.value;
        let parsedValue = parseInt(value, 10);
        setPeople(isNaN(parsedValue) || parsedValue < 1 ? 1 : Math.min(parsedValue, 99));
    };

    
    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await PaymentApi.process(formData);

            if (response?.code === 1022) {
                Swal.fire({
                    title: 'Lỗi',
                    text: 'Số lượng người vượt quá số lượng người tối đa!',
                    icon: 'error',
                    confirmButtonText: 'Đóng'
                });
            }
            else if (response?.code === 1023) {
                Swal.fire({
                    title: 'Lỗi',
                    text: 'MoMo đang bảo trì. Vui lòng chọn phương thức thanh toán khác!',
                    icon: 'error',
                    confirmButtonText: 'Đóng'
                });
            }
            else if (response?.code === 1944) {
                Swal.fire({
                    title: 'Thành công',
                    html: `<p style="color: green; margin-bottom: 5px;">Đặt tour thành công</p>
                           <p style="color: red;">${response.result.paymentUrl}</p>`,
                    icon: 'success',
                    confirmButtonText: 'Đóng'
                }).then(() => {
                    navigate("/calendars/index");
                });
            }
            else if (response?.code === 1945 && response?.result?.paymentUrl) {
                window.location.href = response.result.paymentUrl;
            }
            else {
                Swal.fire({
                    title: 'Lỗi',
                    text: 'Đang xảy ra lỗi thanh toán. Vui lòng thử lại!',
                    icon: 'error',
                    confirmButtonText: 'Đóng'
                });
            }
        } catch (error) {
            Swal.fire({
                title: 'Lỗi',
                text: 'Đã xảy ra lỗi không xác định. Vui lòng thử lại!',
                icon: 'error',
                confirmButtonText: 'Đóng'
            });
        } 
    }

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="booking-form">
            <ScrollToTop />
            <h1>Đặt Tour</h1>
            <div className="form-container">
                <section className="tour-info">
                    <h2>Thông tin tour</h2>
                    <div className="tour-image">
                        <img src={assignment.tourImage ? assignment.tourImage : noImage} alt={assignment.tourName} />
                    </div>
                    <div className="form-group">
                        <label>Tên tour:</label>
                        <input type="text" value={assignment.tourName} readOnly />
                    </div>
                    <div className="form-group">
                        <label>Ngày khởi hành:</label>
                        <input type="text" value={formatDatetime(assignment.startDate)} disabled />
                    </div>
                    <div className="form-group">
                        <label>Ngày kết thúc:</label>
                        <input type="text" value={formatDatetime(assignment.endDate)} disabled />
                    </div>
                    <div className="form-group">
                        <label>Giá tour:</label>
                        <input className="cost" type="text" value={formatCurrency(tourCost)} disabled />
                    </div>
                </section>

                <section className="customer-info">
                    <h2>Thông tin người đặt</h2>
                    <div className="form-group">
                        <label>Họ và tên:</label>
                        <input type="text" value={user.fullName} disabled />
                    </div>
                    <div className="form-group">
                        <label>Số điện thoại:</label>
                        <input type="text" value={user.phone} disabled />
                    </div>
                    <div className="form-group">
                        <label>Email:</label>
                        <input type="text" value={user.email} disabled />
                    </div>
                </section>
            </div>

            <div className="guide-info">
                <h2>Thông tin hướng dẫn viên</h2>
                <div className="guide-image">
                    <img src={assignment.guideAvatar ? assignment.guideAvatar : noImage} alt={assignment.guideName} id="guide-image" />
                </div>
                <div className="guide-content">
                    <div className="form-group">
                        <label>Hướng dẫn viên:</label>
                        <input type="text" value={assignment.guideName} readOnly />
                    </div>
                    <div className="form-group">
                        <label>Đã đặt:</label>
                        <input className="bold-red" type="text" value={`${numberOfPeople}/${totalPeople}`} readOnly />
                    </div>
                    <div className="form-group">
                        <label>Giá hướng dẫn viên:</label>
                        <input className="cost" type="text" value={formatCurrency(guideCost)} disabled />
                    </div>
                </div>
            </div>

            <div className="form-group people-group">
                <h3>Số lượng người:</h3>
                <div className="people-input">
                    <button type="button" onClick={handleDecrease}>-</button>
                    <input type="number" min={1} max={99} value={people} onChange={handleChange} onBlur={handleBlur} />
                    <button type="button" onClick={handleIncrease}>+</button>
                </div>
            </div>

            <div className="payment-method">
                <h3>Phương thức thanh toán:</h3>
                <div className="payment-options">
                    {['momo', 'vnpay', 'later'].map(method => (
                        <label key={method} className={`payment-box ${method}`}>
                            <input 
                                type="radio" 
                                name="payment" 
                                value={method} 
                                checked={paymentMethod === method} 
                                onChange={() => setPaymentMethod(method)}
                            />
                            {method === 'later' ? (
                                <span value="later" id="later">Thanh toán sau</span>
                            ) : (
                                <span value={method}>
                                    <img className="payment-img" id={method} src={`/assets/img/payment/${method}.jpg`} alt={method}/>
                                    {method.toUpperCase()}
                                </span>
                            )}
                        </label>
                    ))}
                </div>
            </div>

            <div className="form-group" id="cost-group">
                <h3>Chi tiết thanh toán:</h3>
                <div className="cost-details">
                    <p><strong>Tổng tiền:</strong> <span>{formatCurrency(totalCost)}</span></p>
                    <p><strong>Số người:</strong> <span>{people}</span></p>
                    <hr />
                    <p><strong>Tổng thanh toán:</strong> <span id="total-payment">{formatCurrency(totalPayment)}</span></p>
                </div>
            </div>

            <div className="form-actions">
                <button type="submit" className="btn-submit" onClick={handleSubmit}>Xác nhận đặt Tour</button>
            </div>
        </div>
    );
};

export default memo(BookingPage);