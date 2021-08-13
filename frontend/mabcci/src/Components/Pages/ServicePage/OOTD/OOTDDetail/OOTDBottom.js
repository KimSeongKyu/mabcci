import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { IoMdSend } from 'react-icons/io';
import {
  OOTDCommentReadApi,
  OOTDCommentCreateApi,
} from '../../../../../API/OOTDAPI/OOTDDetailApi';
import userphoto from './Images/userphoto.png';

export const SingleComment = props => {
  const { depth, comment, allComments, userInfo, commentWrite } = props;
  const replyComments = allComments.filter(otherComment => {
    return comment.id === otherComment.parentId;
  });
  const [replyCotent, setReplyCotent] = useState('');

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
                답글
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
          <div className="detail-reply-comment-write">
            <input
              className="detail-reply-comment-input"
              type="text"
              placeholder="댓글 쓰기"
              onChange={e => {
                setReplyCotent(e.target.value);
              }}
            />
            <IoMdSend
              className="detail-comment-send"
              size="30"
              onClick={() => commentWrite(replyCotent, comment.id)}
            />
          </div>
          {replyComments.length !== 0 &&
            replyComments.map(reply => {
              return (
                <SingleComment
                  key={reply.id}
                  depth={depth + 1}
                  comment={reply}
                  allComments={allComments}
                  userInfo={userInfo}
                />
              );
            })}
        </div>
      ) : null}
    </div>
  );
};

const OOTDBottom = props => {
  const { ootdId, userInfo } = props;
  // const [allComments, setAllComments] = useState([
  //   {
  //     memberPicture: '댓글 작성자',
  //     memberNickname: '댓글 작성자 닉네임',
  //     createdDate: '2021-07-27-시-분-초',
  //     modifiedDate: '2021-07-27-시-분-초',
  //     content: '댓글 내용',
  //     id: '1',
  //     parentId: '',
  //   },
  //   {
  //     memberPicture: '대댓글 작성자',
  //     memberNickname: '대댓글 작성자 닉네임',
  //     createdDate: '2021-07-28-시-분-초',
  //     modifiedDate: '2021-07-28-시-분-초',
  //     content: '대댓글 내용',
  //     id: '2',
  //     parentId: '1',
  //   },
  //   {
  //     memberPicture: '댓글 작성자',
  //     memberNickname: '댓글 작성자 닉네임',
  //     createdDate: '2021-07-27-시-분-초',
  //     modifiedDate: '2021-07-27-시-분-초',
  //     content: '댓글 내용',
  //     id: '3',
  //     parentId: '',
  //   },
  //   {
  //     memberPicture: '댓글 작성자',
  //     memberNickname: '댓글 작성자 닉네임',
  //     createdDate: '2021-07-27-시-분-초',
  //     modifiedDate: '2021-07-27-시-분-초',
  //     content: '댓글 내용',
  //     id: '4',
  //     parentId: '1',
  //   },
  // ]);
  const [allComments, setAllComments] = useState([]);
  const comments = allComments.filter(comment => {
    return comment.parentId === '';
  });
  const [commentCotent, setCommentCotent] = useState('');

  useEffect(async () => {
    const response = await OOTDCommentReadApi(ootdId);
    if (response.status === 200) {
      setAllComments({ ...comments, ...response.comments });
    }
  }, []);

  const commentWrite = (content, parentCommentId) => {
    const newComment = {
      ootdId,
      nickname: userInfo.nickname,
      parentCommentId,
      content,
    };

    console.log(newComment);
  };

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
                key={comment.id}
                depth="0"
                comment={comment}
                allComments={allComments}
                userInfo={userInfo}
                commentWrite={commentWrite}
              />
            );
          })}
      </div>
      <div className="detail-comment-write">
        <input
          className="detail-comment-input"
          type="text"
          placeholder="댓글 쓰기"
          onChange={e => {
            setCommentCotent(e.target.value);
          }}
        />
        <IoMdSend
          className="detail-comment-send"
          size="30"
          onClick={() => commentWrite(commentCotent, null)}
        />
      </div>
    </footer>
  );
};

export default OOTDBottom;
