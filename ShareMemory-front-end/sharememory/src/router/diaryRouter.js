import { Suspense, lazy } from "react";
import { Navigate } from "react-router-dom";



const Read = lazy(() => import("../pages/diary/DiaryReadPage"))

const List = lazy(() => import("../pages/diary/DiaryListPage"))

const Write = lazy(() => import("../pages/diary/DiaryWritePage"))

const Modify = lazy(() => import("../pages/diary/DiaryModifyPage"))

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
        {
            path: "write",
            element: <Suspense fallback={Loading}><Write/></Suspense>

        },
        {
            path: "modify/:dno",
            element: <Suspense fallback={Loading}><Modify/></Suspense>
        }
        
    ];
}

export default diaryRouter;