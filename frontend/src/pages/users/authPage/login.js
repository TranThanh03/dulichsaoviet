import { memo, useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import './login.scss';
import { logo } from 'assets/users';
import { AuthApi } from 'api';

const LoginPage = () => {
    const [formData, setFormData] = useState({ username: '', password: '' });
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({ ...prev, [name]: value }));
        setErrorMessage('');
    };

    const handleLogin = async (e) => {
        e.preventDefault();
        
        if (!formData.username || !formData.password) {
            setErrorMessage('Vui lòng nhập đầy đủ tài khoản và mật khẩu!');
            return;
        }

        try {
            const response = await AuthApi.login(formData);

            if (response?.code === 9999) {
                navigate('/');
            } else {
                setErrorMessage(response.message);
            }
        } catch (error) {
            setErrorMessage('Đã xảy ra lỗi không xác định. Vui lòng thử lại!');
        }
    };

    return (
        <div className='login-page'>
            <div className="container">
                <div className="left-box">
                    <div className="featured-image">
                        <img src={logo} alt="Logo" />
                    </div>
                    <p className="title">Sao Việt</p>
                    <small className="subtitle">Uy tín tạo nên sự tin tưởng</small>
                </div>

                <div className="right-box">
                    <div className="header-text">
                        <h2>Xin chào</h2>
                        <p>Chúng tôi rất vui vì bạn đã trở lại</p>
                    </div>

                    <form onSubmit={handleLogin}>
                        <div className="input-group">
                            <label htmlFor="username"><b>Tài khoản</b></label>
                            <input
                                type="text"
                                id="username"
                                name="username"
                                placeholder="SDT hoặc Email"
                                value={formData.username}
                                onChange={handleInputChange}
                                autoFocus
                            />
                        </div>

                        <div className="input-group">
                            <label htmlFor="password"><b>Mật khẩu</b></label>
                            <input
                                type="password"
                                id="password"
                                name="password"
                                placeholder="******"
                                value={formData.password}
                                onChange={handleInputChange}
                            />
                        </div>
        
                        <div className="error-message">
                            {errorMessage}
                        </div>

                        <div className="submit-group">
                            <button type="submit">Đăng nhập</button>
                        </div>
                    </form>

                    <div className="footer-links">
                        <div className="register">
                            <small>Khách hàng mới? <Link to="/auth/register">Tạo tài khoản</Link></small>
                        </div>
                        <div className="back">
                            <small><Link to="/">Quay về</Link></small>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default memo(LoginPage);