import { useEffect, useState } from "react";
import { PieChart, Pie, Cell, Tooltip, LineChart, Line, XAxis, YAxis, CartesianGrid, ResponsiveContainer } from "recharts";
import "./index.scss";
import formatCurrency from "utils/formatCurrency";
import { OrderApi } from "api";

const DashboardPage = () => {
    const [isLoading, setLoading] = useState(false);
    
    const [pieData, setPieData] = useState([
        { name: "Đang xử lý", value: 0, color: "#FFD700" },
        { name: "Đã xác nhận", value: 0, color: "#00C853" },
        { name: "Đã hủy", value: 0, color: "#FF3D00" },
    ]);

    const [lineData, setLineData] = useState([
        { month: "Tháng 1", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 2", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 3", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 4", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 5", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 6", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 7", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 8", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 9", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 10", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 11", revenue: null, canceled: null, confirmed: null },
        { month: "Tháng 12", revenue: null, canceled: null, confirmed: null },
        { month: ""},
    ]);

    useEffect(() => {
        const fetchOrdersStatus = async () => {
            try {
                const response = await OrderApi.statusCounts();

                if (response?.code === 2959 && response?.result) {
                    setPieData((prevData) =>
                        prevData.map((item) => {
                            if (item.name === "Đang xử lý") return { ...item, value: response.result.processing };
                            if (item.name === "Đã xác nhận") return { ...item, value: response.result.confirmed };
                            if (item.name === "Đã hủy") return { ...item, value: response.result.canceled };
                            
                            return item;
                        })
                    );
                }
            }
            catch(error) {
                console.error("Failed to fetch data: ", error);
            }
            finally {
                setLoading(true);
            }
        }

        const fetchOrdersStatistics = async () => {
            try {
                const res = await OrderApi.getStatistics();

                if (res?.code === 2958 && res?.result) {
                    setLineData((prevData) =>
                        prevData.map((item) => {
                            const found = res.result.find((data) => data.month === Number(item.month.replace("Tháng ", "")));
                            
                            return found
                                ? { ...item, revenue: found.revenue, canceled: found.canceled, confirmed: found.confirmed }
                                : item;
                        })
                    );
                }
            } 
            catch (error) {
                console.error("Failed to fetch statistics: ", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchOrdersStatus();
        fetchOrdersStatistics();
    }, [])

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="dashboard-container-page">
            <h2 id="title">Thống kê chi tiết</h2>
            <div className="charts-container">
                <div className="chart">
                    <h3 className="chart-title">Thống kê lịch đặt:</h3>
                    <div id="pie-chart">
                        <PieChart width={350} height={350}>
                            <Pie
                                data={pieData}
                                cx="50%"
                                cy="50%"
                                outerRadius={100}
                                fill="#8884d8"
                                dataKey="value"
                                label
                            >
                                {pieData.map((entry, index) => (
                                    <Cell key={`cell-${index}`} fill={entry.color} />
                                ))}
                            </Pie>
                            <Tooltip />
                        </PieChart>
                        
                        <div className="legend">
                            {pieData.map((entry, index) => (
                                <div key={index} className="legend-item">
                                    <span className="legend-color" style={{ backgroundColor: entry.color }}></span>
                                    {entry.name}
                                </div>
                            ))}
                        </div>
                    </div>
                </div>

                <div className="chart">
                    <h3 className="chart-title">Thống kê doanh thu:</h3>
                    <ResponsiveContainer width="100%" height={400}>
                        <LineChart data={lineData}>
                            <CartesianGrid strokeDasharray="3 3" />
                            <XAxis dataKey="month" interval={0} minTickGap={10} />
                            <YAxis
                                tickFormatter={(value) => `${value / 1000000}`}
                                label={{ value: "Triệu VND", angle: -90, position: "insideLeft" }}
                            />
                            
                            <Tooltip 
                                formatter={(value, name) => {
                                    if (name === "revenue") return [formatCurrency(value), "Doanh thu"];
                                    if (name === "confirmed") return [`${value} lịch`, "Lịch đặt xác nhận"];
                                    if (name === "canceled") return [`${value} lịch`, "Lịch đặt hủy"];
                                    return value;
                                }}
                                labelFormatter={(label) => <strong>{label}</strong>}
                            />

                            <Line type="monotone" dataKey="revenue" stroke="#8884d8" strokeWidth={2} dot={{ r: 4 }} />
                            <Line type="monotone" dataKey="confirmed" stroke="#4CAF50" strokeWidth={2} dot={{ r: 4 }} />
                            <Line type="monotone" dataKey="canceled" stroke="#F44336" strokeWidth={2} dot={{ r: 4 }} />
                        </LineChart>
                    </ResponsiveContainer>
                </div>
            </div>
        </div>
    );
};

export default DashboardPage;