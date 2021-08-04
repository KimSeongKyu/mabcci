import React, { useState, useEffect } from 'react';
import { BiSearchAlt2, BiEdit } from 'react-icons/bi';
import MabcciSearchApi from '../../../../../API/MabcciAPI/MabcciSearchApi';
import 미니멀 from '../../../../../Asset/Images/미니멀옷.png';
import 스트릿 from '../../../../../Asset/Images/스트릿옷.png';
import 아메카지 from '../../../../../Asset/Images/아메카지옷.png';
import 오피스 from '../../../../../Asset/Images/오피스옷.png';
import 캐쥬얼 from '../../../../../Asset/Images/캐쥬얼옷.png';
import 포멀 from '../../../../../Asset/Images/포멀옷.png';
import userphoto from './Images/userphoto.png';

const MabcciSearch = () => {
  const [searchContent, setSearchContent] = useState('');
  const [mabcciList, setMabcciList] = useState([
    {
      nickname: '젠킨스1',
      picture: userphoto,
      categories: ['스트릿', '캐쥬얼'],
    },
    {
      nickname: '젠킨스2',
      picture: userphoto,
      categories: ['오피스룩', '캐쥬얼'],
    },
    {
      nickname: '젠킨스3',
      picture: userphoto,
      categories: ['오피스룩', '캐쥬얼'],
    },
    {
      nickname: '젠킨스4',
      picture: userphoto,
      categories: ['오피스룩', '캐쥬얼'],
    },
    {
      nickname: '젠킨스5',
      picture: userphoto,
      categories: ['오피스룩', '캐쥬얼'],
    },
  ]);
  const categories = [
    '미니멀',
    '스트릿',
    '아메카지',
    '오피스',
    '캐쥬얼',
    '포멀',
  ];
  const categoriesImage = [미니멀, 스트릿, 아메카지, 오피스, 캐쥬얼, 포멀];

  useEffect(async () => {
    // const response = await MabcciSearch();
    // setMabcciList(response.mabcci);
  }, []);

  const searchContentHandler = e => {
    setSearchContent(e.target.value);
    console.log(searchContent);
  };

  const searchHandler = () => {};

  const categoryHandler = category => {
    console.log(category);
  };

  return (
    <>
      <header className="styling-header">
        <div className="styling-title">
          <h3>Styling</h3>
        </div>
        <div className="styling-search">
          <input type="text" onChange={searchContentHandler} />
          <BiSearchAlt2
            size="30"
            className="styling-search-send"
            onClick={searchHandler}
          />
        </div>
      </header>
      <article className="styling-categories">
        {categories.map((category, index) => (
          <div className="styling-category" key={category}>
            <button
              className="btn-style-clothes"
              type="button"
              onClick={() => categoryHandler(category)}
            >
              <img src={categoriesImage[index]} alt={category} />
            </button>
            <p>{category}</p>
          </div>
        ))}
      </article>
      <article className="styling-mabccilist">
        {mabcciList.map(mabcci => (
          <div className="styling-mabcci" key={mabcci.nickname}>
            <img
              className="styling-mabcci-photo"
              src={mabcci.picture}
              alt="mabcciPhoto"
              width="100"
            />
            <div className="styling-mabcci-info">
              <h3>{mabcci.nickname}</h3>
              {mabcci.categories.map(category => (
                <h6># {category} </h6>
              ))}
            </div>
            <BiEdit className="styling-mabcci-apply" size="40" />
          </div>
        ))}
      </article>
    </>
  );
};

export default MabcciSearch;
