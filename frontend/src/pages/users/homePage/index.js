import React, { useState, useEffect, memo } from 'react';
import './style.scss';
import { slide1, slide2, slide3, slide4, slide5 } from 'assets';
import { SlideShow, SlideTour, SlideGuide } from 'component/users';
import { TourApi, GuideApi } from 'services';

const slides = [slide1, slide2, slide3, slide4, slide5];

const HomePage = () => {
    const [tours, setTours] = useState([]);
    const [guides, setGuides] = useState([]);
    const [isLoading, setLoading] = useState(false);

    useEffect(() => {
        const fetchTours = async () => {
            try {
                const response = await TourApi.getTourListNew();
                setTours(response.result);
            }
            catch (error) {
                console.error("Failed to fetch tours:", error);
            }
            finally {
                setLoading(true);
            }
        };

        const fetchGuides = async () => {
            try {
                const response = await GuideApi.getByEvaluate();
                setGuides(response.result);
            }
            catch (error) {
                console.error("Failed to fetch guides:", error);
            }
            finally {
                setLoading(true);
            }
        };

        fetchTours();
        fetchGuides();
    }, []);

    if (!isLoading) {
        return (
            <div style={{height: 500}}></div>
        );
    }

    return (
        <div className="home-page">
            <div className="space" />
            
            <SlideShow slides={slides} />

            <SlideTour data={tours} itemsPerSlide={3} itemWidth={1115}/>
            
            <SlideGuide data={guides} itemsPerSlide={4} itemWidth={1120} />
        </div>
    );
};

export default memo(HomePage);