// src/components/Layout.js
import React from "react";
import { Container, Row, Col } from "react-bootstrap";
import Sidebar from "../menus/SideBar";
import Header from "../menus/Header";


const BasicLayout = (children) => {
  return (
    <div>
        {/* Header 컴포넌트 */}
      <Header />

      <Container fluid className="d-flex">

        {/* Main Content */}
        <main className="flex-grow-1 p-4">
            {children.text}
        </main>
      </Container>
    </div>
  );
};

export default BasicLayout;