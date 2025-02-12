import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { loginPost } from "../api/userApi";

import { getCookie, setCookie, removeCookie } from "../util/cookieUtil";

const initState = {
    userId:''
}

export const loginPostAsync = createAsyncThunk('loginPostAsync', (param) => {

  return loginPost(param)

})

const loadMemberCookie = () => {  //쿠키에서 로그인 정보 로딩 

  const userInfo =  getCookie("user")

  //닉네임 처리 
  if(userInfo && userInfo.nickname) {
    userInfo.userId = decodeURIComponent(userInfo.userId)
  }

  return userInfo
}


const loginSlice = createSlice({
  name: 'LoginSlice',
  initialState: loadMemberCookie()|| initState, //쿠키가 없다면 초깃값사용 
  reducers: {
    login: (state, action) => {
      console.log("login.....")

      //{소셜로그인 회원이 사용}
      const payload = action.payload

    
      setCookie("user",JSON.stringify(payload), 1) //1일
      return payload
    },

    logout: (state, action) => {
        console.log("logout....")

        removeCookie("user")
        return {...initState}
    }
  },
  extraReducers: (builder) => {
    
    builder.addCase( loginPostAsync.fulfilled, (state, action) => { 
      console.log("fulfilled")

      console.log("state : ", state)
      console.log("action : ", action);

      const payload = action.payload


      //정상적인 로그인시에만 저장 
      if(!payload.error){
        setCookie("user",JSON.stringify(payload), 1) //1일
      }

      return payload

    })

    .addCase(loginPostAsync.pending, (state,action) => {

      console.log("state : ", state)
      console.log("action : ", action);
    })
    .addCase(loginPostAsync.rejected, (state,action) => {
        console.log("rejected")
    })
  }
})

export const {login,logout} = loginSlice.actions

export default loginSlice.reducer