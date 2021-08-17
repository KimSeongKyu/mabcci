import React from 'react';
import top from './Images/상의.jpg';
import bottom from './Images/바지.jpg';
import shoes from './Images/신발.jpg';
import acc from './Images/악세서리.jpg';

const SuggestionItem = () => {
  return (
    <article className="suggestion-item">
      <img src={top} alt="상의" className="suggestion-cloth suggestion-top" />
      <img
        src={bottom}
        alt="하의"
        className="suggestion-cloth suggestion-bottom"
      />
      <img
        src={bottom}
        alt="하의"
        className="suggestion-cloth suggestion-bottom"
      />
      <img
        src={shoes}
        alt="신발"
        className="suggestion-cloth suggestion-shoes"
      />
      <img
        src={acc}
        alt="액세서리"
        className="suggestion-cloth suggestion-acc"
      />
    </article>
  );
};

export default SuggestionItem;
