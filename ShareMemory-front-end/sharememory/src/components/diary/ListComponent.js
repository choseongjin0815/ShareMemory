import { useState, useEffect } from "react";
import { getUserDiaryList, getFriendDiaryList, getUserAndFriendDiaryList, getFriendToUserDiaryList } from "../../api/diaryApi";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom";

const initState = {
    dtoList: [],
    pageNumList: [],
    prev: false,
    next: false,
    totalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 1, // 현재 페이지
};

const ListComponent = () => {
    const loginState = useSelector((state) => state.loginSlice);
    const [diaryType, setDiaryType] = useState("user");
    const [serverData, setServerData] = useState(initState);
    const [page, setPage] = useState(1); // 현재 페이지 상태 
    const size = 7;

    useEffect(() => {
      const fetchData = async () => {
        let data;
        try {
          // 다이어리 타입에 따라 API 호출
          switch (diaryType) {
            case "user":
              data = await getUserDiaryList({ page, size }, loginState);
              break;

            case "friend":
              data = await getFriendDiaryList({ page, size }, loginState);

              // 친구 요청을 받고 승낙한 계정으로 로그인 할 시
              if (data.dtoList == '') {
                data = await getFriendToUserDiaryList({page, size}, loginState);
              }
              break;
            case "all":
              data = await getUserAndFriendDiaryList({ page, size }, loginState);
              break;
            default:
              data = await getUserDiaryList({ page, size }, loginState);
          }
        
          // API에서 데이터를 받은 후 상태 업데이트
          setServerData({
            ...data,
            current: data.current || page,
          });
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };
      fetchData();
  }, [page, diaryType, loginState]); 

    useEffect(() => {
      setPage(1);
    },[diaryType]);

    const handlePageChange = (pageNum) => {
      setPage(pageNum);
    };

  return (
      <div className="container mt-4">
        <div className="btn-group mb-4" role="group" aria-label="Diary type selection">
          <button
            className="btn btn-outline-primary"
            onClick={() => setDiaryType("user")}
          >
            My Memories
          </button>
          <button
            className="btn btn-outline-primary"
            onClick={() => setDiaryType("friend")}
          >
            Friend's Memories
          </button>
          <button
            className="btn btn-outline-primary"
            onClick={() => setDiaryType("all")}
          >
            All Memories
          </button>
        </div>

        {/* 게시글 목록 */}
        {serverData.dtoList.length > 0 ? (
          serverData.dtoList.map((diary) => (
            <div
              className="card mb-4 shadow-lg rounded-3"
              key={diary.dno}
              style={{ border: "1px solid #ddd", height: "48px", lineHeight: "48px" }}
            >
              <div className="card-body">
                <div className="row align-items-center">
                  {/* 제목 */}
                  <div className="col-8">
                    <h5 className="card-title mb-0" style={{ fontSize: "1.2rem", fontWeight: "bold" }}>
                      <Link
                        to={`/diary/read/${diary.dno}`}
                        className="card-link text-decoration-none text-dark"
                      >
                        {diary.title}
                      </Link>
                    </h5>
                  </div>

                  {/* 작성자 */}
                  <div className="col-2 text-center">
                    <h6 className="card-subtitle mb-2 text-muted" style={{ fontSize: "1rem" }}>
                      {diary.userId}
                    </h6>
                  </div>

                  {/* 작성일 */}
                  <div className="col-2 text-center">
                    <small className="text-muted" style={{ fontSize: "0.9rem" }}>
                      {diary.regDate}
                    </small>
                  </div>
                </div>
              </div>
            </div>
          ))
        ) : (
          <p>Loading...</p> // 데이터가 없으면 로딩 메시지 표시
        )}

        {/* 페이징 */}
        <nav aria-label="Page navigation" className="mt-4">
          <ul className="pagination justify-content-center">
            {/* 이전 버튼 */}
            {serverData.prev && (
              <li className="page-item">
                <button
                  className="page-link"
                  onClick={() => handlePageChange(serverData.prevPage)}
                >
                  이전
                </button>
              </li>
            )}

            {/* 페이지 번호 */}
            {serverData.pageNumList.map((pageNum) => (
              <li
                className={`page-item ${pageNum === page ? "active" : ""}`}
                key={pageNum}
              >
                <button
                  className="page-link"
                  onClick={() => handlePageChange(pageNum)}
                >
                  {pageNum}
                </button>
              </li>
            ))}

            {/* 다음 버튼 */}
            {serverData.next && (
              <li className="page-item">
                <button
                  className="page-link"
                  onClick={() => handlePageChange(serverData.nextPage)}
                >
                  다음
                </button>
              </li>
            )}
          </ul>
        </nav>
      </div>
    );
};

export default ListComponent;