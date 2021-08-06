import React, { useState } from 'react';
import { RiArrowRightSLine } from 'react-icons/ri';
import userphoto from './Images/userphoto.png';

const mabcciListPrint = mabcciList => {
  return mabcciList.map(mabcci => {
    return (
      <div className="home-mabcci" key={mabcci.nickname}>
        <img
          className="home-mabcci-photo"
          src={mabcci.picture}
          alt="mabcciPhoto"
          width="100"
        />
        <h5>{mabcci.nickname}</h5>
      </div>
    );
  });
};

const PopularMabcci = () => {
  const [mabcciList, setMabcciList] = useState([
    {
      nickname: '젠킨스1',
      picture: userphoto,
    },
    {
      nickname: '젠킨스2',
      picture: userphoto,
    },
    {
      nickname: '젠킨스3',
      picture: userphoto,
    },
    {
      nickname: '젠킨스4',
      picture: userphoto,
    },
    {
      nickname: '젠킨스5',
      picture: userphoto,
    },
    {
      nickname: '젠킨스6',
      picture: userphoto,
    },
    {
      nickname: '젠킨스7',
      picture: userphoto,
    },
    {
      nickname: '젠킨스8',
      picture: userphoto,
    },
  ]);

  return (
    <article className="home-popularMabcci">
      <section className="home-popularMabcci-title">
        <h5>인기 Mabcci</h5>
      </section>
      <div className="home-popularMabcci-background" />
      <section className="home-popularMabcci-list">
        <div className="home-popularMabcci-list1">
          <div className="home-popularMabcci-top1">
            {mabcciListPrint(mabcciList.slice(0, 2))}
          </div>
          <div className="home-popularMabcci-bottom1">
            {mabcciListPrint(mabcciList.slice(2, 4))}
          </div>
        </div>
        <div className="home-popularMabcci-list2">
          <div className="home-popularMabcci-top2">
            {mabcciListPrint(mabcciList.slice(4, 6))}
          </div>
          <div className="home-popularMabcci-bottom2">
            {mabcciListPrint(mabcciList.slice(6, 8))}
          </div>
        </div>
      </section>
    </article>
  );
};

const PopularPost = () => {
  return <div> </div>;
};

export { PopularMabcci, PopularPost };
