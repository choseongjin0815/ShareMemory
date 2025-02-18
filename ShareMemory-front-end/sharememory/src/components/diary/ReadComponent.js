import { useState, useEffect } from "react";
import { getDiaryDetail } from "../../api/diaryApi";
import '../../css/ReadComponent.css'; // 커스텀 CSS 추가

const ReadComponent = ({ dno }) => {
  console.log(dno);

  const [diaryDetail, setDiaryDetail] = useState(null);

  // 상세 정보 가져오기
  useEffect(() => {
    getDiaryDetail(dno).then((data) => {
      setDiaryDetail(data);
    });
  }, [dno]);

  // 로딩 중일 때 표시할 텍스트
  if (!diaryDetail) {
    return <div className="text-center text-secondary mt-5">로딩 중...</div>;
  }

  return (
    <div className="container mt-5 read-component">
      {/* 일기 내용 카드 */}
      <div className="card shadow-lg rounded-3 border-0 mb-5 diary-card">
        <div className="card-body">
          <h3 className="card-title mb-4 fw-bold diary-title">
            {diaryDetail.title}
          </h3>

          <div className="row mb-3 diary-meta">
            <div className="col-md-6">
              <h6 className="text-muted">
                작성자: <span className="text-dark">{diaryDetail.userId}</span>
              </h6>
            </div>
            <div className="col-md-6 text-end">
              <small className="text-muted">
                작성일: {new Date(diaryDetail.regDate).toLocaleDateString()}
              </small>
            </div>
          </div>

          {/* 일기 내용 */}
          <div className="mb-3">
            <p className="card-text diary-content">
              {diaryDetail.content}
            </p>
          </div>
        </div>
      </div>

      {/* 뒤로 가기 버튼 */}
      <div className="text-center">
        <button 
          className="btn btn-outline-secondary px-4 py-2 rounded-pill shadow-sm back-button"
          onClick={() => window.history.back()}
        >
          ← 돌아가기
        </button>
      </div>
    </div>
  );
};

export default ReadComponent;