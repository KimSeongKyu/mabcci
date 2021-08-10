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

const imageProcess = cloth => {
  return cloth ? (
    <div className="makeSuggestion-swiper-container">
      <img src="test" alt="사진을 추가해주세요" />
      <button type="submit" className="btn-util OOTDWrite-btn-remove">
        X
      </button>
    </div>
  ) : (
    <div className="makeSuggestion-swiper-container-noimage">
      <div className="makeSuggestion-initial-image">No image yet</div>
      <div>
        <label htmlFor="input-file" className="OOTDWrite-input-file">
          <GrGallery />
          Add your photo
          <input
            type="file"
            multiple="multiple"
            id="input-file"
            style={{ display: 'none' }}
            accept=".jpg,.jpeg,.png"
          />
        </label>
      </div>
    </div>
  );
};

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
          style={{
            '--swiper-navigation-color': '#f9a77c',
            '--swiper-pagination-color': '#f9a77c',
          }}
          zoom
          navigation
          pagination={{
            clickable: true,
          }}
          className="makeSuggestion-swiper"
        >
          <SwiperSlide>
            <IoShirt size="30" className="makeSuggestion-swiper-icon" />
            {imageProcess(suggestion.top)}
          </SwiperSlide>
          <SwiperSlide>
            <GiArmoredPants size="30" className="makeSuggestion-swiper-icon" />
            {imageProcess(suggestion.bottom)}
          </SwiperSlide>
          <SwiperSlide>
            <GiConverseShoe size="30" className="makeSuggestion-swiper-icon" />
            {imageProcess(suggestion.shoes)}
          </SwiperSlide>
          <SwiperSlide>
            <FaShoppingBag size="30" className="makeSuggestion-swiper-icon" />
            {imageProcess(suggestion.acc)}
          </SwiperSlide>
        </Swiper>
      </div>
    </div>
  );
};

export default MakeSuggestion;
