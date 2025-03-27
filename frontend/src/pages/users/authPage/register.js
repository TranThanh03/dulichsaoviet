import { memo, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './register.scss';
import { CustomerApi } from 'services';
import Swal from 'sweetalert2';

const RegisterPage = () => {
    const [formData, setFormData] = useState({
        fullName: '',
        phone: '',
        email: '',
        password: '',
        repeatpw: ''
    });

    const navigate = useNavigate();
    const [errors, setErrors] = useState({});

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        setErrors((prev) => ({ ...prev, [name]: '' }));
    };

    const validateForm = () => {
        let newErrors = {};

        if (!formData.fullName.trim()) newErrors.fullName = 'Họ và tên không được để trống!';
        
        if (!formData.phone.trim()) newErrors.phone = 'Số điện thoại không được để trống!';
        
        if (!formData.email.trim()) newErrors.email = 'Email không được để trống!';
        
        if (!formData.password.trim()) newErrors.password = 'Mật khẩu không được để trống!';
        
        if (!formData.repeatpw.trim()) newErrors.repeatpw = 'Vui lòng nhập lại mật khẩu!';
        else if (formData.password !== formData.repeatpw) newErrors.repeatpw = 'Mật khẩu không khớp!';

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleRegister = async (e) => {
        e.preventDefault();

        if (!validateForm()) return;

        try {
            const response = await CustomerApi.create(formData);

            if (response?.code === 1999) {
                setFormData({
                    fullName: '',
                    phone: '',
                    email: '',
                    password: '',
                    repeatpw: ''
                });

                Swal.fire({
                    title: 'Thành công',
                    text: 'Đăng ký tài khoản thành công',
                    icon: 'success',
                    confirmButtonText: 'Đóng'
                }).then(() => {
                    navigate('/auth/login');
                });
            } else {
                let newErrors = {};
                if (response?.code === 1001 || response?.code === 1004) newErrors.phone = response.message;
                if (response?.code === 1002 || response?.code === 1005) newErrors.email = response.message;
                if (response?.code === 1003) newErrors.password = response.message;
                if (response?.code === 1006 || response?.code === 1007) newErrors.fullName = response.message;

                setErrors(newErrors);
            }
        } catch (error) {
            setErrors({ general: 'Đã xảy ra lỗi không xác định. Vui lòng thử lại!' });
        }
    };

    return (
        <div className="register-page">
            <div className="container">
                <div className="box-area">
                    <h1 className="text-center">Đăng ký tài khoản</h1>

                    <form onSubmit={handleRegister}>
                        <div className="form-group">
                            <label htmlFor="name">Họ và tên</label>
                            <input
                                type="text"
                                id="name"
                                name="fullName"
                                value={formData.fullName}
                                onChange={handleInputChange}
                            />
                            {errors.fullName && <p className="warning">{errors.fullName}</p>}
                        </div>

                        <div className="form-group">
                            <label htmlFor="phone">Số điện thoại</label>
                            <input
                                type="text"
                                id="phone"
                                name="phone"
                                value={formData.phone}
                                onChange={handleInputChange}
                            />
                            {errors.phone && <p className="warning">{errors.phone}</p>}
                        </div>

                        <div className="form-group">
                            <label htmlFor="email">Email</label>
                            <input
                                type="email"
                                id="email"
                                name="email"
                                value={formData.email}
                                onChange={handleInputChange}
                            />
                            {errors.email && <p className="warning">{errors.email}</p>}
                        </div>

                        <div className="form-group">
                            <label htmlFor="pwd">Mật khẩu</label>
                            <input
                                type="password"
                                id="pwd"
                                name="password"
                                value={formData.password}
                                onChange={handleInputChange}
                            />
                            {errors.password && <p className="warning">{errors.password}</p>}
                        </div>

                        <div className="form-group">
                            <label htmlFor="repeatpw">Nhập lại mật khẩu</label>
                            <input
                                type="password"
                                id="repeatpw"
                                name="repeatpw"
                                value={formData.repeatpw}
                                onChange={handleInputChange}
                            />
                            {errors.repeatpw && <p className="warning">{errors.repeatpw}</p>}
                        </div>

                        {errors.general && <p className="warning">{errors.general}</p>}

                        <button type="submit" className="btn-submit">Đăng ký</button>
                    </form>

                    <p>
                        Bằng việc đăng ký tài khoản bạn đã đồng ý với 
                        <Link to="#"> Điều khoản sử dụng</Link> của chúng tôi!
                    </p>
                    <p>Bạn đã có tài khoản? <Link to="/auth/login">Đăng nhập</Link></p>
                </div>
            </div>
        </div>
    );
};

export default memo(RegisterPage);