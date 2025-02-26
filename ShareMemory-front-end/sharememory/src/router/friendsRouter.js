import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const List = lazy(() => import("../pages/friends/FriendsRequestPage"))

const FriendList = lazy(() => import("../pages/friends/FriendsListPage"));

const RequestList = lazy(() => import("../pages/friends/FriendsAcceptPage"));
const Loading = <div>Loading.....</div>

const friendsRouter = () => {
    return [
        {
            path: "list",
            element: <Suspense fallback={Loading}><List/></Suspense> 
        },
        {
            path: "",
            element: <Navigate replace to="list"></Navigate>
        },
        {
            path: "friendList",
            element: <Suspense fallback={Loading}><FriendList/></Suspense>
        },
        {
            path: "request",
            element: <Suspense fallback={Loading}><RequestList/></Suspense>
        }
       
    ]
}

export default friendsRouter;