import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { getCommentList,deleteComment } from "../../api/commentApi";
import { Card, Spinner} from "react-bootstrap";
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

const ListCommentComponent = ({ dno }) => {
    const loginState = useSelector((state) => state.loginSlice);
    const [loading, setLoading] = useState(true);
    const [serverData, setServerData] = useState(initState);
    const navigate = useNavigate();
    const [page, setPage] = useState(1); // 현재 페이지 상태 
    const size = 5;
    

    useEffect(() => {
        const fetchComments = async () => {
            try {
                let data = await getCommentList({ page, size }, dno);
                console.log(data)
                setServerData({
                    ...data,
                    current: data.current || page,
                });
            } catch (err) {
                
            } finally {
                setLoading(false);
            }
        };
        fetchComments();
    }, [page]);

    const handleDelete = async (cno) => {
        try {
            // API 호출 시, deleteComment 함수 호출
            await deleteComment(cno); // 삭제 후 상태 업데이트
            alert('댓글이 삭제되었습니다.');
            navigate(0); // 페이지 새로 고침
        } catch (err) {
            console.error("댓글 삭제 실패:", err);
            alert('댓글 삭제에 실패했습니다..');
            
        }
    };

    const handlePageChange = (pageNum) => {
        setPage(pageNum);
    };

    if (loading) return <Spinner animation="border" variant="primary" />;
 

    return (
        <div>
           {serverData.dtoList.length > 0 ? (
                serverData.dtoList.map((comment) => (
                    <Card key={comment.cno} className="mb-3 shadow-sm border-light rounded">
                    <Card.Body className="d-flex justify-content-between align-items-center">
                        <Card.Title className="font-weight-bold text-primary">{comment.userId}</Card.Title>
                        {comment.userId == loginState.userId ? 
                        <button 
                            className="btn btn-danger"
                            onClick={() => handleDelete(comment.cno)} // 삭제 버튼 클릭 시 호출되는 함수
                        >
                            삭제
                        </button>
                        : <></>
                        }
                    </Card.Body>
                    <Card.Footer className="text-muted bg-light">
                        <span className="text-muted">{comment.content}</span>
                    </Card.Footer>
                    </Card>
                ))
                ) : (
                <div className="text-center text-muted">
                    <p>댓글이 없습니다.</p>
                </div>
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

export default ListCommentComponent;