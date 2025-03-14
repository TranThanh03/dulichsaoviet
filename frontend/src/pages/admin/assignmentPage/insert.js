import { useState, useEffect } from "react";
import Swal from "sweetalert2";
import { AssignmentApi, TourApi, GuideApi } from "api";
import "./insert.scss";

const AddAssignmentForm = ({ onClose, onAdded }) => {
    const [tours, setTours] = useState([]);
    const [guides, setGuides] = useState([]);
    const getCurrentDate = () => {
        const today = new Date();
        const year = today.getFullYear();
        const month = (today.getMonth() + 1).toString().padStart(2, "0");
        const day = today.getDate().toString().padStart(2, "0");
        return `${year}-${month}-${day}`;
    };
    const [formData, setFormData] = useState({
        tourId: "",
        guideId: "",
        totalPeople: 1,
        startDate: getCurrentDate(),
        endDate: getCurrentDate(),
        guidePrice: 1
    });
    const [errorMsg, setErrorMsg] = useState("");

    useEffect(() => {
        const fetchData = async () => {
            try {
                const tourResponse = await TourApi.getToursByAssignment();
                const guideResponse = await GuideApi.getGuidesByAssignment();

                if (tourResponse?.code === 2989) {
                    setTours(tourResponse.result);
                }

                if (guideResponse?.code === 1963) {
                    setGuides(guideResponse.result);
                }
            } catch (error) {
                console.error("Failed fetch to data: ", error);
            }
        };
        
        fetchData();
    }, []);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const formattedData = {
                ...formData,
                startDate: new Date(formData.startDate).toISOString().split("T")[0],
                endDate: new Date(formData.endDate).toISOString().split("T")[0],
                totalPeople: parseInt(formData.totalPeople, 10) || 0,
                guidePrice: parseInt(formData.guidePrice, 10) || 0,
            };

            const response = await AssignmentApi.create(formattedData);
            
            if (response?.code === 1979) {
                Swal.fire("Thành công", "Lịch phân công đã được thêm", "success");
                onAdded();
                onClose();
            } else {
                setErrorMsg(response?.message);
            }
        } catch (error) {
            setErrorMsg("Lỗi khi thêm lịch phân công");
        }
    };

    return (
        <div className="modal">
            <div className="modal-content">
                <h2>Thêm lịch phân công</h2>
                <button id="btn-close" onClick={onClose}>X</button>
                <form onSubmit={handleSubmit}>
                    <label>Mã - Tên tour</label>
                    <select name="tourId" value={formData.tourId} onChange={handleChange} required>
                        <option value="">-- Chọn tour --</option>
                        {tours.map((tour) => (
                            <option key={tour.id} value={tour.id}>
                                {tour.id} - {tour.name}
                            </option>
                        ))}
                    </select>

                    <label>Mã - Tên hướng dẫn viên</label>
                    <select name="guideId" value={formData.guideId} onChange={handleChange} required>
                        <option value="">-- Chọn hướng dẫn viên --</option>
                        {guides.map((guide) => (
                            <option key={guide.id} value={guide.id}>
                                {guide.id} - {guide.fullName}
                            </option>
                        ))}
                    </select>

                    <label>Số người:</label>
                    <input type="number" name="totalPeople" value={formData.totalPeople} onChange={handleChange} required/>
                    <label>Ngày khởi hành:</label>
                    <input type="date" name="startDate" value={formData.startDate} onChange={handleChange} required />
                    <label>Ngày kết thúc:</label>
                    <input type="date" name="endDate" value={formData.endDate} onChange={handleChange} required />
                    <label>Giá hướng dẫn viên:</label>
                    <input type="number" name="guidePrice" value={formData.guidePrice} onChange={handleChange} required/>
                    <p id="error-msg">{errorMsg}</p>

                    <button id="btn-submit" type="submit">Thêm</button>
                </form>
            </div>
        </div>
    );
};

export default AddAssignmentForm;