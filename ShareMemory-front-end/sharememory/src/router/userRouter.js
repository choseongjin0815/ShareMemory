import { Suspense, lazy } from "react";


const Login = lazy(() => import("../pages/user/LoginPage"))


const Loading = <div>Loading.....</div>

const userRouter = () => {
    return [
        {
            path: "login",
            element: <Suspense fallback={Loading}><Login/></Suspense> 
        }
    ]
}

export default userRouter;