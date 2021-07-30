import axios from 'axios';

import {
  OOTDDetailUrl,
  OOTDCommentCreateUrl,
  OOTDCommentUpdateUrl,
  OOTDCommentDeleteUrl,
  OOTDCommentReadUrl,
  OOTDLikeUrl,
} from '../ApiUrl';

export const OOTDDetailApi = async () => {
  try {
    const response = await axios.get(OOTDDetailUrl);
    return {};
  } catch (response) {
    return {};
  }
};

export const OOTDCommentCreateApi = async () => {
  try {
    const response = await axios.get(OOTDCommentCreateUrl);
    return {};
  } catch (response) {
    return {};
  }
};
