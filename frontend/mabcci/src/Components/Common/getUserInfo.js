import jwtDecode from 'jwt-decode';

const getUserInfo = () => {
  const accessToken = localStorage.getItem('accessToken');

  if (accessToken) {
    const decoded = jwtDecode(accessToken);
    const userInfo = {
      email: decoded.email,
      nickname: decoded.nickname,
      role: decoded.role,
    };

    return userInfo;
  }

  return null;
};

export default getUserInfo;
