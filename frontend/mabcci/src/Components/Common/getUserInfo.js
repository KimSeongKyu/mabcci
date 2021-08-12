import jwtDecode from 'jwt-decode';

const getUserInfo = () => {
  const accessToken = localStorage.getItem('accessToken');
  const decoded = jwtDecode(accessToken);
  const userInfo = {
    email: decoded.email,
    nickname: decoded.nickname,
    role: decoded.role,
  };

  return userInfo;
};

export default getUserInfo;
