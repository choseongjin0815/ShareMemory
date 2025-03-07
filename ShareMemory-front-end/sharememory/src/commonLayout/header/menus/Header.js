import React from "react";
import { Navbar, Nav, Button, Container } from "react-bootstrap";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import useCustomLogin from "../../../hooks/useCustomLogin";
import "../../../css/Header.css";

const Header = () => {
  const navigate = useNavigate();
  const loginState = useSelector((state) => state.loginSlice);

  const { doLogout, moveToPath } = useCustomLogin();

  const handleClickLogout = () => {
    doLogout();
    alert("로그아웃되었습니다.");
    moveToPath("/");
  };

  return (
    <>
      <Navbar bg="dark" variant="dark" expand="lg" collapseOnSelect>
        <Container>
          <Navbar.Brand onClick={() => navigate("/")} className="brand-title">
            ShareMemory
          </Navbar.Brand>

          {loginState.userId && (
            <Navbar.Collapse>
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
            </Navbar.Collapse>
          )}

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