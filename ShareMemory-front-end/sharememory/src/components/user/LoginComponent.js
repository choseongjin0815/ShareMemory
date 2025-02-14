import { useState } from "react";
import { Button, Card, Form, Col, Row, Container} from "react-bootstrap";
import useCustomLogin from "../../hooks/useCustomLogin";

const initState = {
    userId : '',
    pwd : ''
}

const LoginComponent = () => {
    const[loginParam, setLoginParam] = useState({...initState})
    const{doLogin, moveToPath} = useCustomLogin()

    const handleChange = (e) => {
        loginParam[e.target.name] = e.target.value

        setLoginParam({...loginParam})
    }

    const handleClickLogin = (e) => {

        doLogin(loginParam) // loginSlice의 비동기 호출 
        .then(data => {
          console.log("test", data)
          console.log(data.error)
          if(data.error) {
            alert("아이디와 패스워드를 다시 확인하세요")
          }else {
            alert("로그인 성공")
            moveToPath('/')
          }
        })
      }

      return (

          <Container className="d-flex justify-content-center align-items-center" 
                style={{ minHeight: '100vh' }}>
            <Row>
              <Col>
                <Card className="p-4 shadow-sm">
                  <Card.Body>
                    <h3 className="text-center mb-4">로그인</h3>
                    <Form>
                      <Form.Group className="mb-3" controlId="formEmail">
                        <Form.Label>이메일</Form.Label>
                        <Form.Control type="email" name="userId" value={loginParam.userId} 
                        onChange={handleChange} placeholder="이메일 입력" />
                      </Form.Group>

                      <Form.Group className="mb-3" controlId="formPassword">
                        <Form.Label>비밀번호</Form.Label>
                        <Form.Control type="password" name="pwd" value={loginParam.password}
                        placeholder="비밀번호 입력" onChange={handleChange}/>
                      </Form.Group>

                      <Button variant="primary" onClick={handleClickLogin} className="w-100">
                        로그인
                      </Button>
                    </Form>
                  </Card.Body>
                </Card>
              </Col>
            </Row>
          </Container>
        // <div>
        //     <input
        // name="userId"
        // type={'text'} 
        // value={loginParam.userId}
        // onChange={handleChange}
        // >
        // </input>

        // <input  
        // name="pwd"
        // type={'password'} 
        // value={loginParam.pwd}
        // onChange={handleChange}
        // >
        // </input>

        // <Button 
            
        //     onClick={handleClickLogin}  
        //     >
        //     LOGIN
        //   </Button>
        // </div>
        
        
      )

    


}

export default LoginComponent;