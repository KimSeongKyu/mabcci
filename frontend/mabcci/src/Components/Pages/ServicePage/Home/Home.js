import React from 'react';
<<<<<<< HEAD

function Home() {
  return (
    <div>
      <h3>홈페이지입니다.</h3>
=======
import { Link } from 'react-router-dom';
import './Home.css';
import HomeHeader from './HomeHeader';
import { PopularMabcci, PopularPost } from './HomeContent';

function Home() {
  return (
    <div className="container home-container">
      <HomeHeader />
      <div className="home-content">
        <PopularMabcci />
        <PopularPost />
      </div>
>>>>>>> 00ecaa1 ([S05P13C107-59] [FE-seongaeee] feat: HOME 페이지 인기 Mabcci 반응형 디자인 구축)
    </div>
  );
}

export default Home;
