import React, { useState, useEffect } from 'react';
import { useLocation, useParams, useHistory } from 'react-router-dom';
import './OOTDWrite.css';

import { Swiper, SwiperSlide } from 'swiper/react';

import 'swiper/swiper.min.css';
import 'swiper/components/zoom/zoom.min.css';
import 'swiper/components/navigation/navigation.min.css';
import 'swiper/components/pagination/pagination.min.css';

import SwiperCore, { Zoom, Navigation, Pagination } from 'swiper/core';

import { IoShirt } from 'react-icons/io5';
import { GiArmoredPants, GiConverseShoe } from 'react-icons/gi';
import { FaShoppingBag } from 'react-icons/fa';

import OOTDUpdateApi from '../../../../../API/OOTDAPI/OOTDUpdateApi';
import { baseUrl } from '../../../../../API/ApiUrl';

SwiperCore.use([Zoom, Navigation, Pagination]);

function OOTDUpdate() {
  const history = useHistory();
  const location = useLocation();
  const { id, nickname } = useParams();
  const [myOOTDInfo, setMyOOTDInfo] = useState({
    id,
    top: null,
    bottom: null,
    shoes: null,
    accessory: null,
    content: null,
    picture: [],
    hashTag: [],
  });

  useEffect(async () => {
    await setMyOOTDInfo(location.state.info);
  }, []);

  const addOOTDInfo = e => {
    const { name, value } = e.target;
    setMyOOTDInfo({
      ...myOOTDInfo,
      [name]: value,
    });
  };

  const submitOOTD = async () => {
    const myUpdateInfo = {
      top: myOOTDInfo.top,
      bottom: myOOTDInfo.bottom,
      shoes: myOOTDInfo.shoes,
      accessory: myOOTDInfo.accessory,
      content: myOOTDInfo.content,
    };
    const res = await OOTDUpdateApi(myOOTDInfo.id, myUpdateInfo);
    if (res.status === 204) {
      window.location.replace(`/OOTD/${id}/${nickname}`);
    }
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
                  <img src={baseUrl + image} alt="사진을 추가해주세요" />
                </div>
              </SwiperSlide>
            );
          })}
        </Swiper>
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
