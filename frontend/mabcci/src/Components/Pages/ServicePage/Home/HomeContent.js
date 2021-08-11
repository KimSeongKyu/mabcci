import React, { useState, useEffect } from 'react';
import { RiArrowLeftSLine, RiArrowRightSLine } from 'react-icons/ri';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation } from 'swiper/core';
import 'swiper/swiper.min.css';
import 'swiper/components/navigation/navigation.min.css';
import PopularMabcciApi from '../../../../API/MabcciAPI/PopularMabcciApi';
import userphoto from './Images/userphoto.png';

SwiperCore.use([Navigation]);

const mabcciListPrint = (mabcciList, type) => {
  const className = `home-mabcci-${type}`;

  return mabcciList.map(mabcci => {
    return (
      <div className={className} key={mabcci.nickName}>
        <img
          className="home-mabcci-photo"
          src={mabcci.picture}
          alt="mabcciPhoto"
          width="100"
        />
        <h5>{mabcci.nickName}</h5>
      </div>
    );
  });
};

const PopularMabcci = () => {
  const [firstMabcciIdx, setfirstMabcciIdx] = useState(0);
  const [mabcciList, setMabcciList] = useState([
    // {
    //   nickname: '젠킨스1',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스2',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스3',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스4',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스5',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스6',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스7',
    //   picture: userphoto,
    // },
    // {
    //   nickname: '젠킨스8',
    //   picture: userphoto,
    // },
  ]);

  useEffect(async () => {
    const response = await PopularMabcciApi();
    console.log(response);
    setMabcciList(response.mabccies);
  }, []);

  return (
    <article className="home-popularMabcci">
      <section className="home-popularMabcci-title">
        <h5>인기 Mabcci </h5>
      </section>
      <div className="home-popularMabcci-background" />
      <section className="home-popularMabcci-web">
        <div className="home-popularMabcci-top-web">
          {mabcciListPrint(mabcciList.slice(0, 4), 'web')}
        </div>
        <div className="home-popularMabcci-bottom-web">
          {mabcciListPrint(mabcciList.slice(4, 8), 'web')}
        </div>
      </section>
      <section className="home-popularMabcci-mobile">
        <Swiper navigation className="home-swiper-container">
          <SwiperSlide className="home-swiper-slide">
            <div className=".home-popularMabcci-top-mobile">
              {mabcciListPrint(mabcciList.slice(0, 2), 'mobile')}
            </div>
            <div className=".home-popularMabcci-bottom-mobile">
              {mabcciListPrint(mabcciList.slice(2, 4), 'mobile')}
            </div>
          </SwiperSlide>
          <SwiperSlide className="home-swiper-slide">
            <div className="home-popularMabcci-top">
              {mabcciListPrint(mabcciList.slice(4, 6), 'mobile')}
            </div>
            <div className="home-popularMabcci-bottom">
              {mabcciListPrint(mabcciList.slice(6, 8), 'mobile')}
            </div>
          </SwiperSlide>
        </Swiper>
      </section>
    </article>
  );
};

const PopularPost = () => {
  return <div> </div>;
};

export { PopularMabcci, PopularPost };
