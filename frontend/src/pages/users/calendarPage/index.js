import { memo, useEffect, useState } from 'react';
import './index.scss';
import { useNavigate, Link } from 'react-router-dom';
import Swal from 'sweetalert2';
import formatCurrency from 'utils/formatCurrency';
import formatDatetime from 'utils/formatDatetime';
import { noImage } from 'assets';

const CalendarPage = () => {
//     const [calendars, setCalendars] = useState([]);
//     const paymentClassMap = {
//         "Đã thanh toán": "paid",
//         "Chưa thanh toán": "unpaid",
//     };
//     const statusClassMap = {
//         "Đã xác nhận": "confirm",
//         "Đã hủy": "cancel",
//         "Đang xử lý": "processing",
//     };
//     const navigate = useNavigate();
//     const [isLoading, setLoading] = useState(false);

//     useEffect(() => {
//         const fetchData = async () => {
//             try {
//                 const response = await OrderApi.getByUserId();
//                 if (response?.code === 1957 && response?.result) {
//                     setCalendars(response.result);
//                 }
//             }
//             catch (error) {
//                 console.error("Failed to fetch data:", error);

//                 if (error?.status === 401 || error === 'Network Error') {
//                     navigate("/auth/login");
//                 }
//             }
//             finally {
//                 setLoading(true);
//             }
//         };

//         fetchData();
//     }, [navigate]);

//     const handleCancel = async (id, orderId) => {
//         const result = await Swal.fire({
//             title: "Xác nhận",
//             html: `Bạn có chắc chắn muốn hủy lịch đặt <b>${orderId}</b> không?`,
//             icon: "warning",
//             showCancelButton: true,
//             confirmButtonText: "Có",
//             cancelButtonText: "Không",
//         });

//         if (result.isConfirmed) {
//             try {
//                 const response = await OrderApi.cancel(id);

//                 if (response.code === 1955) {
//                     Swal.fire({
//                         title: "Thành công",
//                         text: "Lịch đặt đã được hủy thành công",
//                         icon: "success",
//                         confirmButtonText: "Đóng",
//                     });

//                     setCalendars((prev) =>
//                         prev.map((item) =>
//                             item.id === id ? { ...item, orderStatus: "Đã hủy" } : item
//                         )
//                     );
//                 } else {
//                     Swal.fire({
//                         title: "Lỗi",
//                         text: response.message || "Hủy lịch đặt không thành công!",
//                         icon: "error",
//                         confirmButtonText: "Đóng",
//                     });
//                 }
//             } catch (error) {
//                 Swal.fire({
//                     title: "Lỗi",
//                     text: "Đã xảy ra lỗi khi hủy lịch đặt!",
//                     icon: "error",
//                     confirmButtonText: "Đóng",
//                 });
//             }
//         }
//     };

//     const handlePayment = async (item) => {
//         try {
//             const response = await PaymentApi.processLater(item.id, { method: 'momo' });

//             if (response?.code === 1942 && response?.result?.paymentUrl) {
//                 window.location.href = response.result.paymentUrl;
//             }
//             else if (response?.code === 1022) {
//                 Swal.fire({
//                     title: 'Lỗi',
//                     html: `<p style="color: red; padding-bottom: 5px">Số người của lịch đặt vượt quá số người tối đa!</p>
//                             <p>Vui lòng hủy lịch đặt và đặt lại.</p>`,
//                     icon: 'error',
//                     confirmButtonText: 'Đóng'
//                 });
//             }
//             else if (response?.code === 1023) {
//                 Swal.fire({
//                     title: 'Lỗi',
//                     text: 'MoMo đang bảo trì. Vui lòng chọn phương thức thanh toán khác!',
//                     icon: 'error',
//                     confirmButtonText: 'Đóng'
//                 });
//             }
//             else {
//                 Swal.fire({
//                     title: "Lỗi",
//                     text: "Đã xảy ra lỗi thanh toán. Vui lòng thử lại!",
//                     icon: "error",
//                     confirmButtonText: "Đóng",
//                 });
//             }
//         } catch (error) {
//             Swal.fire({
//                 title: "Lỗi",
//                 text: "Đã xảy ra lỗi thanh toán. Vui lòng thử lại sau!",
//                 icon: "error",
//                 confirmButtonText: "Đóng",
//             });
//         }
//     };

//     if (!isLoading) {
//         return (
//             <div style={{height: 1000}}></div>
//         );
//     }

//     return (
//         <div className="calendar-page">
//             {calendars && Array.isArray(calendars) && calendars.length > 0 ? (
//                 <div className="container">
//                     <h1>Danh sách Tour đã đặt</h1>
//                     <div className="list">
//                         {calendars.map((item, index) => (
//                             <div className="item" key={index}>
//                                 <div className="image">
//                                     <Link to={`/tours/detail/${item.tourId}`}>
//                                         <img src={item.tourImage ? item.tourImage : noImage} id={item.tourImage ? 'img' : 'no-img'} alt={`${item.tourName}`} />
//                                     </Link>
//                                 </div>
//                                 <div className="infor">
//                                     <div className="name">
//                                         <h3>
//                                             <Link to={`/tours/detail/${item.tourId}`}>
//                                                 {item.tourName}(<span id="start-date">{formatDatetime(item.startDate)}</span> - <span id="end-date">{formatDatetime(item.endDate)}</span>)
//                                             </Link>
//                                         </h3>
//                                     </div>
//                                     <div className="context">
//                                         <table>
//                                             <tbody>
//                                                 <tr>
//                                                     <td className="bold">Mã lịch đặt:</td>
//                                                     <td>{item.orderId}</td>
//                                                     <td className="bold p-left">Mã giao dịch:</td>
//                                                     <td>{item.paymentId}</td>
//                                                 </tr>
//                                                 <tr>
//                                                     <td className="bold">Thời gian đặt:</td>
//                                                     <td>{formatDatetime(item.orderDatetime)}</td>
//                                                     <td className="bold p-left">Phương thức thanh toán:</td>
//                                                     <td>{item.method}</td>
//                                                 </tr>
//                                                 <tr>
//                                                     <td className="bold">Hướng dẫn viên:</td>
//                                                     <td>
//                                                         <Link to={`/guides/detail/${item.guideId}`}>{item.guideName}</Link>
//                                                     </td>
//                                                     <td className="bold p-left">Trạng thái thanh toán:</td>
//                                                     <td className={paymentClassMap[item.paymentStatus] || ""}>{item.paymentStatus}</td>
//                                                 </tr>
//                                                 <tr>
//                                                     <td className="bold">Số người:</td>
//                                                     <td>{item.numberOfPeople}</td>
//                                                     <td className="bold p-left">Thời gian thanh toán:</td>
//                                                     <td>{item.paymentDatetime != null ? formatDatetime(item.paymentDatetime) : ''}</td>
//                                                 </tr>
//                                                 <tr>
//                                                     <td className="bold">Trạng thái lịch đặt:</td>
//                                                     <td className={statusClassMap[item.orderStatus] || ""}>{item.orderStatus}</td>
//                                                     <td className="bold p-left">Tổng tiền:</td>
//                                                     <td style={{color: 'red'}}>{formatCurrency(item.totalPrice)}</td>
//                                                 </tr>
//                                             </tbody>
//                                         </table>
//                                     </div>
//                                     <div className="control">
//                                         {item.orderStatus && item.orderStatus === "Đang xử lý" && (
//                                             <button type="button" id="btn-cancel" 
//                                                 onClick={() => handleCancel(item.id, item.orderId)}
//                                             >
//                                                 Hủy tour
//                                             </button>
//                                         )}
                                        
//                                         {item.orderStatus && item.orderStatus === "Đang xử lý" && item.paymentStatus && item.paymentStatus === "Chưa thanh toán" && (
//                                             <button type="button" id="btn-payment"
//                                                 onClick={() => handlePayment(item)}
//                                             >
//                                                 Thanh toán
//                                             </button>
//                                         )}
//                                     </div>
//                                 </div>
//                             </div>
//                         ))}
//                     </div>
//                 </div>
//             ) : (
//                 <div className="calendar-none">
//                     <h2>Chưa có tour nào được đặt. Hãy tìm hiểu các lựa chọn du lịch tuyệt vời cùng Sao Việt!</h2>
//                 </div>
//             )}
//         </div>
//     );
};

export default memo(CalendarPage);