import React, { useState, useEffect } from 'react';
import './MakeSuggestion.css';

import { Swiper, SwiperSlide } from 'swiper/react';
import 'swiper/swiper.min.css';
import 'swiper/components/zoom/zoom.min.css';
import 'swiper/components/navigation/navigation.min.css';
import 'swiper/components/pagination/pagination.min.css';
import SwiperCore, { Zoom, Navigation, Pagination } from 'swiper/core';

import { GrGallery } from 'react-icons/gr';
import { IoShirt } from 'react-icons/io5';
import { GiArmoredPants, GiConverseShoe } from 'react-icons/gi';
import { FaShoppingBag } from 'react-icons/fa';

SwiperCore.use([Zoom, Navigation, Pagination]);

const MakeSuggestion = () => {
  const [suggestion, setSuggestion] = useState({
    memberId: '',
    mabcciId: '',
    description: '',
    top: '',
    bottom: '',
    shoes: '',
    acc: '',
  });

  return (
    <div className="makeSuggestion-container">
      <h5>제안서 작성하기</h5>

      <div>
        <Swiper
          zoom
          navigation
          pagination={{
            clickable: true,
          }}
          className="mySwiper"
        >
          <SwiperSlide>TEST1</SwiperSlide>
          <SwiperSlide>TEST2</SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
};

export default MakeSuggestion;
