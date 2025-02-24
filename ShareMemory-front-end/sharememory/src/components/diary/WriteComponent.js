import { useSelector } from "react-redux";
import { useRef, useState } from "react";
import { createDiary } from "../../api/diaryApi";
import { useNavigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"; // Bootstrap 적용
import "../../css/WriteComponent.css"; // 커스텀 스타일 적용

const initState = {
    userId: '',
    title: '',
    content: '',
    files: []
};

const WriteComponent = () => {
    const navigate = useNavigate();
    const loginState = useSelector((state) => state.loginSlice);
    const [diary, setDiary] = useState({ ...initState, userId: loginState.userId });
    const uploadRef = useRef();

    const handleChangeDiary = (e) => {
        setDiary({ ...diary, [e.target.name]: e.target.value });
    };

    const handleFileChange = (e) => {
        setDiary({ ...diary, files: [...e.target.files] });
        console.log(diary);
    };

    const handleSubmit = () => {
        const files = uploadRef.current.files;
        const formData = new FormData();

        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        formData.append("userId", diary.userId);
        formData.append("title", diary.title);
        formData.append("content", diary.content);
        
        setDiary()

        createDiary(formData).then(data => {
            console.log(data);
            alert("일기 등록 완료!");
            navigate("/diary/list");
        });

        
    };

    return (
        <div className="container mt-5 d-flex justify-content-center">
            <div className="card shadow-lg p-4 diary-card">
                <h3 className="text-center mb-4">📖 기억 남기기</h3>

                <div className="mb-3">
                    <label className="form-label fw-bold">제목</label>
                    <input
                        className="form-control"
                        name="title"
                        type="text"
                        value={diary?.title}
                        onChange={handleChangeDiary}
                        placeholder="제목을 입력하세요"
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label fw-bold">내용</label>
                    <textarea
                        className="form-control diary-textarea"
                        name="content"
                        value={diary?.content}
                        onChange={handleChangeDiary}
                        placeholder="내용을 입력하세요"
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label fw-bold">파일 첨부</label>
                    <input
                        ref={uploadRef}
                        className="form-control"
                        type="file"
                        multiple
                        onChange={handleFileChange}
                    />
                </div>

                <div className="text-end">
                    <button
                        type="button"
                        className="btn btn-primary px-4 btn-lg diary-btn"
                        onClick={handleSubmit}
                    >
                        작성하기 📝
                    </button>
                </div>
            </div>
        </div>
    );
};

export default WriteComponent;