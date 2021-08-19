import axios from 'axios';
import instance from '../index';

const accessToken = localStorage.getItem('accessToken');
export const chatListApi = async nickname => {
  const chatListUrl = `/api/chat/rooms/${nickname}`;
  // const mockUrl = `http://localhost/chat`;
  const response = await instance.get(chatListUrl);
  return response;
};

export const createChatRoomApi = async mabcci => {
  const createChatRoomUrl = `/api/chat/room`;
  const response = await instance.post(createChatRoomUrl, mabcci, {
    headers: {
      Authorization: accessToken,
    },
  });
};

export const sendMessageApi = async message => {
  console.log(message);
  const sendMessageUrl = '/pub/chat/message';
  const response = await instance.post(sendMessageUrl, message, {
    headers: {
      Authorization: accessToken,
    },
  });
  console.log(response);
};
