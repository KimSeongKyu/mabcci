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
  const [myImage, setMyImage] = useState([]);
  const [myTag, setMyTag] = useState([]);
  const [myOOTDInfo, setMyOOTDInfo] = useState({
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
    content: '',
  });

  const addImage = e => {
    const nowSelectImageList = e.target.files;
    const nowImageURLList = [...myImage];
    for (let i = 0; i < nowSelectImageList.length; i += 1) {
      const nowImageUrl = URL.createObjectURL(nowSelectImageList[i]);
      nowImageURLList.push(nowImageUrl);
    }
    setMyImage(nowImageURLList);
  };

  const removeImage = e => {
    const nowIdx = e.target.value;
    const copyMyImage = [...myImage];
    copyMyImage.splice(nowIdx, 1);
    setMyImage(copyMyImage);
  };

  const getTags = tag => {
    setMyTag(tag);
  };

  const addOOTDInfo = e => {
    const { name, value } = e.target;
    setMyOOTDInfo({
      ...myOOTDInfo,
      [name]: value,
    });
  };

  return (
    <div className="OOTDWrite-container">
      <h5>OOTD Write</h5>

      <div>
        {myImage.length === 0 ? (
          <div className="OOTDWrite-initial-image">No images yet</div>
        ) : null}
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
          {myImage.map(function imageList(image, i) {
            return (
              <SwiperSlide>
                <div className="swiper-zoom-container" key={image}>
                  <img src={image} alt="사진을 추가해주세요" />
                  <button
                    type="submit"
                    onClick={removeImage}
                    value={i}
                    className="btn-util OOTDWrite-btn-remove"
                  >
                    X
                  </button>
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

      <div className="OOTDWrite-input-box">
        <div className="OOTDWrite-input-brand">
          <IoShirt />
          <p>Top</p>
          <input
            type="text"
            placeholder="Brand / Item"
            name="top"
            onChange={addOOTDInfo}
          />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiArmoredPants />
          <p>Bottom</p>
          <input
            type="text"
            placeholder="Brand / Item"
            name="bottom"
            onChange={addOOTDInfo}
          />
        </div>
        <div className="OOTDWrite-input-brand">
          <GiConverseShoe />
          <p>Shoes</p>
          <input
            type="text"
            placeholder="Brand / Item"
            name="shoes"
            onChange={addOOTDInfo}
          />
        </div>
        <div className="OOTDWrite-input-brand">
          <FaShoppingBag />
          <p>ACC</p>
          <input
            type="text"
            placeholder="Brand / Item"
            name="accessory"
            onChange={addOOTDInfo}
          />
        </div>
      </div>
      <div className="OOTDWrite-input-box">
        <p>Tag</p>
        <InputTags
          onTag={getTags}
          tagColor="#48c774"
          placeHolder="Press enter"
          className="OOTDWrite-hashtag-input"
        />
        {myTag.length >= 20 ? (
          <p id="OOTDWrite-warnning-tag">태그는 20개까지 작성가능합니다</p>
        ) : null}
      </div>
      <div className="OOTDWrite-input-box">
        <p>Content</p>
        <textarea
          id=""
          cols="30"
          rows="5"
          name="content"
          onChange={addOOTDInfo}
        />
      </div>
      <button type="submit" className="OOTDWrite-btn btn-rounded-sm">
        Submit
      </button>

    </div>
  );
}

export default OOTDWrite;
