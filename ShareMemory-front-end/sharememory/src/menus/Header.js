import React, { useState } from "react";
import { Navbar, Nav, Button, Container } from "react-bootstrap";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import { FiMenu } from "react-icons/fi";
import useCustomLogin from "../hooks/useCustomLogin";
import "../css/Header.css";

const Header = () => {
  const navigate = useNavigate();
  const loginState = useSelector((state) => state.loginSlice);

  const { doLogout, moveToPath } = useCustomLogin();

  const [showSidebar, setShowSidebar] = useState(false); // 사이드바 상태 관리
  const handleClickLogout = () => {
    doLogout();
    alert("로그아웃되었습니다.");
    moveToPath("/");
  };

  return (
    <>
      <Navbar bg="dark" variant="dark" expand="lg">
        <Container>
          {/* 좌측 끝 토글 버튼
          <Button
            variant="dark"
            onClick={() => setShowSidebar(true)}
            className="me-2"
          >
            <FiMenu size={24} color="white" />
          </Button> */}

          <Navbar.Brand onClick={() => navigate("/")} className="brand-title">
            ShareMemory
          </Navbar.Brand>

          {/* 중앙 메뉴 항목들 (메모리, 친구 등) */}
          <Nav className="menu-items">
            <Button
              variant="link"
              className="nav-item"
              onClick={() => navigate("/diary")}
            >
              Memories
            </Button>
            <Button
              variant="link"
              className="nav-item"
              onClick={() => navigate("/friends")}
            >
              Friends
            </Button>
          </Nav>

          {/* 오른쪽 로그인 정보 / 버튼 */}
          <Nav className="ms-auto">
            {loginState.userId ? (
              <>
                <Navbar.Text className="me-3">
                  {loginState.nickname}님 환영합니다
                </Navbar.Text>
                <Button variant="outline-light" onClick={handleClickLogout}>
                  로그아웃
                </Button>
              </>
            ) : (
              <Button
                variant="outline-light"
                onClick={() => navigate("/user/login")}
              >
                로그인
              </Button>
            )}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
};

export default Header;