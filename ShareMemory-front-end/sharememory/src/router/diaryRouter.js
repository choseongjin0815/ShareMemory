import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";
import { replace } from "react-router-dom";


const Read = lazy(() => import("../pages/diary/DiaryReadPage"))

const List = lazy(() => import("../pages/diary/DiaryListPage"))
const Diary = lazy(() => import("../pages/diary/DiaryPage"))

const Loading = <div>Loading.....</div>

const diaryRouter = () => {
    return [
        {
            path: "list",
            element: <Suspense fallback={Loading}><List/></Suspense>
            
        },
        {
            path: "",
            element: <Navigate replace to="list"/>
        },
        {
            path: "read/:dno",
            element: <Suspense fallback={Loading}><Read/></Suspense>
        },

        // {
        //     path: "read/:dno",
        //     element: <Suspense fallback={Loading}><Read/></Suspense> 
        // },
        
    ];
}

export default diaryRouter;