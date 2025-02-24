import { useState } from "react";
import { useSelector } from "react-redux";
import { writeComment} from "../../api/commentApi";
import 'bootstrap/dist/css/bootstrap.min.css';

const initState = {
    userId: '',
    content: '',
    dno: ''
};

const WriteCommentComponent = ({ dno }) => {
    const loginState = useSelector((state) => state.loginSlice);
    const [comment, setComment] = useState({ ...initState, userId : loginState.userId });

    // 댓글 작성 핸들러
    const handleCommentChange = (e) => {
        const { value } = e.target;
        setComment((prevComment) => ({
            ...prevComment,
            content: value // content만 업데이트
        }));
    };

    // 댓글 제출 핸들러
    const handleSubmit = () => {
        // 로그인된 사용자 정보로 userId 및 dno 설정
        const updatedComment = {
            ...comment,
            userId: loginState.userId,
            dno: dno
        };

        console.log("submit", updatedComment);

        // 댓글 작성 API 호출
        writeComment(updatedComment).then((data) => {
            console.log(data);
            // 성공 후 처리 (예: 댓글 목록 갱신)
            alert('댓글 등록 완료');
            // 입력 필드 초기화
            setComment({ ...initState });
        }).catch((error) => {
            console.error('댓글 등록 실패:', error);
        });
    };

    return (
        <div className="container mt-4">
            {loginState.userId ? (
                <div>
                    <div className="mb-3">
                        <textarea
                            className="form-control"
                            value={comment.content} // comment 상태값 사용
                            onChange={handleCommentChange}
                            placeholder="댓글을 입력하세요"
                            rows="4"
                        />
                    </div>
                    <button 
                        className="btn btn-primary" 
                        onClick={handleSubmit}
                    >
                        댓글 제출
                    </button>
                </div>
            ) : (
                <div className="alert alert-warning" role="alert">
                    로그인 후 댓글을 작성할 수 있습니다.
                </div>
            )}
        </div>
    );
};

export default WriteCommentComponent;