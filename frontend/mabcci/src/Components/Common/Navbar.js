import React from 'react';
import './Nav.css';
import { Row, Col } from 'react-bootstrap';
import { AiOutlineLogin, AiOutlineHome } from 'react-icons/ai';
import { FiUser } from 'react-icons/fi';
import { FaRegComments } from 'react-icons/fa';
import { BsImages } from 'react-icons/bs';
import { MdAccountCircle, MdAccessAlarm } from 'react-icons/md';
import { IoShirtOutline } from 'react-icons/io5';
import { Link } from 'react-router-dom';
import logo from '../../Asset/Images/logo.png';

function Nav() {
  return (
    <div>
      <div className="web-navbar">
        <div className="logo-bar">
          <Link to="home">
            <img className="logo-image" src={logo} alt="로고" />
          </Link>
          <div className="btn-login">
            <Link to="login">
              <AiOutlineLogin /> Login
            </Link>
            <MdAccessAlarm />
            <Link to="MyPage">
              <MdAccountCircle />
            </Link>
          </div>
        </div>
        <div className="menu-bar">
          <div className="menu-link">
            <Row>
              <Col>
                <Link to="home" className="btn-link">
                  Home
                </Link>
              </Col>
              <Col>
                <Link to="OOTD" className="btn-link">
                  OOTD
                </Link>
              </Col>
              <Col>
                <Link to="styling" className="btn-link">
                  Styling
                </Link>
              </Col>
              <Col>
                <Link to="Community" className="btn-link">
                  커뮤니티
                </Link>
              </Col>
            </Row>
          </div>
        </div>
      </div>

      <div className="mobile-navbar">
        <div className="mobile-menu-bar">
          <div className="mobile-menu-link">
            <Row>
              <Col>
                <Link to="home" className="btn-link">
                  <div>
                    <AiOutlineHome />
                    <p>홈</p>
                  </div>
                </Link>
              </Col>
              <Col>
                <Link to="OOTD" className="btn-link">
                  <BsImages />
                  <p>OOTD</p>
                </Link>
              </Col>

              <Col>
                <Link to="styling" className="btn-link">
                  <IoShirtOutline />
                  <p>스타일링</p>
                </Link>
              </Col>
              <Col>
                <Link to="community" className="btn-link">
                  <FaRegComments />
                  <p>커뮤니티</p>
                </Link>
              </Col>
              <Col>
                <Link to="mypage" className="btn-link">
                  <FiUser />
                  <p>내정보</p>
                </Link>
              </Col>
            </Row>
          </div>
        </div>
      </div>
    </div>
  );
}

export default Nav;
