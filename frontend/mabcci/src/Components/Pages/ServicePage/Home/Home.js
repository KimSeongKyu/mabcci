import React, { useEffect } from 'react';
import { useDispatch } from 'react-redux';
import { Link } from 'react-router-dom';
import NavCategory from '../../../../Redux/Actions/NavAction';
import './Home.css';
import HomeHeader from './HomeHeader';
import { PopularMabcci, Etc } from './HomeContent';

const Home = () => {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(NavCategory('home'));
  }, []);

  return (
    <div className="container home-container">
      <HomeHeader />
      <div className="home-content">
        <PopularMabcci />
        <Etc />
      </div>
    </div>
  );
};

export default Home;
