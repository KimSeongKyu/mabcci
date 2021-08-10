import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';

import { HiMenu } from 'react-icons/hi';
import MabcciReview from './MabcciReview';
import MyPageFeed from './MyPageFeed';
import MyPageProfile from './MyPageProfile';
import MypageReadApi from '../../../../../API/MypageAPI/MypageReadApi';
import FollowBox from '../MyPageFollow/FollowBox';
import MyChatList from '../MyPageSetting/MyChatList/MyChatList';
import MyProposalList from '../MyPageSetting/MyProposal/MyProposalList';
import MyProposalListMobile from '../MyPageSetting/MyProposal/MyProposalListMobile';
import MyPageMobileMenu from '../MyPageSetting/MySetting/MyPageMobileMenu';
import MyPageUpdate from '../MyPageSetting/MySetting/MyPageUpdate';

function MyPageMain() {
  const history = useHistory();

  const userInfo = JSON.parse(localStorage.getItem('userInfo'));

  const [myInfo, setMyInfo] = useState({});

  const [followBox, setFollowBox] = useState('none');

  const [chatBox, setChatBox] = useState(false);

  const [proposalBox, setProposalBox] = useState(false);

  const [mobileMenu, setMobileMenu] = useState(false);

  const [myPageUpdate, setMyPageUpdate] = useState('none');

  useEffect(async () => {
    const res = await MypageReadApi(userInfo.nickname);
    // await setMyInfo(...myInfo, ...res.myInfo);
    await setMyInfo(res.myInfo);
    console.log(res.myInfo);
  }, []);

  const goToMobileMenu = () => {
    setMobileMenu(true);
  };

  return (
    <div className="mypage-entire">
      <MyPageUpdate
        myPageUpdate={myPageUpdate}
        setMyPageUpdate={setMyPageUpdate}
      />
      <FollowBox followBox={followBox} setFollowBox={setFollowBox} />
      <MyChatList chatBox={chatBox} setChatBox={setChatBox} />
      <MyProposalList
        proposalBox={proposalBox}
        setProposalBox={setProposalBox}
      />
      <MyProposalListMobile
        proposalBox={proposalBox}
        setProposalBox={setProposalBox}
      />
      <MyPageMobileMenu
        mobileMenu={mobileMenu}
        setMobileMenu={setMobileMenu}
        proposalBox={proposalBox}
        setProposalBox={setProposalBox}
        myPageUpdate={myPageUpdate}
        setMyPageUpdate={setMyPageUpdate}
      />
      <div className="mypage-container">
        <button
          className="mypage-mobile-setting"
          type="submit"
          onClick={goToMobileMenu}
        >
          <HiMenu />
        </button>
        <MyPageProfile
          myInfo={myInfo}
          followBox={followBox}
          setFollowBox={setFollowBox}
          chatBox={chatBox}
          setChatBox={setChatBox}
          proposalBox={proposalBox}
          setProposalBox={setProposalBox}
          myPageUpdate={myPageUpdate}
          setMyPageUpdate={setMyPageUpdate}
        />
        <MyPageFeed />
        <MabcciReview />
      </div>
    </div>
  );
}

export default MyPageMain;
