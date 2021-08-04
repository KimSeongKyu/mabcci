import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';

const OOTDTest = () => {
  const history = useHistory();

  const id = 1;
  const nickname = 'tjddo';

  const [detail, setDetail] = useState({
    content: '내용무',
    top: '상의',
    bottom: '바지',
    shoes: '신발',
    accessory: 'ㅇㅇ',
    picture: [],
    hashTag: ['하이', '해시', '태그'],
  });

  const updateOOTD = () => {
    history.push({
      pathname: `/OOTDUpdate/${id}/${nickname}`,
      state: { detail },
    });
  };

  return (
    <div>
      <p>updateTest</p>
      <button type="submit" onClick={updateOOTD}>
        수정!
      </button>
    </div>
  );
};

export default OOTDTest;
