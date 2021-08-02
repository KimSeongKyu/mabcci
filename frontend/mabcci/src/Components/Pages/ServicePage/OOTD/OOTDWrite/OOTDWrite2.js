import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';

// Import Swiper styles
import 'swiper/swiper.min.css';
import 'swiper/components/effect-coverflow/effect-coverflow.min.css';
import 'swiper/components/pagination/pagination.min.css';

import './OOTDWrite2.css';

// import Swiper core and required modules
import SwiperCore, { EffectCoverflow, Pagination } from 'swiper/core';

// install Swiper modules
SwiperCore.use([EffectCoverflow, Pagination]);

function OOTDWrite2() {
  return (
    <div className="OOTDWrite-container">
      <Swiper
        effect="coverflow"
        grabCursor
        centeredSlides
        slidesPerView="auto"
        coverflowEffect={{
          rotate: 50,
          stretch: 0,
          depth: 100,
          modifier: 1,
          slideShadows: true,
        }}
        pagination
        className="mySwiper"
      >
        <SwiperSlide>
          <img
            src="https://swiperjs.com/demos/images/nature-1.jpg"
            alt="ㅎㅇ"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            src="https://swiperjs.com/demos/images/nature-2.jpg"
            alt="ㅎㅇ"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            src="https://swiperjs.com/demos/images/nature-3.jpg"
            alt="ㅎㅇ"
          />
        </SwiperSlide>
        <SwiperSlide>
          <img
            src="https://swiperjs.com/demos/images/nature-4.jpg"
            alt="ㅎㅇ"
          />
        </SwiperSlide>
      </Swiper>
      <input type="file" />
    </div>
  );
}

export default OOTDWrite2;
