import React from 'react';
import { Link } from 'react-router-dom';
import './Home.css';
import HomeHeader from './HomeHeader';
import { PopularMabcci, PopularPost } from './HomeContent';

const Home = () => {
  return (
    <div className="container home-container">
      <HomeHeader />
      <div className="home-content">
        <PopularMabcci />
        <PopularPost />
      </div>
    </div>
  );
};

export default Home;
