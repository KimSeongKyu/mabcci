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
import { IconBase } from 'react-icons/lib';

SwiperCore.use([Zoom, Navigation, Pagination]);

const imageProcess = (cloth, clothImage, addImage) => {
  return clothImage.length !== 0 ? (
    <div className="makeSuggestion-swiper-container">
      <img src={clothImage[0]} alt="사진을 추가해주세요" />
      <button type="submit" className="btn-util makeSuggestion-btn-remove">
        X
      </button>
    </div>
  ) : (
    <div className="makeSuggestion-swiper-container-noimage">
      <div className="makeSuggestion-initial-image">No image yet</div>
      <label htmlFor="input-file" className="makeSuggestion-input-file">
        <GrGallery />
        Add your photo {cloth}
        <input
          name={cloth}
          key={cloth}
          type="file"
          id="input-file"
          style={{ display: 'none' }}
          accept=".jpg,.jpeg,.png"
          onChange={e => {
            addImage(e);
          }}
        />
      </label>
    </div>
  );
};

const MakeSuggestion = () => {
  const clothes = ['top', 'bottom', 'shoes', 'acc'];
  const clothIcon = [IoShirt, GiArmoredPants, GiConverseShoe, FaShoppingBag];
  const [suggestion, setSuggestion] = useState({
    memberId: '',
    mabcciId: '',
    description: '',
    top: [] /* [ImageURL, originImageURL] */,
    bottom: [],
    shoes: [],
    acc: [],
  });
  const [curSilde, setCurSlide] = useState(0);

  const addImage = e => {
    console.log(`curSlide${curSilde}`);
    const cloth = clothes[curSilde];
    const nowSelectImage = e.target.files[0];
    const ImageUrl = URL.createObjectURL(nowSelectImage);
    const OriginImageUrl = nowSelectImage;

    setSuggestion({
      ...suggestion,
      [cloth]: [ImageUrl, OriginImageUrl],
    });
    e.target.value = '';
  };

  return (
    <div className="makeSuggestion-container">
      <h5>제안서 작성하기</h5>
      <div>
        <Swiper
          className="makeSuggestion-swiper"
          style={{
            '--swiper-navigation-color': '#f9a77c',
            '--swiper-pagination-color': '#f9a77c',
          }}
          zoom
          navigation
          pagination={{
            clickable: true,
          }}
          onSlideChange={swiper => setCurSlide(swiper.activeIndex)}
        >
          {clothes.map((cloth, index) => {
            const Icon = clothIcon[index];
            return (
              <SwiperSlide key={cloth}>
                <Icon size="30" className="makeSuggestion-swiper-icon" />
                {imageProcess(cloth, suggestion[cloth], addImage)}
              </SwiperSlide>
            );
          })}
        </Swiper>
      </div>
    </div>
  );
};

export default MakeSuggestion;
