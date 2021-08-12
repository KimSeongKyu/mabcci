/* eslint-disable */
import React, { useState, useEffect } from 'react';
import { useHistory } from 'react-router-dom';

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

function OOTDWrite() {
  const history = useHistory();

  const [myOOTDInfo, setMyOOTDInfo] = useState({
    nickname: '',
    top: '',
    bottom: '',
    shoes: '',
    accessory: '',
    content: '',
    originPictures: [],
    pictures: [],
    hashTags: [],
  });

  useEffect(() => {
    const myInfo = JSON.parse(localStorage.getItem('userInfo'));
    setMyOOTDInfo({
      ...myOOTDInfo,
      nickname: myInfo.nickname,
    });
  }, []);

  const addImage = e => {
    const nowSelectImageList = e.target.files;
    const nowImageURLList = [...myOOTDInfo.pictures];
    const nowOriginImage = [...myOOTDInfo.originPictures];

    for (let i = 0; i < nowSelectImageList.length; i += 1) {
      const nowImageUrl = URL.createObjectURL(nowSelectImageList[i]);
      console.log(nowImageUrl);
      nowImageURLList.push(nowImageUrl);
      nowOriginImage.push(nowSelectImageList[i]);
    }
    setMyOOTDInfo({
      ...myOOTDInfo,
      pictures: nowImageURLList,
      originPictures: nowOriginImage,
    });
    e.target.value = '';
  };

  const removeImage = e => {
    const nowIdx = e.target.value;
    const copyMyImage = [...myOOTDInfo.pictures];
    const copyMyOriginImage = [...myOOTDInfo.originPictures];

    copyMyImage.splice(nowIdx, 1);
    copyMyOriginImage.splice(nowIdx, 1);

    setMyOOTDInfo({
      ...myOOTDInfo,
      pictures: copyMyImage,
      originPictures: copyMyOriginImage,
    });
  };

  const getTags = tag => {
    setMyOOTDInfo({
      ...myOOTDInfo,
      hashTags: tag,
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
    if (myOOTDInfo.content.length === 0 || myOOTDInfo.pictures.length === 0) {
      alert('image와 content는 필수입력 사항입니다!');
    } else {
      const data = new FormData();
      for (let i = 0; i < myOOTDInfo.originPictures.length; i += 1) {
        const images = myOOTDInfo.originPictures[i];
        data.append('pictures', images);
      }
      data.append('top', myOOTDInfo.top);
      data.append('nickname', myOOTDInfo.nickname);
      data.append('bottom', myOOTDInfo.bottom);
      data.append('hashtags', myOOTDInfo.hashTags);
      data.append('content', myOOTDInfo.content);
      data.append('shoes', myOOTDInfo.shoes);
      data.append('accessory', myOOTDInfo.accessory);

      const res = await OOTDWriteApi(data);
      if (res.status === 204) {
        history.push('/OOTD');
      } else {
        console.log(res.status);
      }
    }
  };

  return (
    <div className="OOTDWrite-container">
      {/* <OOTDWrite hashTag={myOOTDInfo.hashTag} /> */}

      <h5>OOTD Write</h5>

      <div>
        {myOOTDInfo.pictures.length === 0 ? (
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
          {myOOTDInfo.pictures.map(function imageList(image, i) {
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
        {myOOTDInfo.hashTags.length >= 20 ? (
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
      <button
        type="submit"
        className="OOTDWrite-btn btn-rounded-sm"
        onClick={submitOOTD}
      >
        Submit
      </button>
    </div>
  );
}

export default OOTDWrite;
