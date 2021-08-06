import React, { useState } from 'react';
import { RiArrowLeftSLine, RiArrowRightSLine } from 'react-icons/ri';
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
  const [firstMabcciIdx, setfirstMabcciIdx] = useState(0);
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

  const nextClickHandler = () => {
    setfirstMabcciIdx(4);
  };

  const beforeClickHandler = () => {
    setfirstMabcciIdx(0);
  };

  return (
    <article className="home-popularMabcci">
      <section className="home-popularMabcci-title">
        <h5>인기 Mabcci </h5>
      </section>
      <div className="home-popularMabcci-background" />
      <section className="home-popularMabcci-web">
        <div className="home-popularMabcci-top">
          {mabcciListPrint(mabcciList.slice(0, 4))}
        </div>
        <div className="home-popularMabcci-bottom">
          {mabcciListPrint(mabcciList.slice(4, 8))}
        </div>
      </section>
      <section className="home-popularMabcci-mobile">
        <div className="home-popularMabcci-top">
          {mabcciListPrint(
            mabcciList.slice(firstMabcciIdx, firstMabcciIdx + 2),
          )}
        </div>
        <div className="home-popularMabcci-bottom">
          {mabcciListPrint(
            mabcciList.slice(firstMabcciIdx + 2, firstMabcciIdx + 4),
          )}
        </div>
        {firstMabcciIdx === 0 ? (
          <RiArrowRightSLine
            className="home-popularMabcci-next"
            size="25"
            onClick={nextClickHandler}
          />
        ) : (
          <RiArrowLeftSLine
            className="home-popularMabcci-before"
            size="25"
            onClick={beforeClickHandler}
          />
        )}
      </section>
    </article>
  );
};

const PopularPost = () => {
  return <div> </div>;
};

export { PopularMabcci, PopularPost };
