import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./diaryApi";

const prefix = `${API_SERVER_HOST}/api/user`;

export const getUserList = async(pagePagam, loginState) => {
    const {page,size} = pagePagam;
    
    const res = await jwtAxios.get(`${prefix}/${loginState.userId}/list`,
         {params : {page:page, size:size}});
    
        
     return res.data;
}