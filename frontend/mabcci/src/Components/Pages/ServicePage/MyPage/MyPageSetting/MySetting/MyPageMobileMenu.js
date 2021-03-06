/* eslint-disable */
import { useParams } from 'react-router-dom';
import React from 'react';
import './MySetting.css';
import { IoArrowBackCircle } from 'react-icons/io5';
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom';
import { Logout } from '../../../../../../Redux/Actions/LoginAction';
import MyCategoryMobile from './MyCategoryMobile';
import MyInfoMobile from './MyInfoMobile';
import MyProfileMobile from './MyProfileMobile';
import { useState, useEffect } from 'react';
import MypageReadApi from '../../../../../../API/MypageAPI/MypageReadApi';
import { baseUrl } from '../../../../../../API/ApiUrl';
import MyPageUpdate from './MyPageUpdate';
import getUserInfo from '../../../../../Common/getUserInfo';

const MyPageMobileMenu = props => {
  const userInfo = getUserInfo();
  const dispatch = useDispatch();
  const history = useHistory();
  const [myUpdateInfo, setMyUpdateInfo] = useState({})
  const { nickname } = useParams();

  useEffect(async () => {
    const res = await MypageReadApi(nickname);
    if (res.myInfo.picture !== null) {
      res.myInfo.picture = baseUrl + res.myInfo.picture;
    }
    setMyUpdateInfo({
      ...myUpdateInfo,
      nickname: res.myInfo.nickname,
      gender: res.myInfo.gender,
      height: res.myInfo.height,
      weight: res.myInfo.weight,
      footSize: res.myInfo.footSize,
      bodyType: res.myInfo.bodyType,
      categories: res.myInfo.categories,
      picture: res.myInfo.picture,
      updatePicture: null,
      description: res.myInfo.description,
    });
  }, [nickname]);

  const updateData = new FormData();
  updateData.append('gender', myUpdateInfo.gender);
  updateData.append('nickname', myUpdateInfo.nickname);
  
  updateData.append('categories', myUpdateInfo.categories);
  {
    myUpdateInfo.height == []
      ? updateData.append('height', 0)
      : updateData.append('height', myUpdateInfo.height);
  }
  {
    myUpdateInfo.weight == []
      ? updateData.append('weight', 0)
      : updateData.append('weight', myUpdateInfo.weight);
  }
  {
    myUpdateInfo.footSize == []
      ? updateData.append('footSize', 0)
      : updateData.append('footSize', myUpdateInfo.footSize);
  }
  updateData.append('picture', myUpdateInfo.updatePicture);
  updateData.append('description', myUpdateInfo.description);
    {
      myUpdateInfo.bodyType
        ? updateData.append('bodyType', myUpdateInfo.bodyType)
        : updateData.append('bodyType', 'none');
    }
  

  const [myPageUpdate, setMyPageUpdate] = useState('none')

  const goBack = () => {
    props.setMobileMenu(false)
    props.setMyPageUpdate('none');
  };

  const LogOut = () => {
    localStorage.clear();
    dispatch(Logout());
    history.push('/intro');
  };

  const goMyPageUpdate = e => {
    props.setMyPageUpdate(e.target.name);
    props.setMobileMenu(false)
  }

  const goToMobileProposal = () => {
    props.setProposalBox(true)
    props.setMobileMenu(false);
    props.setMyPageUpdate('none');
  };

  return (
    <>
      <MyPageUpdate
        myUpdateInfo={myUpdateInfo}
        setMyUpdateInfo={setMyUpdateInfo}
        updateData={updateData}
        myPageUpdate={props.myPageUpdate}
        setMyPageUpdate={props.setMyPageUpdate}
        myInfo={props.myInfo}
        setMyInfo={props.setMyInfo}
        setMobileMenu={props.setMobileMenu}
      />
      <MyCategoryMobile
        myPageUpdate={props.myPageUpdate}
        setMyPageUpdate={props.setMyPageUpdate}
        setMobileMenu={props.setMobileMenu}
        myUpdateInfo={myUpdateInfo}
        setMyUpdateInfo={setMyUpdateInfo}
        updateData={updateData}
        myInfo={props.myInfo}
        setMyInfo={props.setMyInfo}
      />
      <MyInfoMobile
        myPageUpdate={props.myPageUpdate}
        setMyPageUpdate={props.setMyPageUpdate}
        setMobileMenu={props.setMobileMenu}
        myUpdateInfo={myUpdateInfo}
        setMyUpdateInfo={setMyUpdateInfo}
        updateData={updateData}
        myInfo={props.myInfo}
        setMyInfo={props.setMyInfo}
      />
      <MyProfileMobile
        myPageUpdate={props.myPageUpdate}
        setMyPageUpdate={props.setMyPageUpdate}
        setMobileMenu={props.setMobileMenu}
        myUpdateInfo={myUpdateInfo}
        setMyUpdateInfo={setMyUpdateInfo}
        updateData={updateData}
        myInfo={props.myInfo}
        setMyInfo={props.setMyInfo}
      />
      {props.mobileMenu === true ? (
        <div className="mypage-moblie-container" />
      ) : null}
      {props.mobileMenu === true ? (
        <div className="mypage-mobile-menu">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h1>??????</h1>
          </div>
          <div className="mypage-mobile-menu-content">
            <h3>?????? ??????</h3>
            <div>
              <button type="submit" name="info" onClick={goMyPageUpdate}>
                ??? ?????? ??????
              </button>
            </div>
            <div>
              <button type="submit" name="category" onClick={goMyPageUpdate}>
                ???????????? ??????
              </button>
            </div>
            <div>
              <button type="submit" name="profile" onClick={goMyPageUpdate}>
                ????????? ??????
              </button>
            </div>
          </div>
          <div className="mypage-mobile-menu-content">
            <h3>?????????</h3>
            <div>
              <button type="submit">????????????</button>
            </div>
            <div>
              <button type="submit" onClick={goToMobileProposal}>
                ???????????????
              </button>
            </div>
          </div>
          <button
            type="submit"
            className="mobile-menu-logout-btn"
            onClick={LogOut}
          >
            ????????????
          </button>
        </div>
      ) : null}
    </>
  );
};

export default MyPageMobileMenu;
