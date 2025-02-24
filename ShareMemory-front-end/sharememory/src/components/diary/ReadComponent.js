import { useState, useEffect, React } from "react";
import { useNavigate } from "react-router-dom";
import { getDiaryDetail, deleteDiary } from "../../api/diaryApi";
import '../../css/ReadComponent.css'; // 커스텀 CSS 추가
import {API_SERVER_HOST} from "../../api/diaryApi";
import { useSelector } from "react-redux";
import WriteCommentComponent from "../ comment/WriteCommentComponent";

const ReadComponent = ({ dno }) => {
  console.log(dno);

  const host = API_SERVER_HOST;
  
  const navigate = useNavigate();

  const loginState = useSelector((state) => state.loginSlice);

  const [diaryDetail, setDiaryDetail] = useState(null);

  // 상세 정보 가져오기
  useEffect(() => {
    getDiaryDetail(dno).then((data) => {
      setDiaryDetail(data);
      console.log(data);
    });
  }, [dno]);

  //이전 페이지로 이동
  const handleBack = () => {
    navigate(-1);
  }
  //수정 페이지로 이동
  const handleEdit = (dno) => {
    navigate(`/diary/modify/${dno}`)
  };

  //삭제 페이지로 이동
  const handleDelete = () => {
    if (window.confirm("정말 삭제하시겠습니까?")) {
      deleteDiary(dno).then((data) =>{
        console.log(data);
        handleBack();
      })
    }
  };


  // 로딩 중일 때 표시할 텍스트
  if (!diaryDetail) {
    return <div className="text-center text-secondary mt-5">로딩 중...</div>;
  }

  return (
    <div className="container mt-5 read-component">
      
      <div className="card shadow-lg rounded-3 border-0 mb-5 diary-card">
        <div className="card-body">
        <h3 className="card-title mb-4 fw-bold diary-title">
          {diaryDetail.title}

          {/* 나의 diary만 수정 삭제 가능 */}
          {loginState.userId === diaryDetail.userId ? ( 
            <div className="button-group">
            <button className="edit-button" onClick={() => handleEdit(diaryDetail.dno)}>수정</button>
            <button className="delete-button" onClick={() => handleDelete(diaryDetail.dno)}>삭제</button>
          </div>
            )
            : <></>
          }   
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
              {diaryDetail.uploadFileNames.map((imgFile, i) => (
                <span key={i}>
                  <br/>
                  <img 
                    alt="diary"
                    className="p-4 w-50" 
                    src={`${host}/api/diary/view/${imgFile}`}
                  />
                </span>
              ))}
              
            </p>
          </div>
        </div>
      </div>
      <WriteCommentComponent dno={dno}/>       
      {/* 뒤로 가기 버튼 */}
      <div className="text-center">
        <button 
          className="btn btn-outline-secondary px-4 py-2 rounded-pill shadow-sm back-button"
          onClick={handleBack}
        >
          ← 돌아가기
        </button>
      </div>
    </div>
  );
};

export default ReadComponent;