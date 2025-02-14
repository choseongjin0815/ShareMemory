import { Suspense, lazy } from "react";
import userRouter from "./userRouter"
import diaryRouter from "./diaryRouter";
const {createBrowserRouter} = require("react-router-dom");

const Main = lazy(() => import("../pages/MainPage"))


const Loading = <div>Loading.....</div>

const root = createBrowserRouter([
    {
        path: "",     
        element: <Suspense fallback={Loading}><Main/></Suspense> 
    },
    {
        path : "user",
        children : userRouter()
    },
    {
        path: "diary",
        children : diaryRouter()
    }
])

export default root;