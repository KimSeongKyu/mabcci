import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import SignupApi from '../../../../API/AuthAPI/SignupApi';
import 미니멀 from '../../../../Asset/Images/미니멀.png';
import 스트릿 from '../../../../Asset/Images/스트릿.png';
import 아메카지 from '../../../../Asset/Images/아메카지.png';
import 오피스 from '../../../../Asset/Images/오피스.png';
import 캐쥬얼 from '../../../../Asset/Images/캐쥬얼.png';
import 포멀 from '../../../../Asset/Images/포멀.png';
import {
  SingupSelectStyle,
  SingupSelectGender,
} from '../../../../Redux/Actions/SignupAction';

function SignupBottom() {
  const dispatch = useDispatch();
  const history = useHistory();
  const signupRedux = useSelector(state => state.SignupReducer);

  // 회원정보가 다 입력되어 있는지 확인

  const handleSubmit = async e => {
    e.preventDefault();
    const data = signupRedux;
    const isEmpty = Object.values(data).some(x => x === '' || x.length === 0);
    console.log(data);

    if (isEmpty === true) {
      alert('회원정보를 모두 입력해주세요');
    } else {
      console.log(data, '회원가입누르면');
      const response = await SignupApi(signupRedux);
      if (response.status === 200) {
        history.push('/login');
      } else {
        alert('회원가입 실패');
      }
    }
  };

  const [selectGender, setSelectGender] = useState('');
  // 성별 선택 버튼 클릭
  function mwBtnClick(e) {
    e.target.classList.toggle('btn-rounded-man-woman-active');
    setSelectGender(e.target.name);
    dispatch(SingupSelectGender(e.target.name));
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
    const data = signupRedux;
    console.log(data);
  }

  return (
    <div>
      <h5>성별</h5>
      <div className="select-man-woman">
        {selectGender === 'MALE' ? (
          <button
            className="btn-sex-select"
            type="submit"
            name="MALE"
            onClick={mwBtnClick}
          >
            Man
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            type="submit"
            name="MALE"
            onClick={mwBtnClick}
          >
            Man
          </button>
        )}

        {selectGender === 'FEMALE' ? (
          <button
            className="btn-sex-select"
            type="submit"
            name="FEMALE"
            onClick={mwBtnClick}
          >
            Woman
          </button>
        ) : (
          <button
            className="btn-rounded-sm"
            type="submit"
            name="FEMALE"
            onClick={mwBtnClick}
          >
            Woman
          </button>
        )}
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
