import React, { useState, useEffect } from "react";
import Swal from "sweetalert2";
import "./index.scss";
import { UserApi } from "services";
import formatDatetime from "utils/formatDatetime";

const UserPage = () => {
    const [users, setUsers] = useState([]);
    const [search, setSearch] = useState("");
    const [showAddForm, setShowAddForm] = useState(false);
    const [showEditForm, setShowEditForm] = useState(false);
    const [editUser, setEditUser] = useState(null);
    const [errorMsgAdd, setErrorMsgAdd] = useState("");
    const [errorMsgEdit, setErrorMsgEdit] = useState("");
    const [currentPage, setCurrentPage] = useState(0);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 6;
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchUsers = async () => {
            try {
                const response = await UserApi.getAll({ page: currentPage, size: pageSize });

                if (response?.code === 1998) {
                    setUsers(response.result.content);
                    setTotalPages(response.result.totalPages);
                }
            }
            catch (error) {
                console.error("Failed to fetch users: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchUsers();
    }, [currentPage]);

    const handleDelete = async (id, userId) => {
        const confirm = await Swal.fire({
            title: "Xác nhận",
            html: `Bạn có chắc chắn xóa khách hàng <b>${userId}</b> không?`,
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Có",
            cancelButtonText: "Không",
        });

        if (confirm.isConfirmed) {
            try {
                const response = await UserApi.delete(id);

                if (response?.code === 1994) {
                    setUsers(users.filter(user => user.id !== id));
                    Swal.fire("Thành công", "Xóa khách hàng thành công", "success");
                }
                else if (response?.code === 1026) {
                    Swal.fire("Lỗi", "Khách hàng này đang có lịch đặt!", "error");
                }
                else {
                    Swal.fire("Lỗi", "Không thể xóa khách hàng", "error");
                }
            } catch (error) {
                Swal.fire("Lỗi", "Không thể xóa khách hàng", "error");
            }
        }
    };

    const handleAddUser = async (e) => {
        e.preventDefault();
        setErrorMsgAdd("");
        const formData = new FormData(e.target);
        
        try {
            const response = await UserApi.create(Object.fromEntries(formData));

            if (response?.code === 1999) {
                setUsers([...users, response.result]);
                setShowAddForm(false);
                Swal.fire("Thành công", "Thêm khách hàng thành công", "success");
            } else {
                setErrorMsgAdd(response?.message);
            }
        } catch (error) {
            setErrorMsgAdd("Đã xảy ra lỗi khi thêm khách hàng");
        }
    };

    const handleEditUser = async (e) => {
        e.preventDefault();
        
        setErrorMsgEdit("");
        const formData = new FormData(e.target);
        
        try {
            const response = await UserApi.updateAdmin(editUser.id, Object.fromEntries(formData));
           
            if (response?.code === 1995) {
                setUsers(users.map(user => user.id === editUser.id ? response.result : user));
                setShowEditForm(false);
                Swal.fire("Thành công", "Cập nhật khách hàng thành công", "success");
            } else {
                setErrorMsgEdit(response?.message);
            }
        } catch (error) {
            setErrorMsgEdit("Đã xảy ra lỗi khi cập nhật khách hàng");
        }
    };

    const handleEdit = (user) => {
        setEditUser(user);
        setShowEditForm(true);
    };

    const filteredUsers = users.filter(user => 
        user.fullName?.toLowerCase().includes(search.toLowerCase()) ||
        user.userId?.toLowerCase().includes(search.toLowerCase()) ||
        user.phone?.includes(search) ||
        user.email?.toLowerCase().includes(search.toLowerCase())
    );

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="user-manage-page">
            <h2 id="title">Danh sách khách hàng</h2>
            <div className="control">
                <button onClick={() => setShowAddForm(true)}>Thêm</button>
                <form onSubmit={(e) => e.preventDefault()}>
                    <input
                        type="search"
                        placeholder="Nhập mã, tên, sdt, email khách hàng"
                        value={search}
                        onChange={(e) => setSearch(e.target.value)}
                    />
                    <button type="submit">Tìm</button>
                </form>
            </div>
            <div className="main">
                <table>
                    <thead>
                        <tr>
                            <th>STT</th>
                            <th>Mã khách hàng</th>
                            <th>Tên khách hàng</th>
                            <th>Số điện thoại</th>
                            <th>Email</th>
                            <th>Thời gian đăng ký</th>
                            <th>Thao tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        {filteredUsers.length > 0 ? (
                            filteredUsers.map((user, index) => (
                                <tr key={index}>
                                    <td>{index + 1}</td>
                                    <td>{user.userId}</td>
                                    <td>{user.fullName}</td>
                                    <td>{user.phone}</td>
                                    <td>{user.email}</td>
                                    <td>{formatDatetime(user.registerTime)}</td>
                                    <td>
                                        <button onClick={() => handleEdit(user)}>Sửa</button>
                                        <button 
                                            id="btn-delete" 
                                            onClick={() => handleDelete(user.id, user.userId)}
                                        >
                                            Xóa
                                        </button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="7">Không có dữ liệu</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>

            <div className="pagination">
                <button disabled={currentPage === 0} onClick={() => setCurrentPage(currentPage - 1)}>
                    &lt;
                </button>
                <span>Trang {currentPage + 1} / {totalPages}</span>
                <button disabled={currentPage >= totalPages - 1} onClick={() => setCurrentPage(currentPage + 1)}>
                    &gt;
                </button>
            </div>
            
            {showAddForm && (
                <div className="form-input">
                    <div className="content">
                        <h2>Thêm khách hàng</h2>
                        <button id="btn-close" onClick={() => {
                                setShowAddForm(false);
                                setErrorMsgAdd("");
                            }}>X</button>
                        <form onSubmit={handleAddUser}>
                            <label>Tên khách hàng</label>
                            <input type="text" name="fullName" required />
                            <label>Số điện thoại</label>
                            <input type="tel" name="phone" pattern="[0-9]{10}" required />
                            <label>Email</label>
                            <input type="email" name="email" required />
                            <label>Mật khẩu</label>
                            <input type="password" name="password" required />
                            <p id="msg-error">{errorMsgAdd}</p>
                            <button id="btn-submit" type="submit">Thêm</button>
                        </form>
                    </div>
                </div>
            )}

            {showEditForm && (
                <div className="form-input">
                    <div className="content">
                        <h2>Chỉnh sửa khách hàng</h2>
                        <button id="btn-close" onClick={() => {
                                setShowEditForm(false);
                                setErrorMsgEdit("");
                            }}>X</button>
                        <form onSubmit={handleEditUser}>
                            <label>Tên khách hàng</label>
                            <input type="text" name="fullName" defaultValue={editUser.fullName} required />
                            <label>Số điện thoại</label>
                            <input type="tel" name="phone" pattern="[0-9]{10}" defaultValue={editUser.phone} required />
                            <label>Email</label>
                            <input type="email" name="email" defaultValue={editUser.email} required />
                            <p id="msg-error">{errorMsgEdit}</p>
                            <button id="btn-submit" type="submit">Cập nhật</button>
                        </form>
                    </div>
                </div>
            )}
        </div>
    );
};

export default UserPage;