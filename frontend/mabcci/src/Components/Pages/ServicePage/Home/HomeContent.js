import React, { useState, useEffect } from 'react';
import { RiArrowLeftSLine, RiArrowRightSLine } from 'react-icons/ri';
import { Swiper, SwiperSlide } from 'swiper/react';
import SwiperCore, { Navigation } from 'swiper/core';
import 'swiper/swiper.min.css';
import 'swiper/components/navigation/navigation.min.css';
import { Link } from 'react-router-dom';
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
    {
      nickName: '젠킨스1',
      picture: userphoto,
    },
    {
      nickName: '젠킨스2',
      picture: userphoto,
    },
    {
      nickName: '젠킨스3',
      picture: userphoto,
    },
    {
      nickName: '젠킨스4',
      picture: userphoto,
    },
    {
      nickName: '젠킨스5',
      picture: userphoto,
    },
    {
      nickName: '젠킨스6',
      picture: userphoto,
    },
    {
      nickName: '젠킨스7',
      picture: userphoto,
    },
    {
      nickName: '젠킨스8',
      picture: userphoto,
    },
  ]);

  useEffect(async () => {
    // const response = await PopularMabcciApi();
    // console.log(response);
    // setMabcciList(response.mabccies);
  }, []);

  return (
    <article className="home-popularMabcci">
      <section className="home-popularMabcci-title">
        <h4>인기 Mabcci </h4>
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
        <Swiper
          style={{
            '--swiper-navigation-color': '#fbf1e9',
          }}
          navigation
          className="home-swiper-container"
        >
          <SwiperSlide className="home-swiper-slide">
            <div className="home-popularMabcci-top-mobile">
              {mabcciListPrint(mabcciList.slice(0, 2), 'mobile')}
            </div>
            <div className="home-popularMabcci-bottom-mobile">
              {mabcciListPrint(mabcciList.slice(2, 4), 'mobile')}
            </div>
          </SwiperSlide>
          <SwiperSlide className="home-swiper-slide">
            <div className="home-popularMabcci-top-mobile">
              {mabcciListPrint(mabcciList.slice(4, 6), 'mobile')}
            </div>
            <div className="home-popularMabcci-bottom-mobile">
              {mabcciListPrint(mabcciList.slice(6, 8), 'mobile')}
            </div>
          </SwiperSlide>
        </Swiper>
      </section>
    </article>
  );
};

const Etc = () => {
  const [position, setPosition] = useState(0);
  const [fix, setFix] = useState({
    firstImage: false,
    firstImageDesc: false,
    secondImage: false,
    secondImageDesc: false,
  });

  const onScroll = () => {
    setPosition(window.scrollY);
  };

  useEffect(() => {
    window.addEventListener('scroll', onScroll);
    return () => {
      window.removeEventListener('scroll', onScroll);
    };
  }, []);

  useEffect(() => {
    if (window.scrollY >= 370) {
      setFix({ ...fix, firstImage: true });
    }
    if (window.scrollY >= 700) {
      setFix({ ...fix, firstImageDesc: true });
    }
    if (window.scrollY >= 800) {
      setFix({ ...fix, secondImage: true });
    }
    if (window.scrollY >= 1100) {
      setFix({ ...fix, secondImageDesc: true });
    }
  }, [position]);

  return (
    <div>
      <div className="welcome">
        <div className="welcome-comment">
          <span className="welcome-title">Welcome,</span>
          <div>
            <p className="welcome-sub-title">Mabcci에 오신것을</p>
            <p className="welcome-sub-title">진심으로 환영합니다!!</p>
          </div>
        </div>
        <div className="circle-box">
          <div className="circle" />
          <div className="circle" />
          <div className="circle" />
        </div>
      </div>
      <div className="welcome-image-box">
        <div
          className={`welcome-image-desc-box ${
            fix.firstImage ? 'welcome-image' : ''
          }`}
        >
          <div className={`${fix.firstImageDesc ? `welcome-image-desc` : ''}`}>
            <p>당신에게 꼭 맞는</p>
            <p>
              <span>맵시</span>를 찾아보세요
            </p>
          </div>
          <div className={`${fix.firstImageDesc ? `welcome-image-desc` : ''}`}>
            <Link to="/styling">
              <button type="button" className="btn-sm">
                <p>Styling</p>
              </button>
            </Link>
          </div>
        </div>
        <div
          className={`welcome-image-desc-box ${
            fix.secondImage ? 'welcome-image' : ''
          }`}
        >
          <div className={`${fix.secondImageDesc ? `welcome-image-desc` : ''}`}>
            <Link to="/ootd">
              <button type="button" className="btn-sm">
                <p>OOTD</p>
              </button>
            </Link>
          </div>
          <div className={`${fix.secondImageDesc ? `welcome-image-desc` : ''}`}>
            <p>
              당신의 <span>스타일</span>을
            </p>
            <p>마음껏 펼쳐보세요!!</p>
          </div>
        </div>
      </div>
      <div className="welcome-bottom">
        <p>developed by.</p>
        <div className="developer-box">
          <div className="developer" />
          <div className="developer" />
          <div className="developer" />
          <div className="developer" />
          <div className="developer" />
        </div>
      </div>
    </div>
  );
};

export { PopularMabcci, Etc };
