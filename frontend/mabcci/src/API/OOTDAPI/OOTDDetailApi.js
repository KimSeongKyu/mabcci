import axios from 'axios';
import instance from '../indexMockSeongae';

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

export const OOTDCommentCreateApi = async () => {
  try {
    const response = await axios.post(OOTDCommentCreateUrl);
    return response;
  } catch (response) {
    return response;
  }
};

export const OOTDCommentReadApi = async id => {
  try {
    const response = await axios.get(`OOTDCommentReadUrl${id}`);
    return {
      status: response.status,
      comments: response.comments,
    };
  } catch (response) {
    return {
      status: response.status,
    };
  }
};
