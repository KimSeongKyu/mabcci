import './OOTDWrite.css';

import React, { useState } from 'react';

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

function OOTDWrite() {
  const [myImage, setMyImage] = useState([]);

  const addImage = e => {
    const nowSelectImage = e.target.value;
    setMyImage([...myImage, nowSelectImage]);
    e.target.value = '';
  };
  return (
    <div className="OOTDWrite-container">
      <h5>OOTD Write</h5>

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
          {myImage.map(function imageList(image) {
            return (
              <SwiperSlide>
                <div className="swiper-zoom-container">
                  <img src={image} alt="하이" />
                </div>
              </SwiperSlide>
            );
          })}
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
      <div>
        <label
          htmlFor="input-file"
          className="OOTDWrite-input-file"
          onChange={addImage}
        >
          <GrGallery />
          + Photo
          <input
            type="file"
            id="input-file"
            style={{ display: 'none' }}
            accept=".jpg,.jpeg,.png"
          />
        </label>
      </div>

      <div className="OOTDWrite-input-box">
        <div className="OOTDWrite-input-brand">
          <IoShirt />
          <p>top</p>
          <input type="text" placeholder="내용입력" />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiArmoredPants />
          <p>Bottom</p>
          <input type="text" placeholder="내용입력" />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiConverseShoe />
          <p>Shoes</p>
          <input type="text" placeholder="내용입력" />
        </div>
        <div className="OOTDWrite-input-brand">
          <FaShoppingBag />
          <p>ACC</p>
          <input type="text" placeholder="내용입력" />
        </div>
      </div>
      <div className="OOTDWrite-input-box">
        <input
          type="text"
          placeholder="태그입력"
          className="OOTDWrite-hash-input"
        />
      </div>
      <div className="OOTDWrite-input-box">
        <textarea
          name=""
          id=""
          cols="30"
          rows="5"
          placeholder="내용을 입력해주세요"
        />
      </div>
    </div>
  );
}

export default OOTDWrite;
