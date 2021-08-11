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

SwiperCore.use([Zoom, Navigation]);

const imageProcess = (cloth, clothImage, addImage, removeImage) => {
  return clothImage.length !== 0 ? (
    <div className="makeSuggestion-swiper-container">
      <img src={clothImage[0]} alt="사진을 추가해주세요" />
      <button
        type="submit"
        className="btn-util makeSuggestion-btn-remove"
        onClick={removeImage}
      >
        X
      </button>
    </div>
  ) : (
    <div className="makeSuggestion-swiper-container-noimage">
      <div className="makeSuggestion-initial-image">No image yet</div>
      <label htmlFor="input-file" className="makeSuggestion-input-file">
        <GrGallery />
        Add {cloth} photo
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

  const removeImage = e => {
    const cloth = clothes[curSilde];

    setSuggestion({
      ...suggestion,
      [cloth]: [],
    });
  };

  const addDescription = e => {
    setSuggestion({
      ...suggestion,
      description: e.target.value,
    });
  };

  const submitSuggention = () => {
    console.log(suggestion);
  };

  return (
    <div className="makeSuggestion-container">
      <h4>제안서 작성하기</h4>
      <div className="makeSuggestion-suggestion">
        <div>
          <Swiper
            className="makeSuggestion-swiper"
            style={{
              '--swiper-navigation-color': '#f9a77c',
            }}
            zoom
            navigation
            onSlideChange={swiper => setCurSlide(swiper.activeIndex)}
          >
            {clothes.map((cloth, index) => {
              const Icon = clothIcon[index];
              return (
                <SwiperSlide key={cloth}>
                  <Icon size="30" className="makeSuggestion-swiper-icon" />
                  {imageProcess(
                    cloth,
                    suggestion[cloth],
                    addImage,
                    removeImage,
                  )}
                </SwiperSlide>
              );
            })}
          </Swiper>
        </div>
        <div className="makeSuggestion-input-box">
          <h5>Content</h5>
          <textarea
            id=""
            cols="30"
            rows="5"
            name="content"
            placeholder="설계한 스타일을 설명해주세요."
            onChange={addDescription}
          />
        </div>
      </div>
      <button
        type="submit"
        className="makeSuggestion-btn btn-rounded-sm"
        onClick={submitSuggention}
      >
        Submit
      </button>
    </div>
  );
};

export default MakeSuggestion;
