import { useState } from "react";
import { Button } from "react-bootstrap";
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
        <div>
            <input
        name="userId"
        type={'text'} 
        value={loginParam.userId}
        onChange={handleChange}
        >
        </input>

        <input  
        name="pwd"
        type={'password'} 
        value={loginParam.pwd}
        onChange={handleChange}
        >
        </input>

        <Button 
            
            onClick={handleClickLogin}  
            >
            LOGIN
          </Button>
        </div>
        
        
      )

    


}

export default LoginComponent;