import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";

const List = lazy(() => import("../pages/friends/FriendsRequestPage"))

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
        }
       
    ]
}

export default friendsRouter;