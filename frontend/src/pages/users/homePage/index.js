import React, { useState, useEffect, memo } from 'react';
import './style.scss';
import { slide1, slide2, slide3, slide4, slide5 } from 'assets';
import SlideShow from 'component/users/home/SlideShow';
import SearchForm from 'component/users/home/SearchForm';

const slides = [slide1, slide2, slide3, slide4, slide5];

const HomePage = () => {
    return (
        <div className="home-page">
            <SlideShow slides={slides} interval={3000} />

            <SearchForm />

        </div>
    );
};

export default memo(HomePage);