/* eslint-disable */
import React, { useState, useEffect } from 'react';
import '../MySetting/MySetting.css';
import { IoArrowBackCircle } from 'react-icons/io5';
import { useHistory } from 'react-router-dom';
import 기본이미지 from '../../../../../../Asset/Images/기본프로필.jpg'
import { baseUrl } from '../../../../../../API/ApiUrl'
import MyPageProposalReview from './MyProposalReview';
import SuggestSuggestionListApi from '../../../../../../API/SuggestionAPI/SuggestSuggestionListApi'
import ReceiveSuggestionListApi from '../../../../../../API/SuggestionAPI/ReceiveSuggestionListApi';

const MyPageProposalListMobile = props => {
  const [reviewBox, setReviewBox] = useState(false);
  const history = useHistory();

  const [suggestList, setSuggestList] = useState();
  const [receiveList, setReceiveList] = useState();

  useEffect(async () => {
    const suggestRes = await SuggestSuggestionListApi(props.myInfo.nickname);
    setSuggestList(suggestRes);
    const receiveRes = await ReceiveSuggestionListApi(props.myInfo.nickname);
    setReceiveList(receiveRes);
  }, [props.myInfo.nickname]);

  const [nowProposalPage, setNowProposalPage] = useState('receive');
  const [nowProposalId, setNowProposalId] = useState();

  const seeSuggestList = () => {
    setNowProposalPage('suggest');
  };

  const seeReceiveList = () => {
    setNowProposalPage('receive');
  };

  const goBack = () => {
    props.setProposalBox(false)
    props.setMobileMenu(true)
    props.setMyPageUpdate('update')
  };  

  const openReviewBox = e => {
    setNowProposalId(e.target.id);
    setReviewBox(true);
  };

  const goProposalDetail = (id) => {
    history.push(`/suggestion/${id}`)
  }

  return (
    <>
      <MyPageProposalReview
        reviewBox={reviewBox}
        setReviewBox={setReviewBox}
        nowProposalId={nowProposalId}
        receiveList={receiveList}
        setReceiveList={setReceiveList}
      />
      {props.proposalBox === true ? (
        <div className="mypage-moblie-container" />
      ) : null}
      {props.proposalBox === true ? (
        <div className="mypage-mobile-menu mypage-mobile-proposal">
          <div className="mypage-mobile-menu-header">
            <button
              type="submit"
              className="mypage-mobile-menu-btn"
              onClick={goBack}
            >
              <IoArrowBackCircle />
            </button>
            <h3>Proposal List</h3>
          </div>
          {props.myInfo.role == 'MABCCI' ? (
            <div className="mypage-proposal-header">
              <h5
                style={
                  nowProposalPage == 'receive' ? { color: '#f9a77c' } : null
                }
                onClick={seeReceiveList}
              >
                receive
              </h5>
              <h5
                style={
                  nowProposalPage == 'suggest' ? { color: '#f9a77c' } : null
                }
                onClick={seeSuggestList}
              >
                suggest
              </h5>
            </div>
          ) : null}
          {nowProposalPage == 'receive' ? (
            <div>
              {receiveList.map(receiveproposal => {
                return (
                  <div
                    className="mypage-modal-box-content mypage-mobile-proposal-box"
                    key={receiveproposal.id}
                  >
                    <img
                      src={
                        receiveproposal.picture == null
                          ? 기본이미지
                          : baseUrl + receiveproposal.picture
                      }
                      alt="하이"
                    />
                    <div className="mypage-proposal-box-info">
                      <h5>To {props.myInfo.nickname}</h5>
                      <p>Mabcci {receiveproposal.nickname}</p>
                      <p>{receiveproposal.createdDate.slice(0, 10)}</p>
                    </div>

                    {receiveproposal.isReviewed == false ? (
                      <button
                        type="submit"
                        onClick={openReviewBox}
                        id={receiveproposal.id}
                      >
                        Review
                      </button>
                    ) : (
                      <button type="submit">Open</button>
                    )}
                  </div>
                );
              })}
            </div>
          ) : (
            <div>
              {suggestList.map(suggestproposal => {
                return (
                  <div
                    className="mypage-modal-box-content mypage-mobile-proposal-box"
                    key={suggestproposal.id}
                  >
                    <img
                      src={
                        suggestproposal.picture == null
                          ? 기본이미지
                          : baseUrl + suggestproposal.picture
                      }
                      alt="하이"
                    />
                    <div className="mypage-proposal-box-info">
                      <h5>To {suggestproposal.nickname}</h5>
                      <p>Mabcci {props.myInfo.nickname}</p>
                      <p>{suggestproposal.createdDate.slice(0, 10)}</p>
                    </div>

                    <button
                      type="submit"
                      id={suggestproposal.id}
                      onClick={goProposalDetail}
                    >
                      Open
                    </button>
                  </div>
                );
              })}
            </div>
          )}
        </div>
      ) : null}
    </>
  );
};

export default MyPageProposalListMobile;
