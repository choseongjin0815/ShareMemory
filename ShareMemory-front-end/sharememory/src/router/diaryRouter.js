import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";
import { replace } from "react-router-dom";


const Read = lazy(() => import("../pages/diary/DiaryReadPage"))
const Diary = lazy(() => import("../pages/diary/DiaryPage"))
const Loading = <div>Loading.....</div>

const diaryRouter = () => {
    return [
        {
            path: "",
            element: <Suspense fallback={Loading}><Diary/></Suspense>
            
        },
        {
            path: "list",
            element: <Navigate replace to="/diary"/>
        },

        {
            path: "read/:dno",
            element: <Suspense fallback={Loading}><Read/></Suspense> 
        },
        
    ];
}

export default diaryRouter;