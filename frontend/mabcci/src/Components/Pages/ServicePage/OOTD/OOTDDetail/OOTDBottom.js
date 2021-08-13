import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { IoMdSend } from 'react-icons/io';
import { OOTDCommentReadApi } from '../../../../../API/OOTDAPI/OOTDDetailApi';
import userphoto from './Images/userphoto.png';

export const SingleComment = props => {
  const { depth, comment, userInfo } = props;
  const [replyComment, setReplyComment] = useState([]);

  useEffect(() => {
    // if (depth === 0) {
    // }
  });

  return (
    <div className="detail-singlecomment">
      <div className="detail-comment">
        <div className="detail-comment-info">
          <div className="detail-comment-info-photo">
            {comment.memberPicture}
          </div>
          <div className="detail-comment-info-content">
            <p>{comment.memberNickname}</p>
            <p>{comment.createdDate}</p>
            {depth === '0' ? (
              <button type="button" className="detail-comment-info-button">
                답글달기
              </button>
            ) : null}
            {comment.memberNickname === userInfo.nickname ? (
              <>
                <button type="button" className="detail-comment-info-button">
                  수정
                </button>
                <button type="button" className="detail-comment-info-button">
                  삭제
                </button>
              </>
            ) : null}
          </div>
        </div>
        <div className="detail-comment-content">{comment.content}</div>
      </div>
      {depth === '0' ? (
        <div className="detail-reply-comment">
          {
            // comment.comments.length !== 0 &&
            //   comment.comments.map(replyComment => {
            //     return (
            //       <SingleComment
            //         key={comment.createdDate}
            //         depth={depth + 1}
            //         comment={replyComment}
            //         userInfo={userInfo}
            //       />
            //     );
            //   })
          }
        </div>
      ) : null}
    </div>
  );
};

const OOTDBottom = props => {
  const { ootdId, userInfo } = props;
  const [comments, setComments] = useState([
    {
      memberPicture: '작성자 사진',
      memberNickname: '썽',
      createdDate: '2021-07-29-시-분-초',
      modifiedDate: '2021-07-29-시-분-초',
      content:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt',
      comments: [
        {
          memberPicture: '대댓글 작성자 사진',
          memberNickname: '썽',
          createdDate: '2021-07-29-시-분-초',
          modifiedDate: '2021-07-29-시-분-초',
          content: '대댓글 내용',
          comments: [],
        },
        {
          memberPicture: '대댓글 작성자 사진',
          memberNickname: '대댓글 작성자 닉네임',
          createdDate: '2021-07-29-시-분-초',
          modifiedDate: '2021-07-29-시-분-초',
          content: '대댓글 내용',
          comments: [],
        },
      ],
    },
    {
      memberPicture: '작성자 사진',
      memberNickname: '썽',
      createdDate: '2021-07-29-시-분-초',
      modifiedDate: '2021-07-29-시-분-초',
      content:
        'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt',
      comments: [
        {
          memberPicture: '대댓글 작성자 사진',
          memberNickname: '대댓글 작성자 닉네임',
          createdDate: '2021-07-29-시-분-초',
          modifiedDate: '2021-07-29-시-분-초',
          content: '대댓글 내용',
          comments: [],
        },
        {
          memberPicture: '대댓글 작성자 사진',
          memberNickname: '대댓글 작성자 닉네임',
          createdDate: '2021-07-29-시-분-초',
          modifiedDate: '2021-07-29-시-분-초',
          content: '대댓글 내용',
          comments: [],
        },
      ],
    },
  ]);

  useEffect(async () => {
    // const response = await OOTDCommentReadApi(ootdId);
    // if (response.status === 200) {
    //   setComments({ ...comments, ...response.comments });
    // }
  }, []);

  return (
    <footer className="detail-bottom">
      <div className="detail-comments-title">
        <h5>Comments</h5>
      </div>
      <div className="detail-comments">
        {comments.length !== 0 &&
          comments.map(comment => {
            return (
              <SingleComment
                key={comment.createdDate}
                depth="0"
                comment={comment}
                userInfo={userInfo}
              />
            );
          })}
      </div>
      <div className="detail-comment-write">
        <input
          className="detail-comment-input"
          type="text"
          placeholder="댓글 쓰기"
        />
        <IoMdSend className="detail-comment-send" size="30" />
      </div>
    </footer>
  );
};

export default OOTDBottom;
