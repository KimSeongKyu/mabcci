/* eslint-disable */
import '../../MyPageFollow/Follow.css';
import '../MySetting/MySetting.css'
import React, {useState, useEffect} from 'react';
import { Link, useHistory } from 'react-router-dom'
import 기본이미지 from '../../../../../../Asset/Images/기본프로필.jpg'
import MyProposalReviewDetail from './MyProposalReviewDetail';
import MyProposalReview from './MyProposalReview';
import SuggestSuggestionListApi from '../../../../../../API/SuggestionAPI/SuggestSuggestionListApi';
import ReceiveSuggestionListApi from '../../../../../../API/SuggestionAPI/ReceiveSuggestionListApi';
import { baseUrl } from '../../../../../../API/ApiUrl'

const MyProposalList = props => {
  const history = useHistory();

  const closeProposalBox = () => {
    props.setProposalBox(false);
  };

  const [suggestList, setSuggestList] = useState()
  const [receiveList, setReceiveList] = useState()

  useEffect(async() => {
    const suggestRes = await SuggestSuggestionListApi(props.myInfo.nickname);
    setSuggestList(suggestRes)
    const receiveRes = await ReceiveSuggestionListApi(props.myInfo.nickname);
    setReceiveList(receiveRes);
  }, [props.myInfo.nickname])

  const [nowProposalPage, setNowProposalPage] = useState('receive');

  const [nowProposalId, setNowProposalId] = useState();

  const seeSuggestList = () => {
    setNowProposalPage('suggest')
  }

  const seeReceiveList = () => {
    setNowProposalPage('receive');
  }

  const [reviewBox, setReviewBox] = useState(false);

  const [reviewDetailBox, setReviewDetailBox] = useState(false)

  const openReviewBox = (e) => {
    setNowProposalId(e.target.id);
    setReviewBox(true)
  }

  const openReviewDetail = (e) => {
    setNowProposalId(e.target.id);
    setReviewDetailBox(true);
  }

  const goProposalDetail = e => {
    history.push(`/suggestion/${e.target.id}`);
  };

  return (
    <>
      <MyProposalReviewDetail
        reviewDetailBox={reviewDetailBox}
        setReviewDetailBox={setReviewDetailBox}
        nowProposalId={nowProposalId}
      />
      <MyProposalReview
        reviewBox={reviewBox}
        setReviewBox={setReviewBox}
        nowProposalId={nowProposalId}
        receiveList={receiveList}
        setReceiveList={setReceiveList}
      />
      <div>
        {props.proposalBox === true ? (
          <div className="mypage-modal-container" />
        ) : null}
        {props.proposalBox === true ? (
          <div className="mypage-modal-box">
            <div className="mypage-modal-box-header">
              <h5>Proposal List</h5>
              <button
                type="submit"
                className="mypage-modal-box-btn"
                onClick={closeProposalBox}
              >
                X
              </button>
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
            {nowProposalPage === 'receive' ? (
              <div>
                {receiveList.map((receiveproposal, idx) => {
                  return (
                    <div
                      className="mypage-modal-box-content"
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
                        <div style={{ display: 'flex' }}>
                          <button
                            type="submit"
                            style={{ width: '5rem', height: '2rem' }}
                            id={receiveproposal.id}
                            onClick={goProposalDetail}
                          >
                            Open
                          </button>
                          <button
                            type="submit"
                            style={{
                              width: '5rem',
                              height: '2rem',
                              backgroundColor: 'lightblue',
                            }}
                            onClick={openReviewBox}
                            id={receiveproposal.id}
                          >
                            리뷰작성
                          </button>
                        </div>
                      ) : (
                        <div style={{ display: 'flex' }}>
                          <button
                            type="submit"
                            style={{ width: '5rem', height: '2rem' }}
                            id={receiveproposal.id}
                            onClick={goProposalDetail}
                          >
                            Open
                          </button>
                        <button
                          type="submit"
                          style={{
                            width: '5rem',
                            height: '2rem',
                            backgroundColor: 'lightgreen',
                          }}
                          id={receiveproposal.id}
                          onClick={openReviewDetail}
                        >
                          리뷰보기
                        </button>
                        </div>
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
                      className="mypage-modal-box-content"
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
      </div>
    </>
  );
};

export default MyProposalList;


