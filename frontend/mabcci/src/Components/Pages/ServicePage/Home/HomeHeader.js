import React from 'react';
import { MdAccessAlarm } from 'react-icons/md';
import logo from '../../../../Asset/Images/logo.png';

const HomeHeader = () => {
  return (
    <header className="home-mobile-header">
      <img src={logo} alt="logo" />
      <MdAccessAlarm className="home-mobile-header-alarm" size="20" />
    </header>
  );
};

export default HomeHeader;
