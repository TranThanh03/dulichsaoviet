import { memo, useEffect, useState } from 'react';
import './index.scss';
import { useNavigate } from 'react-router-dom';
import { userAvatar } from 'assets/users';
import Swal from 'sweetalert2';
import { UserApi } from 'api';

const ProfilePage = () => {
    const [user, setUser] = useState(null);
    const [error, setError] = useState(null);
    const [id, setId] = useState();
    const navigate = useNavigate();
    const [formData, setFormData] = useState({ fullName: '', phone: '', email: '' });
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        if (user) {
            setFormData({
                fullName: user.fullName || '',
                phone: user.phone || '',
                email: user.email || ''
            });
        }
    }, [user]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await UserApi.getByToken();
                if (response?.code === 1996) {
                    setUser(response.result);
                    setId(response.result.id);
                }
            }
            catch (error) {
                console.error("Failed to fetch user data:", error);
                navigate("/error/404");
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

    const isDataChanged = () => {
        return (
            user &&
            (formData.fullName !== user.fullName ||
            formData.phone !== user.phone ||
            formData.email !== user.email)
        );
    };

    const handleUpdate = async (e) => {
        e.preventDefault();

        if (!formData.fullName || !formData.phone || !formData.email) {
            setError('Vui lòng không bỏ trống!');
            return;
        }

        if (!isDataChanged()) {
            setError('Dữ liệu không thay đổi, không thể cập nhật!');
            return;
        }

        try {
            const response = await UserApi.update(id, formData);

            if (response.code === 1995) {
                setUser((prev) => ({ ...prev, ...formData }));
                
                Swal.fire({
                    title: 'Thành công',
                    text: 'Cập nhật thông tin thành công',
                    icon: 'success',
                    confirmButtonText: 'Đóng'
                });
            } else {
                setError(response.message);
            }
        } catch (error) {
            setError('Đã xảy ra lỗi không xác định. Vui lòng thử lại!');
        }
    };

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="user-profile">
            <div className="avatar-container">
                <img src={userAvatar} alt="avatar" className="avatar" />
            </div>
            <div className="user-details">
                <h2>Thông tin khách hàng</h2>
                <table>
                    <tbody>
                        <tr>
                            <td><strong>Mã khách hàng:</strong></td>
                            <td className="infor">{user?.userId || 'N/A'}</td>
                        </tr>
                        <tr>
                            <td><strong>Họ tên:</strong></td>
                            <td className="infor">
                                <input type="text" name="fullName" value={formData.fullName} onChange={handleInputChange} />
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Số điện thoại:</strong></td>
                            <td className="infor">
                                <input type="text" name="phone" value={formData.phone} onChange={handleInputChange} />
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Email:</strong></td>
                            <td className="infor">
                                <input type="text" name="email" value={formData.email} onChange={handleInputChange} />
                            </td>
                        </tr>
                        {error && (
                            <tr>
                                <td colSpan={2} id="error">{error}</td>
                            </tr>
                        )}
                        <tr>
                            <td colSpan={2} id="btn">
                                <button type="button" onClick={handleUpdate}>Cập nhật</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default memo(ProfilePage);