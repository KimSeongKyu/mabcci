import React, { useState, useEffect } from 'react';
import { BiSearchAlt2, BiEdit } from 'react-icons/bi';
import { Link } from 'react-router-dom';
import AllMabcciApi from '../../../../../API/MabcciAPI/AllMabcciApi';
import 미니멀 from '../../../../../Asset/Images/미니멀옷.png';
import 스트릿 from '../../../../../Asset/Images/스트릿옷.png';
import 아메카지 from '../../../../../Asset/Images/아메카지옷.png';
import 오피스 from '../../../../../Asset/Images/오피스옷.png';
import 캐쥬얼 from '../../../../../Asset/Images/캐쥬얼옷.png';
import 포멀 from '../../../../../Asset/Images/포멀옷.png';
import { baseUrl } from '../../../../../API/ApiUrl';
import 기본프로필 from '../../../../../Asset/Images/기본프로필.jpg';
import userphoto from './Images/userphoto.png';
import { createChatRoomApi } from '../../../../../API/ChatAPI/ChatApi';

const MabcciSearch = () => {
  const [searchContent, setSearchContent] = useState('');
  const [mabcciList, setMabcciList] = useState([]);
  const [filterMabcciList, setFilterMabcciList] = useState([]);
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
    const response = await AllMabcciApi();
    setMabcciList(response.mabccies);
    setFilterMabcciList(response.mabccies);
  }, []);

  /* 검색 이벤트 */
  const searchContentHandler = e => {
    setSearchContent(e.target.value);
  };

  const searchHandler = () => {
    setFilterMabcciList(
      mabcciList &&
        mabcciList.filter(mabcci => {
          return mabcci.nickname === searchContent;
        }),
    );
  };

  const searchAllHandler = () => {
    setFilterMabcciList(mabcciList);
  };

  /* 카테고리 이벤트 */
  const categoryHandler = category => {
    setFilterMabcciList(
      mabcciList &&
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
  const stylingApplyHandler = async nickname => {
    const mabcci = { mabcci: nickname };
    const response = await createChatRoomApi(mabcci);
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
        {filterMabcciList &&
          filterMabcciList.map(mabcci => (
            <div className="styling-mabcci" key={mabcci.nickname}>
              <button type="button">
                <Link to={`/mypage/${mabcci.nickname}`}>
                  <img
                    className="styling-mabcci-photo"
                    src={
                      mabcci.picture !== null
                        ? baseUrl + mabcci.picture
                        : 기본프로필
                    }
                    alt="mabcciPhoto"
                    width="100"
                  />
                </Link>
              </button>
              <div className="styling-mabcci-info">
                <Link to={`/mypage/${mabcci.nickname}`}>
                  <h3>{mabcci.nickname}</h3>
                </Link>
                {mabcci.categories.map(category => (
                  <h6 key={category}># {category} </h6>
                ))}
              </div>
              <BiEdit
                className="styling-mabcci-apply"
                size="40"
                onClick={() => stylingApplyHandler(mabcci.nickname)}
                onKeyDown={() => stylingApplyHandler(mabcci.nickname)}
              />
            </div>
          ))}
      </article>
    </>
  );
};

export default MabcciSearch;
