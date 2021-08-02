import React, { useState } from 'react';

import './OOTDWrite.css';

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

import InputTags from './InputTags';

SwiperCore.use([Zoom, Navigation, Pagination]);

function OOTDWrite() {
  const [myImage, setMyImage] = useState(['']);

  const addImage = e => {
    const nowSelectImage = e.target.value;
    setMyImage([...myImage, nowSelectImage]);
    e.target.value = '';
  };

  const [tags, setTags] = useState([]);

  const getTags = tag => {
    console.log(tag, '개별');
    if (tag.length > 0) {
      setTags(tag.slice(0, tag.length - 1));
    }
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
                  <img src={image} alt="사진을 추가해주세요" />
                </div>
              </SwiperSlide>
            );
          })}
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
          <input type="text" placeholder="Brand / Item" />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiArmoredPants />
          <p>Bottom</p>
          <input type="text" placeholder="Brand / Item" />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiConverseShoe />
          <p>Shoes</p>
          <input type="text" placeholder="Brand / Item" />
        </div>
        <div className="OOTDWrite-input-brand">
          <FaShoppingBag />
          <p>ACC</p>
          <input type="text" placeholder="Brand / Item" />
        </div>
      </div>
      <div className="OOTDWrite-input-box">
        <InputTags
          onTag={getTags}
          tagColor="#48c774"
          placeHolder="Press enter"
          className="OOTDWrite-hashtag-input"
        />
      </div>
      <div className="OOTDWrite-input-box">
        <textarea name="" id="" cols="30" rows="5" placeholder="Content" />
      </div>
      <button type="submit" className="OOTDWrite-btn btn-rounded-sm">
        Submit
      </button>
    </div>
  );
}

export default OOTDWrite;
