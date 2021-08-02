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
    </div>
  );
}

export default OOTDWrite;
