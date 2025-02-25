import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./diaryApi";

const prefix = `${API_SERVER_HOST}/api/comment`;

export const writeComment = async (commentObj) => {
    console.log("commentObj", commentObj);
    const res = await jwtAxios.post(`${prefix}/create`, commentObj);
    console.log(res)
    return res.data;
}

export const getCommentList = async(pageParam, dno) => {
    const {page,size} = pageParam
    const res = await jwtAxios.get(`${prefix}/${dno}`,  {params: {page:page, size:size}})
    console.log("getCommentList dno:", dno);
    console.log("getCommentList data : ", res.data)
    return res.data;
}

export const deleteComment = async(cno) => {
    const res = await jwtAxios.delete(`${prefix}/${cno}`);
}