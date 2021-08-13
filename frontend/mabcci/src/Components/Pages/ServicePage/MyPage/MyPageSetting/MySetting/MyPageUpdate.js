/* eslint-disable */

import React from 'react';
import './MySetting.css';
import '../../MyPageFollow/Follow.css'
import MypageUpdateApi from '../../../../../../API/MypageAPI/MyPageUpdateApi';
const MyPageUpdate = (props) => {

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const closeUpdate = () => {
    props.setMyPageUpdate('none')
  }

  const myInfo = {
    nickname: '박서준',
    gender: 'WOMAN',
    height: '185',
    weight: '70',
    footSize: '270',
    bodyType: '',

    category: ['스트릿', '포멀'],
    picture: '',
    description: '안녕하세요 서준팍입니다',
  };

  const updateMyInfo = async () => {
    const res = await MypageUpdateApi(myInfo, userInfo.nickname);
    console.log(res)
  }

  return (
    <>
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-modal-container" />
      ) : null}
      {props.myPageUpdate !== 'none' ? (
        <div className="mypage-update-container">
          <h1>회원정보수정페이지</h1>
          <h2>하이루</h2>
          <h2>하이루</h2>
          <button type="submit" onClick={closeUpdate}>
            X
          </button>
          <button className="btn-sm" type='submit' onClick={updateMyInfo}>회원정보수정</button>
        </div>
      ) : null}
    </>
  );
};

export default MyPageUpdate;
