import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import Header from "../header/menus/Header";
import Footer from "../footer/footer";
import { Outlet } from "react-router-dom";

const BasicLayout = ({ side, main}) => {
  return (
    <div className="d-flex flex-column min-vh-100">
      {/* Header 컴포넌트 */}
      <Header />

      <Container fluid className="p-0">
        <Row className="d-flex justify-content-center align-items-center" style={{ minHeight: "100vh" }}>
          {/* 사이드 메뉴 */}
          {side ? (
            <Col
              md={3}
              className="p-3 border-right"
              style={{
                backgroundColor: "#f8f9fa", // 사이드 메뉴 배경색
                boxShadow: "2px 0 5px rgba(0, 0, 0, 0.1)", // 그림자 효과
                height: "100vh", // 전체 화면 높이를 채움
              }}
            >
              {side}
            </Col>
          ) : null}

          {/* 메인 콘텐츠 영역 */}
          <Col
            md={9}
            className="p-4"
            style={{
              minHeight: "100vh", // 전체 화면 높이를 채움
              paddingTop: "120px", // 헤더 공간 확보
            }}
          >
            {main}
          </Col>
        </Row>
      
      </Container>
      <Footer />
      
    </div>
  );
};

export default BasicLayout;