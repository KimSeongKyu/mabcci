import { OOTDAll } from '../Actions/OOTDAction';

const initialState = {
  ootd: [
    { img: 'abc', content: 'test1' },
    { img: 'abc', content: 'test2' },
    { img: 'abc', content: 'test3' },
    { img: 'abc', content: 'test4' },
    { img: 'abc', content: 'test5' },
    { img: 'abc', content: 'test6' },
  ],
};

const OotdReducer = (state = initialState, { type, payload }) => {
  switch (type) {
    case OOTDAll: {
      // 복사후 받아온 ootd 피드들을 push
      const feed = { ...state };
      feed.ootd.push(payload);
      return feed;
    }
    default:
      return state;
  }
};

export default OotdReducer;
