import React from 'react';
import { Swiper, SwiperSlide } from 'swiper/react';
import DescriptionPage1 from './DescriptionPage1';
import DescriptionPage2 from './DescriptionPage2';
import DescriptionPage3 from './DescriptionPage3';
import 'swiper/swiper.scss';

const Description = () => {
  return (
    <div className>
      <Swiper className="mySwiper desc-container">
        <SwiperSlide>
          <DescriptionPage1 />
        </SwiperSlide>
        <SwiperSlide>
          <DescriptionPage2 />
        </SwiperSlide>
        <SwiperSlide>
          <DescriptionPage3 />
        </SwiperSlide>
      </Swiper>
    </div>
  );
};

export default Description;
