import BasicLayout from "../../commonLayout/layouts/BasicLayout";
import FriendsSideMenu from "./FriendsSideMenu";
import { Outlet } from "react-router-dom";
const FriendsPage = () => {
    return (
        <BasicLayout side={<FriendsSideMenu/>} main={<Outlet/>}>
            
        </BasicLayout>
    )
}

export default FriendsPage;