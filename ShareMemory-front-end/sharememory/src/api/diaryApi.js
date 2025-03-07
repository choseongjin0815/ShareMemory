
import jwtAxios from "../util/jwtUtil"
export const API_SERVER_HOST = 'http://localhost:8080'

const prefix = `${API_SERVER_HOST}/api/diary`

export const getUserDiaryList = async (pageParam, loginState) => {

    const {page,size} = pageParam
    console.log(loginState)
    const res = await jwtAxios.get(`${prefix}/list/${loginState.userId}`, {params: {page:page, size:size}})

    return res.data
}

export const getFriendDiaryList = async (pageParam, loginState) => {
    const {page,size} = pageParam
    const res = await jwtAxios.get(`${prefix}/list/${loginState.userId}/friend`, {params: {page:page, size:size}})
    
    return res.data
}

export const getFriendToUserDiaryList = async (pageParam, loginState) => {
    const {page,size} = pageParam
    const res = await jwtAxios.get(`${prefix}/list/${loginState.userId}/toFriend`, {params: {page:page, size:size}})
    
    return res.data
}

export const getUserAndFriendDiaryList = async (pageParam, loginState) => {
    const {page,size} = pageParam
    const res = await jwtAxios.get(`${prefix}/list/${loginState.userId}/all`, {params: {page:page, size:size}})

    return res.data
}

export const getDiaryDetail = async (dno) => {
    const res = await jwtAxios.get(`${prefix}/${dno}`)
    return res.data
}

export const createDiary = async (diaryObj) => {
    const res = await jwtAxios.post(`${prefix}/create`, diaryObj)
    
    return res.data

}

export const deleteDiary = async (dno) => {
    const res = await jwtAxios.delete(`${prefix}/${dno}`);

    return res.data;
}

export const modifyDiary = async (dno, diaryObj) => {
    const res = await jwtAxios.put(`${prefix}/${dno}`, diaryObj);
}

export const getFile = async(fileName) => {
    const res = await jwtAxios.get(`${prefix}/view/${fileName}`)
    
    console.log(res);
    
    
    return res.status;
}