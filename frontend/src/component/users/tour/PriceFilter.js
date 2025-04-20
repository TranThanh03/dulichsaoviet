import { memo, useState, useEffect } from "react";
import Slider from "rc-slider";
import "rc-slider/assets/index.css";

const PriceFilter = ({ maxPrice = 10000000, onChangeRange }) => {
    const [priceRange, setPriceRange] = useState([0, maxPrice]);

    useEffect(() => {
        setPriceRange([0, maxPrice]);
    }, [maxPrice]);

    const handleChange = (values) => {
        setPriceRange(values);
        if (onChangeRange) {
            onChangeRange(values);
        }
    };

    return (
        <div className="widget widget-filter" data-aos="fade-up" data-aos-delay="50" data-aos-duration="1500" data-aos-offset="50">
            <h6 className="widget-title">Lọc theo giá</h6>
            <div className="price-filter-wrap">
                <Slider
                    range
                    min={0}
                    max={maxPrice}
                    step={500000}
                    value={priceRange}
                    onChange={handleChange}
                    allowCross={false}
                    trackStyle={[{ backgroundColor: "green" }]}
                    handleStyle={[
                        { borderColor: "green" },
                        { borderColor: "green" },
                    ]}
                />
                <div className="price mt-2">
                    <span>Giá: </span>
                    <input
                        type="text"
                        readOnly
                        className="form-control-plaintext"
                        value={`${priceRange[0].toLocaleString()}đ - ${priceRange[1].toLocaleString()}đ`}
                    />
                </div>
            </div>
        </div>
    );
};

export default memo(PriceFilter);