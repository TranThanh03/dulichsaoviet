import React, { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { TourApi } from "services";
import "./update.scss";

const TourUpdatePage = () => {
    const { id } = useParams();
    const introduceEditorRef = useRef(null);
    const descriptionEditorRef = useRef(null);
    const [isLoading, setLoading] = useState(false);
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        categoryId: 1,
        image: "",
        price: 0
    });
    const [preview, setPreview] = useState("/assets/users/img/tour/no-image.jpg");
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const [tourResponse, categoryResponse] = await Promise.all([
                    TourApi.getById(id),
                    TourApi.getCategory()
                ]);

                if (tourResponse?.code === 1985) {
                    setFormData(tourResponse.result);
                    setPreview(tourResponse.result.image || "/assets/users/img/tour/no-image.jpg");
                }

                if (categoryResponse?.code === 1986) {
                    setCategories(categoryResponse.result);
                }
            }
            catch (error) {
                Swal.fire("Lỗi", "Không thể tải dữ liệu!", "error");
            }
            finally {
                setLoading(true);
            }
        };

        fetchData();
    }, [id]);

    useEffect(() => {
        const initEditor = (id, ref) => {
            if (window.CKEDITOR && document.getElementById(id) && !ref.current) {
                ref.current = window.CKEDITOR.replace(id);
            }
        };

        setTimeout(() => {
            initEditor("introduce", introduceEditorRef);
            initEditor("description", descriptionEditorRef);
        }, 100);

        return () => {
            if (introduceEditorRef.current) {
                introduceEditorRef.current.destroy();
                introduceEditorRef.current = null;
            }

            if (descriptionEditorRef.current) {
                descriptionEditorRef.current.destroy();
                descriptionEditorRef.current = null;
            }
        };
    }, []);

    useEffect(() => {
        if (introduceEditorRef.current) introduceEditorRef.current.setData(formData.introduce || "");
        if (descriptionEditorRef.current) descriptionEditorRef.current.setData(formData.description || "");
    }, [formData]);

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleFileChange = async (e) => {
        const file = e.target.files[0];
        if (!file) return;

        if (!["image/jpeg", "image/png", "image/gif", "image/webp"].includes(file.type)) {
            return Swal.fire("Lỗi", "Chỉ chấp nhận JPG, PNG, GIF, WEBP!", "error");
        }

        setPreview(URL.createObjectURL(file));
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
            if (data.secure_url) setFormData({ ...formData, image: data.secure_url });
        } catch (error) {
            Swal.fire("Lỗi", "Không thể tải ảnh lên Cloudinary!", "error");
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        const updatedFormData = {
            ...formData,
            introduce: introduceEditorRef.current.getData(),
            description: descriptionEditorRef.current.getData()
        };

        try {
            const response = await TourApi.update(id, updatedFormData);
            if (response?.code === 1983) {
                Swal.fire("Thành công", "Tour đã được cập nhật thành công", "success");
            } else {
                Swal.fire("Lỗi", response.message || "Có lỗi xảy ra!", "error");
            }
        } catch (error) {
            Swal.fire("Lỗi", "Đã xảy ra lỗi không xác định", "error");
        }
    };

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="tour-update-page">
            <div className="form-container">
                <div className="form-content">
                    <h2>Cập nhật Tour {id}</h2>
                    <form onSubmit={handleSubmit} className="tour-update-form">
                        <div className="form-group">
                            <label>Tên Tour:</label>
                            <input name="name" type="text" required value={formData.name} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Giới thiệu:</label>
                            <textarea id="introduce" name="introduce" required value={formData.introduce} onChange={handleChange}></textarea>
                        </div>

                        <div className="form-group">
                            <label>Chủ đề:</label>
                            <select name="categoryId" required value={formData.categoryId} onChange={handleChange}>
                                {categories.map(cat => (
                                    <option key={cat.id} value={cat.id}>{cat.name}</option>
                                ))}
                            </select>
                        </div>

                        <div className="form-group">
                            <label>Ảnh Tour:</label>
                            <div className="image-upload">
                                <img src={preview} alt="Ảnh tour" className="image" />
                                <input type="file" accept="image/*" onChange={handleFileChange} />
                            </div>
                        </div>

                        <div className="form-group">
                            <label>Mô tả:</label>
                            <textarea id="description" name="description" required value={formData.description} onChange={handleChange}></textarea>
                        </div>

                        <div className="form-group">
                            <label>Giá:</label>
                            <input name="price" type="number" min="0" required value={formData.price} onChange={handleChange} />
                        </div>

                        <div className="button-group">
                            <button type="button" onClick={() => navigate("/manage/tours/index")} className="btn btn-secondary">Quay về</button>
                            <button type="submit" className="btn btn-primary">Cập nhật</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default TourUpdatePage;