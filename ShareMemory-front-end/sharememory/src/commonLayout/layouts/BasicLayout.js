import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import Header from "../header/menus/Header";
import Footer from "../footer/footer";
import "../../css/BasicLayout.css"

const BasicLayout = ({ side, main }) => {
  return (
    <div className="d-flex flex-column min-vh-100">
      {/* Header 컴포넌트 */}
      <Header />

      <Container fluid className="p-0" >
        <Row className="d-flex justify-content-center align-items-center" style={{ minHeight: "calc(100vh - 200px)" }}>
          {/* 사이드 메뉴 */}
          {side ? (
            <Col
              md={3}
              className="p-3 border-right"
              style={{
                position: "fixed", // 사이드 메뉴 고정
                top: "100px", // 헤더 아래에서 조금 더 위로 올림
                left: "0", // 왼쪽에 고정
            
                backgroundColor: "#f8f9fa", // 사이드 메뉴 배경색
                boxShadow: "2px 0 5px rgba(0, 0, 0, 0.1)", // 그림자 효과
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
              minHeight: "calc(100vh - 200px)", // 전체 화면 높이를 채우되 푸터 공간을 제외
              paddingTop: "100px", // 헤더와 더 가까워지도록 조금 더 줄임
              marginLeft: side ? "25%" : "0", // 사이드 메뉴가 있을 때는 왼쪽 마진을 줘서 콘텐츠가 겹치지 않도록 설정
              paddingBottom: "60px", // 푸터 공간 확보
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