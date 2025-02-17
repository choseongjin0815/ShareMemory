import { Button } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import BasicLayout from '../commonLayout/layouts/BasicLayout';
import { useNavigate } from 'react-router-dom';

import "../css/MainPage.css";

const MainPage = () => {
  const navigate = useNavigate();

  const handleClickStart = () => {
    navigate("/user/login");
  };

  const main = (
    <div className="d-flex flex-column justify-content-center align-items-center" style={{ height: "100vh" }}>
      <div className="animated-text">
        <p className="display-4 text-center animated-message">
          당신의 기억을 기록하세요!
        </p>
      </div>
      <Button
        variant="primary"
        className="start-button"
        onClick={handleClickStart}
      >
        시작하기
      </Button>
    </div>
  );

  return <BasicLayout main={main} />;
};

export default MainPage;