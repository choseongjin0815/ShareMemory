import React from "react";
import { Offcanvas, Nav } from "react-bootstrap";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const Sidebar = ({ showSidebar, setShowSidebar }) => {
  const loginState = useSelector((state) => state.loginSlice);

  return (
    <Offcanvas
      show={showSidebar}
      onHide={() => setShowSidebar(false)}
      placement="start" // 좌측 슬라이드
    >
      <Offcanvas.Header closeButton>
        <Offcanvas.Title>My Diary</Offcanvas.Title>
      </Offcanvas.Header>
      <Offcanvas.Body>
        <Nav className="flex-column">
          <Nav.Link as={Link} to="/" className="text-dark" onClick={() => setShowSidebar(false)}>
            🏠 홈
          </Nav.Link>
          <Nav.Link as={Link} to="/diary-list" className="text-dark" onClick={() => setShowSidebar(false)}>
            📚 일기 목록
          </Nav.Link>
          <Nav.Link as={Link} to="/new-diary" className="text-dark" onClick={() => setShowSidebar(false)}>
            ✏️ 새 일기 작성
          </Nav.Link>
          <Nav.Link as={Link} to="/settings" className="text-dark" onClick={() => setShowSidebar(false)}>
            ⚙️ 설정
          </Nav.Link>
          {loginState.userId ? (
            <Nav.Link as={Link} to="/logout" className="text-dark" onClick={() => setShowSidebar(false)}>
              🔒 로그아웃
            </Nav.Link>
          ) : (
            <Nav.Link as={Link} to="/user/login" className="text-dark" onClick={() => setShowSidebar(false)}>
              🔑 로그인
            </Nav.Link>
          )}
        </Nav>
      </Offcanvas.Body>
    </Offcanvas>
  );
};

export default Sidebar;