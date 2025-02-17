import { useState, useEffect } from "react";
import { getDiaryDetail } from "../../api/diaryApi"; // 일기 상세 정보를 가져오는 API 호출 함수

const ReadComponent = ({dno}) => {
    console.log(dno)

    const [diaryDetail, setDiaryDetail] = useState(null);

    // 상세 정보 가져오기
    useEffect(() => {
        getDiaryDetail(dno).then((data) => {
        setDiaryDetail(data);
        });
    }, [dno]);

    // 로딩 중일 때 표시할 텍스트
    if (!diaryDetail) {
        return <div className="text-center">로딩 중...</div>;
    }

    return (
        <div className="container mt-4">
        <h2 className="mb-4 text-center text-primary">일기 상세</h2>

        {/* 일기 내용 카드 */}
        <div className="card shadow-lg rounded-3 mb-4" style={{ border: '1px solid #ddd' }}>
            <div className="card-body">
            <h3 className="card-title mb-3" style={{ fontSize: '2rem', fontWeight: 'bold' }}>
                {diaryDetail.title}
            </h3>

            <div className="row mb-3">
                <div className="col-md-6">
                <h5 className="card-subtitle mb-2 text-muted">
                    작성자: {diaryDetail.userId}
                </h5>
                </div>
                <div className="col-md-6 text-end">
                <small className="text-muted">
                    작성일: {diaryDetail.regDate}
                </small>
                </div>
            </div>

            {/* 일기 내용 */}
            <div className="mb-3">
                <p className="card-text" style={{ fontSize: '1.1rem', lineHeight: '1.6' }}>
                {diaryDetail.content}
                </p>
            </div>

            {/* 댓글 기능 등 추가적인 기능은 여기서 */}
            {/* 예시로 답글 작성란 추가 */}
            <hr />
            <div className="mt-4">
                <h5 className="text-center text-secondary">댓글</h5>
                {/* 댓글 작성란 */}
                <textarea className="form-control" rows="4" placeholder="댓글을 작성해주세요..." style={{ resize: 'none' }}></textarea>
                <button className="btn btn-primary mt-2">댓글 작성</button>
            </div>
            </div>
        </div>

        {/* 뒤로 가기 버튼 */}
        <div className="text-center">
            <button className="btn btn-secondary" onClick={() => window.history.back()}>
            돌아가기
            </button>
        </div>
        </div>
  );
};

export default ReadComponent;