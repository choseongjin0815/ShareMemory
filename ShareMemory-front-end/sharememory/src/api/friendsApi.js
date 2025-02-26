import jwtAxios from "../util/jwtUtil";
import { API_SERVER_HOST } from "./diaryApi";

const prefix = `${API_SERVER_HOST}/api/user`;

const prefixFriend = `${API_SERVER_HOST}/api/friend`
export const getUserList = async(pagePagam, loginState) => {
    const {page,size} = pagePagam;
    
    const res = await jwtAxios.get(`${prefix}/${loginState.userId}/list`,
         {params : {page:page, size:size}});
    
     return res.data;
}

export const getFriendList = async(pageParam, loginState) => {
    const {page, size} = pageParam;

    const res = await jwtAxios.get(`${prefix}/${loginState.userId}/list/friends`,
        {params : {page:page, size:size}});
    console.log(res);
    
    return res.data;
}

export const acceptFriendRequest = async(friendId) => {

    const res = await jwtAxios.patch(`${prefixFriend}/accept/${friendId}`);

    return res.data;
}

export const rejectFriendRequest = async(friendId) => {

    const res = await jwtAxios.patch(`${prefixFriend}/reject/${friendId}`);

    return res.data;
}

export const friendRequest = async(toUserId, fromUserId) => {
    console.log(toUserId);
    console.log(fromUserId);
    
    const res = await jwtAxios.post(`${prefixFriend}/${fromUserId}/${toUserId}`);
}