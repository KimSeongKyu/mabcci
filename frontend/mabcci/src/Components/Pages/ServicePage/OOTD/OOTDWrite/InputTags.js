/* eslint-disable */

import React, { useState, useEffect } from 'react';
import './InputTags.css';
import { MdCancel } from "react-icons/md"
import { GiConsoleController } from 'react-icons/gi';

const Tag = ({ txt, idx, send, tagColor }) => {
  const removeTag = () => {
    send(idx);
  };

  return (
    <span className="tags has-addons">
      <span className="tag is-link" style={{ backgroundColor: tagColor }}>
        {txt}
        <MdCancel onClick={removeTag} />
      </span>
    </span>
  );
};

export default function InputTags({ onTag, placeHolder, tagColor, hashTag }) {
  const [tags, setTags] = useState(['asdf', 'asdf']);
  console.log(hashTag);

  // useEffect(() => {
  //   setTags(hashTag);
  //   onTag(tags);
  // }, []);
  
  useEffect(() => {
    onTag(tags);
  }, [tags, setTags]);

  const addTag = e => {
    if (
      e.keyCode === 32 &&
      e.target.value.trim().length > 0 &&
      tags.length <= 19
    ) {
      const tag = e.target.value.trim();
      setTags([...tags, tag]);
      e.target.value = '';
    }
  };

  const getChildVal = idx => {
    setTags(tags.filter((tag, i) => i !== idx));
  };

  return (
    <div className="tag-container">
      {tags.map((t, i) => (
        <Tag
          txt={t}
          send={getChildVal}
          key={t + i}
          idx={i}
          tagColor="{tagColor}"
        />
      ))}
      <input
        className="input-tags"
        type="text"
        onKeyUp={addTag}
        placeholder="Press spacebar to add tag"
        required
      />
    </div>
  );
}
