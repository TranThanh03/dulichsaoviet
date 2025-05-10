import React, { useEffect, useState, useRef } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Swal from "sweetalert2";
import { TourApi } from "services";
import "./update.scss";
import { noImage } from "assets";

const TourUpdatePage = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const introduceEditorRef = useRef(null);
  const descriptionEditorRef = useRef(null);

  const [tourCode, setTourCode] = useState("");
  const [preview, setPreview] = useState(noImage);
  const [isLoading, setLoading] = useState(false);

  const [categories, setCategories] = useState([]);
  const [formData, setFormData] = useState({
    name: "",
    categoryId: 1,
    image: "",
    price: 0
  });

  // 👉 Cleanup CKEditor
  const destroyEditors = () => {
    if (introduceEditorRef.current) {
      introduceEditorRef.current.destroy();
      introduceEditorRef.current = null;
    }
    if (descriptionEditorRef.current) {
      descriptionEditorRef.current.destroy();
      descriptionEditorRef.current = null;
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [tourRes, catRes] = await Promise.all([
          TourApi.getById(id),
          TourApi.getCategory()
        ]);

        if (tourRes?.code === 1985) {
          setTourCode(tourRes.result.tourCode);
          setFormData(tourRes.result);
          setPreview(tourRes.result.image || noImage);
        }

        if (catRes?.code === 1986) {
          setCategories(catRes.result);
        }
      } catch {
        Swal.fire("Lỗi", "Không thể tải dữ liệu!", "error");
      } finally {
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

    const timer = setTimeout(() => {
      initEditor("introduce", introduceEditorRef);
      initEditor("description", descriptionEditorRef);
    }, 100);

    return () => {
      clearTimeout(timer);
      destroyEditors();
    };
  }, []);

  useEffect(() => {
    if (introduceEditorRef.current)
      introduceEditorRef.current.setData(formData.introduce || "");
    if (descriptionEditorRef.current)
      descriptionEditorRef.current.setData(formData.description || "");
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

    const cloudForm = new FormData();
    cloudForm.append("file", file);
    cloudForm.append("upload_preset", "website-saoviet");
    cloudForm.append("folder", "saoviet");

    try {
      const res = await fetch("https://api.cloudinary.com/v1_1/doie0qiiq/image/upload", {
        method: "POST",
        body: cloudForm
      });
      const data = await res.json();
      if (data.secure_url) {
        setFormData({ ...formData, image: data.secure_url });
      }
    } catch {
      Swal.fire("Lỗi", "Không thể tải ảnh lên Cloudinary!", "error");
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const updated = {
      ...formData,
      introduce: introduceEditorRef.current?.getData() || "",
      description: descriptionEditorRef.current?.getData() || ""
    };

    try {
      const res = await TourApi.update(id, updated);
      if (res?.code === 1983) {
        Swal.fire("Thành công", "Tour đã được cập nhật", "success");
      } else {
        Swal.fire("Lỗi", res.message || "Có lỗi xảy ra!", "error");
      }
    } catch {
      Swal.fire("Lỗi", "Đã xảy ra lỗi không xác định", "error");
    }
  };

  if (!isLoading) return <div style={{ height: 500 }} />;

  return (
    <div className="tour-update-page">
      <div className="form-container">
        <div className="form-content">
          <h2>Cập nhật Tour {tourCode}</h2>
          <form onSubmit={handleSubmit} className="tour-update-form">
            <div className="form-group">
              <label>Tên Tour:</label>
              <input name="name" required value={formData.name} onChange={handleChange} />
            </div>

            <div className="form-group">
              <label>Giới thiệu:</label>
              <textarea id="introduce" name="introduce" required />
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
                <input type="file" accept="image/*" onChange={handleFileChange} />
              </div>
            </div>

            <div className="form-group">
              <label>Mô tả:</label>
              <textarea id="description" name="description" required />
            </div>

            <div className="form-group">
              <label>Giá:</label>
              <input name="price" type="number" min="0" required value={formData.price} onChange={handleChange} />
            </div>

            <div className="button-group">
              <button
                type="button"
                className="btn btn-secondary"
                onClick={() => {
                  destroyEditors();
                  navigate("/manage/tours/index");
                }}
              >
                Quay về
              </button>
              <button type="submit" className="btn btn-primary">Cập nhật</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default TourUpdatePage;