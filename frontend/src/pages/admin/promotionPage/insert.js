import React, { useEffect, useState, useRef } from "react";
import Swal from "sweetalert2";
import "./insert.scss";
import { TourApi } from "services";
import { useNavigate } from "react-router-dom";
import { noImage } from "assets";

const PromotionInsertPage = () => {
    const introduceEditorRef = useRef(null);
    const descriptionEditorRef = useRef(null);
    const navigate = useNavigate();

    const [formData, setFormData] = useState({
        name: "",
        categoryId: 1,
        image: "",
        price: 0
    });
    const [preview, setPreview] = useState(noImage);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        TourApi.getCategory()
            .then(response => {
                if (response?.code === 1986) setCategories(response.result);
            })
            .catch(error => console.error("Failed to fetch categories: ", error));
    }, []);

    useEffect(() => {
        const initEditor = (id, ref) => {
            if (window.CKEDITOR && document.getElementById(id) && !ref.current) {
                ref.current = window.CKEDITOR.replace(id);
            }
        };

        initEditor("introduce", introduceEditorRef);
        initEditor("description", descriptionEditorRef);

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

    const handleChange = (e) => {
        setFormData(prev => ({ ...prev, [e.target.name]: e.target.value }));
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
                setFormData(prev => ({ ...prev, image: data.secure_url }));
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
            introduce: introduceEditorRef.current.getData(),
            description: descriptionEditorRef.current.getData()
        };

        try {
            const response = await TourApi.create(updatedData);

            if (response?.code === 1989) {
                Swal.fire("Thành công", "Tour đã được thêm thành công", "success")
                    .then(() => window.location.href = "/manage/tours/index");
            } else {
                Swal.fire("Lỗi", response.message || "Có lỗi xảy ra!", "error");
            }
        } catch (error) {
            Swal.fire("Lỗi", "Đã xảy ra lỗi không xác định", "error");
        }
    };

    return (
        <div className="tour-insert-page">
            <div className="form-container">
                <div className="form-content">
                    <h2>Thêm Tour</h2>
                    <form onSubmit={handleSubmit} className="tour-insert-form">
                        <div className="form-group">
                            <label>Tên Tour:</label>
                            <input name="name" type="text" required value={formData.name} onChange={handleChange} />
                        </div>

                        <div className="form-group">
                            <label>Giới thiệu:</label>
                            <textarea id="introduce"></textarea>
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
                                <img src={preview} alt="ảnh tour" className="image" />
                                <input type="file" accept="image/*" onChange={handleFileChange} required/>
                            </div>
                        </div>

                        <div className="form-group">
                            <label>Mô tả:</label>
                            <textarea id="description"></textarea>
                        </div>

                        <div className="form-group">
                            <label>Giá:</label>
                            <input name="price" type="number" min="0" required value={formData.price} onChange={handleChange} />
                        </div>

                        <div className="button-group">
                            <button type="button" onClick={() => navigate("/manage/tours/index")} className="btn btn-secondary">Quay về</button>
                            <button type="submit" className="btn btn-primary">Thêm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default PromotionInsertPage;