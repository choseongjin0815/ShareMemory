import { useState, useEffect } from "react";
import { getUserList } from "../../api/friendsApi";
import { useSelector } from "react-redux";


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

const ListUserComponent = () => {
    const loginState = useSelector((state) => state.loginSlice);
    const [serverData, setServerData] = useState(initState);
    const [page, setPage] = useState(1); // 현재 페이지 상태 
    const size = 5;

    useEffect(() => {
      const fetchData = async () => {
        let data;
        try {
              data = await getUserList({ page, size }, loginState);
          setServerData({
            ...data,
            current: data.current || page,
          });
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      };
      fetchData();
  }, [page, loginState]); 


    const handlePageChange = (pageNum) => {
      setPage(pageNum);
    };

  return (
      <div className="container mt-4">
        {/* 게시글 목록 */}
        {serverData.dtoList.length > 0 ? (
          serverData.dtoList.map((user) => (
            <div
              className="card mb-4 shadow-lg rounded-3"
              key={user.userId}
              style={{ border: "1px solid #ddd", height: "60px", lineHeight: "60px" }}
            >
              <div className="card-body">
                <div className="row align-items-center">
              
                  <div className="col-8">
                    <h5 className="card-title mb-0" style={{ fontSize: "1.2rem", fontWeight: "bold" }}>
                      <div
                        
                        className="card-link text-decoration-none text-dark"
                      >
                        {user.nickname}
                      </div>
                    </h5>
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

export default ListUserComponent;