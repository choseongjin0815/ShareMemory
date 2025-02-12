import { useDispatch, useSelector } from "react-redux"
import { Navigate, createSearchParams, useNavigate } from "react-router-dom"
import { loginPostAsync, logout } from "../slice/loginSlice"

const useCustomLogin = ( ) => {

  const navigate = useNavigate()

  const dispatch = useDispatch()

  const exceptionHandle = (ex) => {

    console.log("Exception------------------------")

    console.log(ex)

    const errorMsg = ex.response.data.error

    const errorStr = createSearchParams({error: errorMsg}).toString()

    if(errorMsg === 'REQUIRE_LOGIN'){
      alert("로그인 해야만 합니다.")
      navigate({pathname:'/user/login' , search: errorStr})

      return
    }

    if(ex.response.data.error === 'ERROR_ACCESSDENIED'){
      alert("해당 메뉴를 사용할 수 있는 권한이 없습니다.")
      navigate({pathname:'/user/login' , search: errorStr})
      return 
    }
  }


  const loginState = useSelector(state => state.loginSlice) //-------로그인 상태

  const isLogin = loginState.userId ? true :false //----------로그인 여부 
  

  const doLogin = async (loginParam) => { //----------로그인 함수 

    const action  = await dispatch(loginPostAsync(loginParam))

    return action.payload

  }

  const doLogout = () => { //---------------로그아웃 함수 

    dispatch(logout())
  }

  const moveToPath = (path) => {  //----------------페이지 이동 
    navigate({pathname: path}, {replace:true})
  }

  const moveToLogin = () => { //----------------------로그인 페이지로 이동 
    navigate({pathname: '/user/login'}, {replace:true})
  }

  const moveToLoginReturn = () => { //----------------------로그인 페이지로 이동 컴포넌트 
    return <Navigate replace to="/user/login"/>
  }

  return  {loginState, isLogin, doLogin, doLogout, moveToPath, moveToLogin, moveToLoginReturn, exceptionHandle}

}

export default useCustomLogin;