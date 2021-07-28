import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import SignupApi from '../../../../API/AuthAPI/SingupApi';
import 미니멀 from '../../../../Asset/Images/미니멀.png';
import 스트릿 from '../../../../Asset/Images/스트릿.png';
import 아메카지 from '../../../../Asset/Images/아메카지.png';
import 오피스 from '../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../Asset/Images/포멀.png';
import {
  SingupSelectStyle,
  // SingupSelectSex,
} from '../../../../Redux/Actions/SignupAction';

function SignupBottom() {
  const dispatch = useDispatch();
  const history = useHistory();
  const signupRedux = useSelector(state => state.SignupReducer);

  const handleSubmit = async e => {
    e.preventDefault();
    const data = signupRedux;
    console.log(signupRedux);
    const response = await SignupApi(data);

    if (response.status === 200) {
      history.push('/login');
    } else {
      alert('회원가입 실패');
    }
  };
  // const [selectSex, setSelectSex] = useState('');
  // 성별 선택 버튼 클릭
  function mwBtnClick(e) {
    e.target.classList.toggle('btn-rounded-man-woman-active');
    // console.log(e.target.name);
    // setSelectSex(e.target.name);
    // console.log(selectSex);
    // dispatch(SingupSelectSex(selectSex));
  }

  const [selectStyle, setSelectStyle] = useState([]);
  // 스타일 선택 버튼 클릭
  function styleBtnClick(e) {
    e.target.classList.toggle('btn-select-style-active');
    const copy = [...selectStyle];
    const idx = copy.indexOf(e.target.name);
    if (idx >= 0) {
      copy.splice(idx, 1);
    } else {
      copy.push(e.target.name);
    }
    setSelectStyle(copy);
    dispatch(SingupSelectStyle(copy));
  }

  return (
    <div>
      <h5>성별</h5>
      <div className="select-man-woman">
        <button
          className="btn-rounded-sm"
          type="submit"
          name="Man"
          onClick={mwBtnClick}
        >
          Man
        </button>
        <button
          className="btn-rounded-sm"
          type="submit"
          name="Woman"
          onClick={mwBtnClick}
        >
          Woman
        </button>
      </div>

      <h5>선호하는 스타일을 골라주세요!</h5>
      <div className="style-box">
        <div className="select-style">
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="미니멀"
              onClick={styleBtnClick}
            >
              <img src={미니멀} alt="미니멀" />
            </button>
            <p>미니멀</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="스트릿"
              onClick={styleBtnClick}
            >
              <img src={스트릿} alt="스트릿" />
            </button>
            <p>스트릿</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="아메카지"
              onClick={styleBtnClick}
            >
              <img src={아메카지} alt="아메카지" />
            </button>
            <p>아메카지</p>
          </div>
        </div>
        <div className="select-style">
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="오피스"
              onClick={styleBtnClick}
            >
              <img src={오피스} alt="오피스" />
            </button>
            <p>오피스</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="캐쥬얼"
              onClick={styleBtnClick}
            >
              <img src={캐쥬얼} alt="캐쥬얼" />
            </button>
            <p>캐쥬얼</p>
          </div>
          <div>
            <button
              className="btn-select-style"
              type="submit"
              name="포멀"
              onClick={styleBtnClick}
            >
              <img src={포멀} alt="포멀" />
            </button>
            <p>포멀</p>
          </div>
        </div>
      </div>

      <button type="submit" onClick={handleSubmit} className="btn-rounded">
        Sign up
      </button>
    </div>
  );
}

export default SignupBottom;
