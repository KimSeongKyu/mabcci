import React, { useEffect, useState } from 'react';
import { useParams, useHistory } from 'react-router-dom';
import { useDispatch } from 'react-redux';

import { HiMenu } from 'react-icons/hi';
import NavCategory from '../../../../../Redux/Actions/NavAction';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';
import MypageReadApi from '../../../../../API/MypageAPI/MypageReadApi';
import FollowBox from '../MyPageFollow/FollowBox';
import MyChatList from '../MyPageSetting/MyChatList/MyChatList';
import MyProposalList from '../MyPageSetting/MyProposal/MyProposalList';
import MyProposalListMobile from '../MyPageSetting/MyProposal/MyProposalListMobile';
import MyPageMobileMenu from '../MyPageSetting/MySetting/MyPageMobileMenu';
import getUserInfo from '../../../../Common/getUserInfo';
import { baseUrl } from '../../../../../API/ApiUrl';
import MyPageReview from './MyPageReview';

function MyPageMain() {
  const { nickname } = useParams();

  const userInfo = getUserInfo();

  const dispatch = useDispatch();

  const [myInfo, setMyInfo] = useState({});

  const [followBox, setFollowBox] = useState('none');

  const [chatBox, setChatBox] = useState(false);

  const [proposalBox, setProposalBox] = useState(false);

  const [mobileMenu, setMobileMenu] = useState(false);

  const [myPageUpdate, setMyPageUpdate] = useState('none');

  useEffect(async () => {
    dispatch(NavCategory('mypage'));
    const res = await MypageReadApi(nickname);
    if (res.myInfo.picture !== null) {
      res.myInfo.picture = baseUrl + res.myInfo.picture;
    }
    setMyInfo(res.myInfo);
  }, [nickname]);

  const goToMobileMenu = () => {
    setMobileMenu(true);
    setMyPageUpdate('setting');
  };

  return (
    <div className="mypage-entire">
      {myInfo.categories ? (
        <FollowBox
          followBox={followBox}
          setFollowBox={setFollowBox}
          myInfo={myInfo}
        />
      ) : null}

      <MyChatList chatBox={chatBox} setChatBox={setChatBox} />
      <MyProposalList
        myInfo={myInfo}
        proposalBox={proposalBox}
        setProposalBox={setProposalBox}
      />
      <MyProposalListMobile
        proposalBox={proposalBox}
        setProposalBox={setProposalBox}
        mobileMenu={mobileMenu}
        setMobileMenu={setMobileMenu}
        setMyPageUpdate={setMyPageUpdate}
        myInfo={myInfo}
      />
      {myInfo.categories ? (
        <MyPageMobileMenu
          myInfo={myInfo}
          setMyInfo={setMyInfo}
          mobileMenu={mobileMenu}
          setMobileMenu={setMobileMenu}
          proposalBox={proposalBox}
          setProposalBox={setProposalBox}
          myPageUpdate={myPageUpdate}
          setMyPageUpdate={setMyPageUpdate}
        />
      ) : null}
      <div className="mypage-container">
        {userInfo.nickname === myInfo.nickname ? (
          <button
            className="mypage-mobile-setting"
            type="submit"
            onClick={goToMobileMenu}
          >
            <HiMenu />
          </button>
        ) : null}

        {myInfo.categories ? (
          <MyPageProfile
            myInfo={myInfo}
            setMyInfo={setMyInfo}
            followBox={followBox}
            setFollowBox={setFollowBox}
            chatBox={chatBox}
            setChatBox={setChatBox}
            proposalBox={proposalBox}
            setProposalBox={setProposalBox}
            myPageUpdate={myPageUpdate}
            setMyPageUpdate={setMyPageUpdate}
            setMobileMenu={setMobileMenu}
          />
        ) : null}
        {myInfo.role === 'MABCCI' ? <MyPageReview /> : null}
        {myInfo.ootds ? <MyPageFeed myInfo={myInfo} /> : null}
      </div>
    </div>
  );
}

export default MyPageMain;
