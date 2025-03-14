import { useEffect, useRef, useState } from "react";
import Swal from "sweetalert2";
import "./insert.scss";
import { GuideApi } from "api";
import { useNavigate } from "react-router-dom";

const GuideInsertPage = () => {
    const descriptionEditorRef = useRef(null);
    const navigate = useNavigate();
    
    const [formData, setFormData] = useState({
        fullName: "",
        phone: "",
        email: "",
        dateOfBirth: "",
        sex: "Nam",
        description: "",
        avatar: "",
        evaluate: 1,
    });
    const [preview, setPreview] = useState("/assets/users/img/guide/no-image.jpg");

    useEffect(() => {
        const initEditor = (id, ref) => {
            if (window.CKEDITOR && document.getElementById(id) && !ref.current) {
                ref.current = window.CKEDITOR.replace(id);
            }
        };

        setTimeout(() => {
            initEditor("description", descriptionEditorRef);
        }, 100);

        return () => {
            if (descriptionEditorRef.current) {
                descriptionEditorRef.current.destroy();
                descriptionEditorRef.current = null;
            }
        };
    }, []);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleFileChange = async (e) => {
        const file = e.target.files[0];
        if (!file) return;

        if (!["image/jpeg", "image/png", "image/gif", "image/webp"].includes(file.type)) {
            return Swal.fire("Lỗi", "Chỉ chấp nhận JPG, PNG, GIF, WEBP!", "error");
        }

        const previewURL = URL.createObjectURL(file);
        setPreview(previewURL);

        const formDataCloudinary = new FormData();
        formDataCloudinary.append("file", file);
        formDataCloudinary.append("upload_preset", "website-saoviet");
        formDataCloudinary.append("folder", "saoviet");

        try {
            const response = await fetch("https://api.cloudinary.com/v1_1/doie0qiiq/image/upload", {
                method: "POST",
                body: formDataCloudinary
            });
            const data = await response.json();

            if (data.secure_url) {
                setFormData(prev => ({ ...prev, avatar: data.secure_url }));
                setPreview(data.secure_url);
            } else {
                Swal.fire("Lỗi", "Không thể tải ảnh lên Cloudinary!", "error");
            }
        } catch (error) {
            Swal.fire("Lỗi", "Đã xảy ra lỗi khi tải ảnh lên Cloudinary!", "error");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const updatedData = {
            ...formData,
            description: descriptionEditorRef.current.getData(),
            dateOfBirth: new Date(formData.dateOfBirth).toISOString().split("T")[0]
        };

        try {
            const response = await GuideApi.create(updatedData);
            
            if (response?.code === 1969) {
                Swal.fire("Thành công", "Hướng dẫn viên đã được thêm thành công", "success")
                    .then(() => window.location.href = "/manage/guides/index");
            } else {
                Swal.fire("Lỗi", response.message || "Có lỗi xảy ra!", "error");
            }
        } catch (error) {
            Swal.fire("Lỗi", "Đã xảy ra lỗi không xác định", "error");
        }
    };

    return (
        <div className="guide-insert-page">
            <div className="form-container">
                <div className="form-content">
                    <h2>Thêm hướng dẫn viên</h2>
                    <form onSubmit={handleSubmit} className="guide-insert-form">
                        <div className="form-group">
                            <label>Họ và tên:</label>
                            <input name="fullName" type="text" required value={formData.fullName} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Số điện thoại:</label>
                            <input name="phone" type="tel" required pattern="[0-9]{10}" value={formData.phone} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Email:</label>
                            <input name="email" type="email" required value={formData.email} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Ngày sinh:</label>
                            <input name="dateOfBirth" type="date" required value={formData.dateOfBirth} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Giới tính:</label>
                            <select name="sex" value={formData.sex} onChange={handleChange}>
                                <option value="Nam">Nam</option>
                                <option value="Nữ">Nữ</option>
                            </select>
                        </div>

                        <div className="form-group">
                            <label>Mô tả:</label>
                            <textarea id="description" name="description" required value={formData.description} onChange={handleChange}></textarea>
                        </div>

                        <div className="form-group">
                            <label>Ảnh hướng dẫn viên:</label>
                            <div className="image-upload">
                                <img src={preview} alt="Ảnh hướng dẫn viên" className="image" />
                                <input type="file" accept="image/*" onChange={handleFileChange} required/>
                            </div>
                        </div>

                        <div className="form-group">
                            <label>Đánh giá:</label>
                            <select name="evaluate" value={formData.evaluate} onChange={handleChange}>
                                {[1, 2, 3, 4, 5].map((star) => (
                                    <option key={star} value={star}>{star} sao</option>
                                ))}
                            </select>
                        </div>

                        <div className="button-group">
                            <button type="button" onClick={() => navigate("/manage/guides/index")} className="btn btn-secondary">Quay về</button>
                            <button type="submit" className="btn btn-primary">Thêm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default GuideInsertPage;