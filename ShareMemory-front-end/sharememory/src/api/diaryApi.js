
import jwtAxios from "../util/jwtUtil"
export const API_SERVER_HOST = 'http://localhost:8080'

const prefix = `${API_SERVER_HOST}/api/diary`

export const getUserDiaryList = async (pageParam, loginState) => {

    const {page,size} = pageParam
    console.log(loginState)
    const res = await jwtAxios.get(`${prefix}/list/${loginState.userId}`, {params: {page:page, size:size}})

    return res.data
}

export const getDiaryDetail = async (dno) => {
    const res = await jwtAxios.get(`${prefix}/${dno}`)
    return res.data
}

