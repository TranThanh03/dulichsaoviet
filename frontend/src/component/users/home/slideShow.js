import React, { useState, useEffect, memo } from 'react';
import PropTypes from 'prop-types';
import './slideShow.scss';

const SlideShow = ({ slides }) => {
    const [slideIndex, setSlideIndex] = useState(1);

    useEffect(() => {
        const interval = setInterval(() => {
            plusSlides(1);
        }, 3000);

        return () => clearInterval(interval);
    }, [slideIndex]);

    const plusSlides = (n) => {
        setSlideIndex((prevIndex) => (prevIndex + n - 1 + slides.length) % slides.length + 1);
    };

    const currentSlide = (n) => {
        setSlideIndex(n);
    };

    return (
        <div className="slideshow-container">
            {slides.map((slide, index) => (
                <div
                    key={index}
                    className={`mySlides fade ${slideIndex === index + 1 ? 'active' : ''}`}
                    style={{ display: slideIndex === index + 1 ? 'block' : 'none' }}
                >
                    <img src={slide} alt={`Slide ${index + 1}`} style={{ width: '100%' }} />
                </div>
            ))}

            <a className="prev" onClick={() => plusSlides(-1)}>
                &#10094;
            </a>
            <a className="next" onClick={() => plusSlides(1)}>
                &#10095;
            </a>

            <div style={{ textAlign: 'center' }}>
                {slides.map((_, index) => (
                    <span
                        key={index}
                        className={`dot ${slideIndex === index + 1 ? 'active' : ''}`}
                        onClick={() => currentSlide(index + 1)}
                    ></span>
                ))}
            </div>
        </div>
    );
};

SlideShow.propTypes = {
    slides: PropTypes.arrayOf(PropTypes.string).isRequired,
};

export default memo(SlideShow);