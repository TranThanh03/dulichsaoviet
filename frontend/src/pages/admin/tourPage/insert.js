import React, { useState } from "react";
import "./insert.scss";
import { useNavigate } from "react-router-dom";
import Step1 from "component/admin/tour/insert/Step1";
import Step2 from "component/admin/tour/insert/Step2";
import Step3 from "component/admin/tour/insert/Step3";
import StepZilla from 'react-stepzilla';
import { ToastContainer } from "react-toastify";
import { FaArrowLeft } from "react-icons/fa";

const TourInsertPage = () => {
    const [formData, setFormData] = useState({
        name: '',
        destination: '',
        area: 'b',
        quantityDay: 1,
        description: '',
        image: [],
        itinerary: []
    });
    const [imgPreview, setImgPreview] = useState({
        image: [],
        previewURLs: [] 
    });
    const navigate = useNavigate();

    const steps = [
        { name: 'Nhập thông tin', component: <Step1 formData={formData} setFormData={setFormData} /> },
        { name: 'Thêm hình ảnh', component: <Step2 formData={formData} setFormData={setFormData} imgPreview={imgPreview} setImgPreview={setImgPreview}/> },
        { name: 'Nhập lộ trình', component: <Step3 formData={formData} setFormData={setFormData} setImgPreview={setImgPreview}/> },
    ];

    return (
        <>
            <div className="tour-insert-page">
                <div className="row">
                    <div className="col-md-12 col-sm-12">
                        <div className="x_panel">
                            <div className="x_title">
                                <h2 className="fw-bold">Thêm Tour</h2>
                                <button type="button" className="btn btn-back float-right"
                                    onClick={() => {
                                        navigate("/manage/tours/index");
                                    }}
                                >
                                    <FaArrowLeft size={18} color="black" />
                                </button>
                                <div className="clearfix"></div>
                            </div>
                            <div className="x_content add-tours mb-3">
                                <StepZilla
                                    nextButtonText={"»"}
                                    backButtonText={"«"}
                                    steps={steps}
                                />
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <ToastContainer />
        </>
    );
};

export default TourInsertPage;