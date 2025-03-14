import { memo, useState, useEffect } from 'react';
import './password.scss';
import { useNavigate } from 'react-router-dom';
import { userPassword } from 'assets/users';
import Swal from 'sweetalert2';
import { UserApi } from 'api';

const PasswordPage = () => {
    const [error, setError] = useState(null);
    const [id, setId] = useState();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ 
        currentPassword: '', 
        newPassword: '',
        confirmPassword: ''
    });
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await UserApi.getByTokenAdmin();

                if (response?.code === 1996) {
                    setId(response.result.id);
                }
            }
            catch (error) {
                console.error("Failed to fetch user:", error);
                navigate("/manage/error/404");
            }
            finally {
                setLoading(true);
            }
        };

        fetchData();
    }, [navigate]);

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        setError(null);
    };

    const handleUpdate = async (e) => {
        e.preventDefault();

        if (!formData.currentPassword || !formData.newPassword || !formData.confirmPassword) {
            setError('Vui lòng không bỏ trống!');
            return;
        }

        if (formData.newPassword !== formData.confirmPassword) {
            setError('Mật khẩu mới không khớp!');
            return;
        }

        if (formData.currentPassword === formData.newPassword) {
            setError('Mật khẩu mới phải khác mật khẩu hiện tại!');
            return;
        }

        try {
            const response = await UserApi.changePasswordAdmin(id, formData);

            if (response?.code === 1992) {
                setFormData((prev) =>
                    Object.keys(prev).reduce((acc, key) => ({ ...acc, [key]: '' }), {})
                );

                Swal.fire({
                    title: 'Thành công',
                    text: 'Thay đổi mật khẩu thành công',
                    icon: 'success',
                    confirmButtonText: 'Đóng'
                });
            } 
            else if (response?.code === 1028) {
                setError("Mật khẩu hiện tại không chính xác!");
            }
            else {
                setError(response?.message);
            }
        } catch (error) {
            setError("Đã xảy ra lỗi không xác định. Vui lòng thử lại!");
        }
    };

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="manage-password">
            <div className="avatar-container">
                <img src={userPassword} alt="avatar" className="avatar" />
            </div>
            <div className="user-details">
                <h2>Thay đổi mật khẩu</h2>
                <form onSubmit={handleUpdate} className="password-form">
                    <label htmlFor="currentPassword">Mật khẩu hiện tại</label>
                    <input type="password" id="currentPassword" name="currentPassword" value={formData.currentPassword} onChange={handleInputChange} />
                    
                    <label htmlFor="newPassword">Mật khẩu mới</label>
                    <input type="password" id="newPassword" name="newPassword" value={formData.newPassword} onChange={handleInputChange} />
                    
                    <label htmlFor="confirmPassword">Nhập lại mật khẩu</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" value={formData.confirmPassword} onChange={handleInputChange} />
                    
                    {error && <p className="error-message">{error}</p>}
                    <button type="submit" className="submit-btn">Xác nhận</button>
                </form>
            </div>
        </div>
    );
};

export default memo(PasswordPage);