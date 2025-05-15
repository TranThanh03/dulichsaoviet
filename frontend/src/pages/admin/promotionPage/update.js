import { useEffect, useState } from "react";
import { PromotionApi } from "services";
import { useNavigate, useParams } from "react-router-dom";
import { FaArrowLeft } from "react-icons/fa";
import "./update.scss";
import DatePicker from "react-datepicker";
import { ToastContainer } from "react-toastify";
import { ErrorToast, SuccessToast } from "component/notifi";
import { pick } from "lodash";

const PromotionUpdatePage = () => {
    const { id } = useParams();
    const [formData, setFormData] = useState({
        code: null,
        title: null,
        description: null,
        discount: null,
        startDate: new Date().toISOString().split('T')[0],
        endDate: new Date().toISOString().split('T')[0],
        quantity: null,
        status: null
    });
    const navigate = useNavigate();
    const [isLoading, setIsLoading] = useState(false);

    useEffect(() => {
        const fetchPromotion = async () => {
            setIsLoading(true);

            try {
                const response = await PromotionApi.getById(id);

                if (response?.code === 1703) {
                    setFormData(
                        pick(
                            response?.result,
                            ["code", "title", "description", "discount", "startDate", "endDate", "quantity", "status"]
                        )
                    );
                } else {
                    navigate("/manage/error/404");
                }
            } catch (error) {
                console.error("Failed to fetch promotion: ", error);
                navigate("/manage/error/404");
            }  finally {
                setIsLoading(false);
            }
        }

        fetchPromotion();
    }, [id])

    const handleChange = (e) => {
        setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
    };

    const handleDateChange = (date, name) => {
        const formattedDate = date ? date.toISOString().split('T')[0] : null;
        setFormData(prev => ({ ...prev, [name]: formattedDate }));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        let updatedFormData = { ...formData };

        if (formData.status === 'Đang diễn ra') {
            const today = new Date();
            const tomorrow = new Date();
            tomorrow.setDate(today.getDate() + 1);

            const formatDate = (date) => date.toISOString().split('T')[0];

            updatedFormData.startDate = formatDate(tomorrow);
        }

        try {
            const response = await PromotionApi.update(id, updatedFormData);

            if (response?.code === 1704) {
                SuccessToast("Cập nhật khuyến mãi thành công.");
            } else {
                ErrorToast(response?.message || "Cập nhật khuyến mãi thất bại.");
            }
        } catch (error) {
            console.error("Failed to create promotion: ", error);
            ErrorToast("Đã xảy ra lỗi không xác định! Vui lòng thử lại sau.");
        }
    };

    if (isLoading) {
        return (
            <div style={{ height: 1000 }}></div>
        );
    }

    return (
        <div className="promotion-update-page px-4">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="card shadow col-sm-8 col-lg-6">
                        <div className="card-body">
                            <h3 className="text-center mb-4 fw-bold">Cập nhật khuyến mãi</h3>
                            <form onSubmit={handleSubmit}>
                                <div className="mb-3">
                                    <label className="form-label ">Mã:</label>
                                    <input name="code" type="text" required value={formData.code || ""} onChange={handleChange} className="form-control" disabled/>
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Tiêu đề:</label>
                                    <input name="title" type="text" required value={formData.title || ""} onChange={handleChange} className="form-control" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Mô tả:</label>
                                    <textarea name="description" rows={5} required value={formData.description || ""} onChange={handleChange} className="form-control" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Giảm giá:</label>
                                    <input name="discount" type="number" min="1" required value={formData.discount || ""} onChange={handleChange} className="form-control" />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Ngày bắt đầu:</label>
                                    <DatePicker
                                        selected={formData.startDate || ""}
                                        onChange={(date) => handleDateChange(date, 'startDate')}
                                        className="form-control"
                                        dateFormat="dd-MM-yyyy"
                                        required
                                        disabled={formData.status !== 'Chưa diễn ra'}
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Ngày kết thúc:</label>
                                    <DatePicker
                                        selected={formData.endDate || ""}
                                        onChange={(date) => handleDateChange(date, 'endDate')}
                                        className="form-control"
                                        dateFormat="dd-MM-yyyy"
                                        required
                                    />
                                </div>

                                <div className="mb-3">
                                    <label className="form-label">Số lượng:</label>
                                    <input name="quantity" type="number" min="1" required value={formData.quantity || ""} onChange={handleChange} className="form-control" />
                                </div>

                                <div className="d-flex justify-content-center gap-3">
                                    <button type="button" className="btn btn-back"
                                        onClick={() => {
                                            navigate("/manage/promotions");
                                        }}
                                    >
                                        <FaArrowLeft size={18} color="black" />
                                    </button>

                                    <button type="submit" className="btn btn-submit fw-bold">Cập nhật</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <ToastContainer />
        </div>
    );
};

export default PromotionUpdatePage;