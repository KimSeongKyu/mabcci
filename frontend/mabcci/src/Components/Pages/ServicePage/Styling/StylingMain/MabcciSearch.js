import React, { useState, useEffect } from 'react';
import { BiSearchAlt2, BiEdit } from 'react-icons/bi';
import AllMabcciApi from '../../../../../API/MabcciAPI/AllMabcciApi';
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
      nickName: '젠킨스1',
      picture: userphoto,
      categories: ['스트릿', '캐쥬얼'],
    },
    {
      nickName: '젠킨스2',
      picture: userphoto,
      categories: ['오피스', '캐쥬얼'],
    },
    {
      nickName: '젠킨스3',
      picture: userphoto,
      categories: ['오피스', '포멀'],
    },
    {
      nickName: '젠킨스4',
      picture: userphoto,
      categories: ['오피스', '미니멀'],
    },
    {
      nickName: '젠킨스5',
      picture: userphoto,
      categories: ['아메카지', '캐쥬얼'],
    },
  ]);
  const [filterMabcciList, setFilterMabcciList] = useState(mabcciList);
  const categories = [
    '미니멀',
    '스트릿',
    '아메카지',
    '오피스',
    '캐쥬얼',
    '포멀',
  ];
  const categoriesImage = [미니멀, 스트릿, 아메카지, 오피스, 캐쥬얼, 포멀];

  useEffect(() => {
    // const response = await AllMabcciApi();
    // setMabcciList(response.mabccies);
  }, []);

  /* 검색 이벤트 */
  const searchContentHandler = e => {
    setSearchContent(e.target.value);
  };

  const searchHandler = () => {
    setFilterMabcciList(
      mabcciList.filter(mabcci => {
        return mabcci.nickName === searchContent;
      }),
    );
  };

  const searchAllHandler = () => {
    setFilterMabcciList(mabcciList);
  };

  /* 카테고리 이벤트 */
  const categoryHandler = category => {
    setFilterMabcciList(
      mabcciList.filter(mabcci => {
        let flag = false;
        mabcci.categories.forEach(mabcciCategory => {
          if (mabcciCategory === category) flag = true;
        });
        return flag === true;
      }),
    );
  };

  /* 스타일링 신청 이벤트 */
  const stylingApplyHandler = nickname => {
    window.open('', '_blank');
    console.log(nickname, '스타일링 신청 페이지');
  };

  /* Mabcci 마이페이지 이벤트 */
  const mabcciPageHandler = nickName => {
    console.log(nickName, 'Mabcci 마이페이지');
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
          <button type="button" onClick={searchAllHandler}>
            ALL
          </button>
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
        {filterMabcciList.map(mabcci => (
          <div className="styling-mabcci" key={mabcci.nickName}>
            <button
              type="button"
              onClick={() => mabcciPageHandler(mabcci.nickName)}
              onKeyDown={() => mabcciPageHandler(mabcci.nickName)}
            >
              <img
                className="styling-mabcci-photo"
                src={mabcci.picture}
                alt="mabcciPhoto"
                width="100"
              />
            </button>
            <div className="styling-mabcci-info">
              <h3>{mabcci.nickName}</h3>
              {mabcci.categories.map(category => (
                <h6 key={category}># {category} </h6>
              ))}
            </div>
            <BiEdit
              className="styling-mabcci-apply"
              size="40"
              onClick={() => stylingApplyHandler(mabcci.nickName)}
              onKeyDown={() => stylingApplyHandler(mabcci.nickName)}
            />
          </div>
        ))}
      </article>
    </>
  );
};

export default MabcciSearch;
