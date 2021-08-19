import axios from 'axios';
import instance from '../index';

export const chatListApi = async () => {
  const mockUrl = `http://localhost/chat`;
  const response = await instance.get(mockUrl);
  return response;
};

export const createChatRoomApi = async mabcci => {
  const createChatRoomUrl = `api/chat/room`;
};
