import './OOTDWrite.css';

import React, { useRef, useState } from 'react';
// Import Swiper React components
import { Swiper, SwiperSlide } from 'swiper/react';

// Import Swiper styles
import 'swiper/swiper.min.css';
import 'swiper/components/zoom/zoom.min.css';
import 'swiper/components/navigation/navigation.min.css';
import 'swiper/components/pagination/pagination.min.css';

// import Swiper core and required modules
import SwiperCore, { Zoom, Navigation, Pagination } from 'swiper/core';

// install Swiper modules
SwiperCore.use([Zoom, Navigation, Pagination]);

// const [myImage, setMyImage] = useState({});

function OOTDWrite() {
  return (
    <div className="OOTDWrite-container">
      <h2>OOTDWrite페이지입니다.</h2>
      <div className="Photo-container">
        <Swiper
          style={{
            '--swiper-navigation-color': '#f9a77c',
            '--swiper-pagination-color': '#f9a77c',
          }}
          zoom
          navigation
          pagination={{
            clickable: true,
          }}
          className="mySwiper"
        >
          <SwiperSlide>
            <div className="swiper-zoom-container">
              <img
                src="https://swiperjs.com/demos/images/nature-1.jpg"
                alt="하이"
              />
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="swiper-zoom-container">
              <img
                src="https://swiperjs.com/demos/images/nature-2.jpg"
                alt="하이"
              />
            </div>
          </SwiperSlide>
          <SwiperSlide>
            <div className="swiper-zoom-container">
              <img
                src="https://swiperjs.com/demos/images/nature-3.jpg"
                alt="하이"
              />
            </div>
          </SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
}

export default OOTDWrite;
