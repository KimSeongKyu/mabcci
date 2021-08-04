import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';

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

import OOTDWriteApi from '../../../../../API/OOTDAPI/OOTDWriteApi';

import InputTags from './InputTags';

SwiperCore.use([Zoom, Navigation, Pagination]);

function OOTDUpdate() {
  const location = useLocation();

  const [myOOTDInfo, setMyOOTDInfo] = useState({
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
    content: '',
    picture: [],
    hashTag: [],
  });

  useEffect(() => {
    setMyOOTDInfo({ ...myOOTDInfo, ...location.state.detail });
  }, []);

  const addImage = e => {
    const nowSelectImageList = e.target.files;
    const nowImageURLList = [...myOOTDInfo.picture];

    for (let i = 0; i < nowSelectImageList.length; i += 1) {
      const nowImageUrl = URL.createObjectURL(nowSelectImageList[i]);
      nowImageURLList.push(nowImageUrl);
    }
    setMyOOTDInfo({
      ...myOOTDInfo,
      picture: nowImageURLList,
    });
  };

  const removeImage = e => {
    const nowIdx = e.target.value;
    const copyMyImage = [...myOOTDInfo.picture];
    copyMyImage.splice(nowIdx, 1);
    setMyOOTDInfo({
      ...myOOTDInfo,
      picture: copyMyImage,
    });
  };

  const getTags = tag => {
    setMyOOTDInfo({
      ...myOOTDInfo,
      hashTag: tag,
    });
  };

  const addOOTDInfo = e => {
    const { name, value } = e.target;
    setMyOOTDInfo({
      ...myOOTDInfo,
      [name]: value,
    });
  };

  const submitOOTD = async () => {
    console.log(myOOTDInfo);
    // const res = await OOTDWriteApi(myOOTDInfo);
    // if (res.status === 200) {
    //   console.log('mock연동 성공');
    //   console.log(res.info);
    // } else {
    //   console.log('mock연동 실패');
    // }
  };

  return (
    <div className="OOTDWrite-container">
      <h5>OOTD Update</h5>

      <div>
        {myOOTDInfo.picture.length === 0 ? (
          <div className="OOTDWrite-initial-image">No image yet</div>
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
          {myOOTDInfo.picture.map(function imageList(image, i) {
            return (
              <SwiperSlide key={image}>
                <div className="swiper-zoom-container">
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
            value={myOOTDInfo.top}
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
            value={myOOTDInfo.bottom}
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
            value={myOOTDInfo.shoes}
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
            value={myOOTDInfo.accessory}
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
          value={myOOTDInfo.hashTag[0]}
        />
        {myOOTDInfo.hashTag.length >= 20 ? (
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
          value={myOOTDInfo.content}
        />
      </div>
      <button
        type="submit"
        className="OOTDWrite-btn btn-rounded-sm"
        onClick={submitOOTD}
      >
        Update
      </button>
    </div>
  );
}

export default OOTDUpdate;
