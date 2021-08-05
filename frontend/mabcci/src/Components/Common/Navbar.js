import React, { useEffect, useState } from 'react';
import { Link, useHistory } from 'react-router-dom';
import { useSelector, useDispatch } from 'react-redux';
import './Nav.css';
import { AiOutlineLogin, AiOutlineHome } from 'react-icons/ai';

import { FiUser } from 'react-icons/fi';
import { FaRegComments } from 'react-icons/fa';
import { BsImages } from 'react-icons/bs';
import { MdAccountCircle, MdAccessAlarm } from 'react-icons/md';
import { IoShirtOutline } from 'react-icons/io5';
import LogoutApi from '../../API/AuthAPI/LogoutApi';
import { Logout } from '../../Redux/Actions/LoginAction';
import logo from '../../Asset/Images/logo.png';

function Nav() {
  const history = useHistory();
  const dispatch = useDispatch();

  const [isLoggedin, setisLoggedin] = useState(false);
  useEffect(() => {
    const localLoinToken = localStorage.getItem('accessToken');
    if (localLoinToken) {
      setisLoggedin(true);
    } else {
      setisLoggedin(false);
    }
  });

  const loginRedux = useSelector(state => state.LoginReducer);

  const [popover, setpopover] = useState(false);

  const openPopover = () => {
    setpopover(!popover);
  };

  const [nowMenu, setNowMenu] = useState('');

  const selectMenu = e => {
    setNowMenu(e.target.name);
  };

  const LogOut = () => {
    localStorage.clear();
    dispatch(Logout());
    history.push('/intro');
  };

  const goMypage = () => {
    const myInfo = JSON.parse(localStorage.getItem('userInfo'));
    setpopover(!popover);
    history.push(`/mypage/${myInfo.nickname}`);
  };

  return (
    <div>
      <div className="navbar-web">
        {loginRedux.isLoggedin}
        <div className="navbar-logo-bar">
          <Link to="/home">
            <img className="navbar-logo-image" src={logo} alt="로고" />
          </Link>
          {isLoggedin ? (
            <div className="navbar-web-icon">
              <MdAccessAlarm />
              <MdAccountCircle className="mypage" onClick={openPopover} />
            </div>
          ) : (
            <Link to="/login">
              <AiOutlineLogin /> Login
            </Link>
          )}

          {popover ? (
            <div className="navbar-popover-box">
              <button type="submit" onClick={LogOut}>
                <p>로그아웃</p>
              </button>
              <button type="submit" onClick={goMypage}>
                <p>마이페이지</p>
              </button>
            </div>
          ) : null}
        </div>
        <div className="navbar-menu-bar">
          <div className="navbar-menu-link">
            <Link
              to="home"
              onClick={selectMenu}
              name="home"
              id={nowMenu === 'home' ? 'navbar-menu-link-selected' : null}
            >
              Home
            </Link>
            <Link
              to="/OOTD"
              onClick={selectMenu}
              name="OOTD"
              id={nowMenu === 'OOTD' ? 'navbar-menu-link-selected' : null}
            >
              OOTD
            </Link>
            <Link
              to="/styling"
              onClick={selectMenu}
              name="Styling"
              id={nowMenu === 'Styling' ? 'navbar-menu-link-selected' : null}
            >
              Styling
            </Link>
            <Link
              to="/Community"
              onClick={selectMenu}
              name="Community"
              id={nowMenu === 'Community' ? 'navbar-menu-link-selected' : null}
            >
              Community
            </Link>
          </div>
        </div>
      </div>

      <div id="navbar-mobile">
        <Link to="/home">
          <div className="navbar-mobile-btn">
            <AiOutlineHome />
            <p name="home">HOME</p>
          </div>
        </Link>
        <Link to="/OOTD">
          <div className="navbar-mobile-btn">
            <BsImages />
            <p>OOTD</p>
          </div>
        </Link>
        <Link to="/styling">
          <div className="navbar-mobile-btn">
            <IoShirtOutline />
            <p>스타일링</p>
          </div>
        </Link>
        <Link to="/community">
          <div className="navbar-mobile-btn">
            <FaRegComments />
            <p>커뮤니티</p>
          </div>
        </Link>
        <Link to="/mypage">
          <div className="navbar-mobile-btn">
            <FiUser />
            <p>내 정보</p>
          </div>
        </Link>
      </div>
    </div>
  );
}

export default Nav;
