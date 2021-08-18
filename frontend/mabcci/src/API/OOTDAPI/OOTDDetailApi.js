import instance from '../index';

import {
  OOTDDetailUrl,
  OOTDCommentCreateUrl,
  OOTDCommentUpdateUrl,
  OOTDCommentDeleteUrl,
  OOTDCommentReadUrl,
  OOTDLikeUrl,
} from '../ApiUrl';

export const OOTDDetailApi = async (id, nickname) => {
  try {
    const response = await instance.get(
      `${OOTDDetailUrl}${id}/detail/${nickname}`,
    );

    return {
      status: response.status,
      detail: response.data,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export const OOTDCommentCreateApi = async newComment => {
  try {
    const response = await instance.post(OOTDCommentCreateUrl, newComment);
    return response;
  } catch (response) {
    return response;
  }
};

export const OOTDCommentReadApi = async id => {
  try {
    const response = await instance.get(`${OOTDCommentReadUrl}${id}/comments`);
    return {
      status: response.status,
      comments: response.data.comments,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};

export const OOTDCommentUpdateApi = async (id, updateComment) => {
  try {
    const response = await instance.put(
      `${OOTDCommentUpdateUrl}${id}`,
      updateComment,
    );
    return response;
  } catch (response) {
    return response;
  }
};

export const OOTDCommentDeleteApi = async id => {
  try {
    const response = await instance.delete(`${OOTDCommentDeleteUrl}${id}`);
    return response;
  } catch (response) {
    return response;
  }
};

export const OOTDLikeApi = async (id, nickname) => {
  try {
    const response = await instance.post(
      `${OOTDLikeUrl}${id}/like/${nickname}`,
    );
    return response;
  } catch (response) {
    return response;
  }
};
