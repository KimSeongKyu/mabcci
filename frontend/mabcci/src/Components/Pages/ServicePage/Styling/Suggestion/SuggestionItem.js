import React from 'react';
import { useSelector } from 'react-redux';
import { baseUrl } from '../../../../../API/ApiUrl';

import top from './Images/tmpTop.png';
import bottom from './Images/tmpBottom.png';
import shoes from './Images/tmpShoes.png';
import acc from './Images/tmpAcc.png';

const SuggestionItem = () => {
  const suggestion = useSelector(state => state.SuggestionReducer);
  console.log(suggestion.top);
  return (
    <div>
      {suggestion ? (
        <article className="suggestion-item">
          <img
            src={suggestion.top ? baseUrl + suggestion.top : top}
            alt="상의"
            className="suggestion-cloth suggestion-top"
          />
          <img
            src={suggestion.bottom ? baseUrl + suggestion.bottom : bottom}
            alt="하의"
            className="suggestion-cloth suggestion-bottom"
          />
          <img
            src={suggestion.shoes ? baseUrl + suggestion.shoes : shoes}
            alt="신발"
            className="suggestion-cloth suggestion-shoes"
          />
          <img
            src={suggestion.acc ? baseUrl + suggestion.acc : acc}
            alt="액세서리"
            className="suggestion-cloth suggestion-acc"
          />
        </article>
      ) : null}
    </div>
  );
};

export default SuggestionItem;
