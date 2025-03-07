import axios from "axios"
import { API_SERVER_HOST } from "./diaryApi"

const host = `${API_SERVER_HOST}/api/user`

export const loginPost = async (loginParam) => {

    const header = {headers: {"Content-Type": "x-www-form-urlencoded"}}
  
    const form = new FormData()
    form.append('username', loginParam.userId)
    form.append('password', loginParam.pwd)
  
    const res = await axios.post(`${host}/login`, form, header)
  
    return res.data
  
  }