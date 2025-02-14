import { Suspense, lazy } from "react";


const Login = lazy(() => import("../pages/user/LoginPage"))


const Loading = <div>Loading.....</div>

const diaryRouter = () => {
    return [
        {
            path: "login",
            element: <Suspense fallback={Loading}><Login/></Suspense> 
        }
    ]
}

export default diaryRouter;