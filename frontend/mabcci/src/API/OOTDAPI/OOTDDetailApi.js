import axios from 'axios';
import instance from '../index';

import {
  OOTDDetailUrl,
  OOTDCommentCreateUrl,
  OOTDCommentUpdateUrl,
  OOTDCommentDeleteUrl,
  OOTDCommentReadUrl,
  OOTDLikeUrl,
} from '../ApiUrl';

export const OOTDDetailApi = async id => {
  try {
    const response = await instance.get(`${OOTDDetailUrl}${id}`);

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
    const response = await instance.get(`${OOTDCommentReadUrl}${id}`);
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
    console.log(updateComment);
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
