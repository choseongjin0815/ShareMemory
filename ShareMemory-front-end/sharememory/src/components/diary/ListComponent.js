import { useState, useEffect } from "react";
import { getUserDiaryList } from "../../api/diaryApi";
import { useSelector } from "react-redux";
import { Link } from "react-router-dom"; // Link 컴포넌트 임포트

const initState = {
  dtoList: [],
  pageNumList: [],
  pageRequestDTO: null,
  prev: false,
  next: false,
  totoalCount: 0,
  prevPage: 0,
  nextPage: 0,
  totalPage: 0,
  current: 1, // 현재 페이지
};

const ListComponent = () => {
  const loginState = useSelector((state) => state.loginSlice);

  const [serverData, setServerData] = useState(initState);
  const [page, setPage] = useState(1); // 📌 현재 페이지 상태 관리
  const size = 10;

  useEffect(() => {
    getUserDiaryList({ page, size }, loginState).then((data) => {
      console.log(data);
      setServerData(data);
    });
  }, [page, size, loginState]);


  const handlePageChange = (pageNum) => {
    setPage(pageNum);
  };

  return (
    <div className="container mt-4">

      {/* 게시글 목록 */}
      {serverData.dtoList.map((diary) => (
        <div className="card mb-4 shadow-lg rounded-3" key={diary.dno} style={{ border: '1px solid #ddd', height:'35px', lineHeight: '35px' }}>
          <div className="card-body">
            <div className="row align-items-center">
              {/* 제목 */}
              <div className="col-8">
                <h5 className="card-title mb-0" style={{ fontSize: '1.2rem', fontWeight: 'bold' }}>
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
                <h6 className="card-subtitle mb-2 text-muted" style={{ fontSize: '1rem' }}>{diary.userId}</h6>
              </div>

              {/* 작성일 */}
              <div className="col-2 text-center">
                <small className="text-muted" style={{ fontSize: '0.9rem' }}>{diary.regDate}</small>
              </div>
            </div>
          </div>
        </div>
      ))}

      {/* 📌 페이징 네비게이션 */}
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
              className={`page-item ${
                pageNum === page ? "active" : ""
              }`}
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