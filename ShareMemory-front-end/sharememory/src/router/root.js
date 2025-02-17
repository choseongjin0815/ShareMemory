import { Suspense, lazy } from "react";
import { useEffect } from "react";
import { useSelector } from "react-redux";
import { useNavigate } from "react-router-dom";
import userRouter from "./userRouter";
import diaryRouter from "./diaryRouter";

const { createBrowserRouter } = require("react-router-dom");

const Main = lazy(() => import("../pages/MainPage"));
const Diary = lazy(() => import("../pages/diary/DiaryPage"));

const Loading = <div>Loading.....</div>;

const RootRouter = () => {
  const navigate = useNavigate();
  const isLogin = useSelector((state) => state.loginSlice);

  // 로그인 상태 체크 후, 자동으로 리디렉션
  useEffect(() => {
    if (isLogin.userId) {
      navigate("/diary"); // 로그인 되어 있으면 diary로 리디렉션
    }
  }, [isLogin, navigate]);

  return (
    <Suspense fallback={Loading}>
      <Main />
    </Suspense>
  );
};

const root = createBrowserRouter([
  {
    path: "",
    element: (
      <Suspense fallback={Loading}>
        <RootRouter />
      </Suspense>
    ),
  },
  {
    path: "user",
    children: userRouter(),
  },
  {
    path: "diary",
    element: 
      <Suspense fallback={Loading}>
        <Diary />
      </Suspense>
    ,
    children: diaryRouter()
  },
  {
    path: "friends",
    // 친구 관련 페이지 추가 가능
  },
]);

export default root;