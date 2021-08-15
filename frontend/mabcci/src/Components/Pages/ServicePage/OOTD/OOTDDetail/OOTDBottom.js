import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { IoMdSend } from 'react-icons/io';
import {
  OOTDCommentReadApi,
  OOTDCommentCreateApi,
  OOTDCommentDeleteApi,
  OOTDCommentUpdateApi,
} from '../../../../../API/OOTDAPI/OOTDDetailApi';
import userphoto from './Images/userphoto.png';

export const SingleComment = props => {
  const { depth, comment, allComments, userInfo } = props;
  const { commentWrite, commentDelete, commentUpdate } = props;
  const replyComments = allComments.filter(otherComment => {
    return comment.id === otherComment.parentId;
  });
  const [replyCotent, setReplyCotent] = useState('');
  const [replyToggle, setReplyToggle] = useState(false);
  const [updateContent, setUpdateContent] = useState(comment.content);
  const [updateToggle, setUpdateToggle] = useState(false);

  return (
    <div className="detail-singlecomment">
      <div className="detail-comment">
        <div className="detail-comment-info">
          <div className="detail-comment-info-photo">
            <img src={userphoto} alt="UserImage" width="70" />
          </div>
          {updateToggle ? null : (
            <div className="detail-comment-info-content">
              <p>{comment.memberNickname}</p>
              <p>{comment.createdDate}</p>
              {depth === '0' ? (
                <button
                  type="button"
                  className="detail-comment-info-button"
                  onClick={() => setReplyToggle(!replyToggle)}
                >
                  답글
                </button>
              ) : null}
              {comment.memberNickname === userInfo.nickname ? (
                <>
                  <button
                    type="button"
                    className="detail-comment-info-button"
                    onClick={() => setUpdateToggle(!updateToggle)}
                  >
                    수정
                  </button>
                  <button
                    type="button"
                    className="detail-comment-info-button"
                    onClick={() => commentDelete(comment.id)}
                  >
                    삭제
                  </button>
                </>
              ) : null}
            </div>
          )}
        </div>
        {updateToggle ? (
          <div className="detail-comment-updateContent">
            <input
              type="text"
              value={updateContent}
              onChange={e => setUpdateContent(e.target.value)}
            />
            <button
              type="button"
              onClick={() => {
                commentUpdate(comment.id, userInfo.nickname, updateContent);
                setUpdateToggle(!updateToggle);
              }}
            >
              저장
            </button>
          </div>
        ) : (
          <div className="detail-comment-content">{comment.content}</div>
        )}
      </div>
      {depth === '0' ? (
        <div className="detail-reply">
          {replyToggle ? (
            <div className="detail-reply-comment-write">
              <input
                className="detail-reply-comment-input"
                type="text"
                placeholder="댓글 쓰기"
                value={replyCotent}
                onChange={e => {
                  setReplyCotent(e.target.value);
                }}
                onKeyPress={e => {
                  if (e.key === 'Enter') {
                    commentWrite(replyCotent, comment.id);
                    setReplyCotent('');
                  }
                }}
              />
              <IoMdSend
                className="detail-comment-send"
                size="30"
                onClick={() => {
                  commentWrite(replyCotent, comment.id);
                  setReplyCotent('');
                }}
              />
            </div>
          ) : null}
          <div className="detail-reply-comment">
            {replyComments.length !== 0 &&
              replyComments.map(reply => {
                return (
                  <SingleComment
                    key={reply.id}
                    depth={depth + 1}
                    userInfo={userInfo}
                    comment={reply}
                    allComments={allComments}
                    commentWrite={commentWrite}
                    commentDelete={commentDelete}
                    commentUpdate={commentUpdate}
                  />
                );
              })}
          </div>
        </div>
      ) : null}
    </div>
  );
};

const OOTDBottom = props => {
  const { ootdId, userInfo } = props;
  const [allComments, setAllComments] = useState([]);
  const [comments, setComments] = useState([]);
  const [commentCotent, setCommentCotent] = useState('');

  const commentRead = async () => {
    const response = await OOTDCommentReadApi(ootdId);

    if (response.status === 200) {
      setAllComments([...response.comments]);
      setComments(
        response.comments.filter(comment => {
          return comment.parentId === 0;
        }),
      );
    }
  };

  useEffect(async () => {
    await commentRead();
  }, []);

  const commentWrite = async (content, parentCommentId) => {
    const newComment = {
      ootdId,
      nickname: userInfo.nickname,
      parentCommentId,
      content,
    };

    await OOTDCommentCreateApi(newComment);
    await commentRead();
  };

  const commentUpdate = async (commentId, nickname, content) => {
    const updateComment = {
      nickname,
      content,
    };

    await OOTDCommentUpdateApi(commentId, updateComment);
    await commentRead();
  };

  const commentDelete = async commentId => {
    await OOTDCommentDeleteApi(commentId);
    await commentRead();
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
                userInfo={userInfo}
                comment={comment}
                allComments={allComments}
                commentWrite={commentWrite}
                commentDelete={commentDelete}
                commentUpdate={commentUpdate}
              />
            );
          })}
      </div>
      <div className="detail-comment-write">
        <input
          className="detail-comment-input"
          type="text"
          placeholder="댓글 쓰기"
          value={commentCotent}
          onChange={e => {
            setCommentCotent(e.target.value);
          }}
          onKeyPress={e => {
            if (e.key === 'Enter') {
              commentWrite(commentCotent, 0);
              setCommentCotent('');
            }
          }}
        />
        <IoMdSend
          className="detail-comment-send"
          size="30"
          onClick={() => {
            commentWrite(commentCotent, 0);
            setCommentCotent('');
          }}
        />
      </div>
    </footer>
  );
};

export default OOTDBottom;
