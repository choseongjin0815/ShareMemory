import { useSelector } from "react-redux";
import { useRef, useState, useEffect } from "react";
import { modifyDiary, getDiaryDetail, API_SERVER_HOST } from "../../api/diaryApi";
import { useNavigate, useParams } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"; // Bootstrap 적용
import "../../css/WriteComponent.css"; // 커스텀 스타일 적용

const initState = {
    userId: '',
    title: '',
    content: '',
    files: [], // 첨부된 파일들을 저장
    uploadFileNames: [] // 서버에 전송할 파일 이름들
};

const ModifyComponent = () => {
    const navigate = useNavigate();
    const host = API_SERVER_HOST;
    const { dno } = useParams();
    const loginState = useSelector((state) => state.loginSlice);
    const [diary, setDiary] = useState({ ...initState, userId: loginState.userId });
    const uploadRef = useRef();
    const contentEditableRef = useRef();
   
    // 일기 정보 가져오기
    useEffect(() => {
        getDiaryDetail(dno).then(data => {
            console.log(data);
            setDiary(data);
        });
    }, [dno]);

    // 입력값 변경 처리
    const handleChangeDiary = (e) => {
        setDiary({
            ...diary,
            [e.target.name]: e.target.value
        });
    };

    // 파일 첨부 처리
    const handleFileChange = (e) => {
        const files = e.target.files;
        setDiary({
            ...diary,
            files: files,
            uploadFileNames: [...diary.uploadFileNames, ...Array.from(files).map(file => file.name)] // 파일 이름 배열 추가
        });
    };

    // 기존 파일 삭제 처리
    const handleRemoveFile = (fileName) => {
        setDiary({
            ...diary,
            uploadFileNames: diary.uploadFileNames.filter((name) => name !== fileName)
        });
    };

    // 내용 변경 시 커서 위치 유지 및 이미지 삽입
    const handleContentChange = () => {
        const newContent = contentEditableRef.current.innerHTML;
        setDiary({ ...diary, content: newContent });
    };

    // 이미지 삽입 후 content 상태 업데이트
    useEffect(() => {
        if (diary.uploadFileNames.length > 0) {
            const images = diary.uploadFileNames.map(fileName => insertImage(fileName)).join('');
            contentEditableRef.current.innerHTML = diary.content + images;
        }
    }, [diary.uploadFileNames, diary.content]);

    const insertImage = (fileName) => {
        return `<img src="${host}/api/diary/view/${fileName}" class="p-4 w-50" alt="image"/>`;
    };

    // 일기 수정 제출 처리
    const handleSubmit = () => {
        const files = uploadRef.current.files;
        const formData = new FormData();

        // 파일들 FormData에 추가
        for (let i = 0; i < files.length; i++) {
            formData.append("files", files[i]);
        }

        // 일기 수정 데이터 보내기
        formData.append("userId", diary.userId);
        formData.append("title", diary.title);
        formData.append("content", diary.content); // HTML 콘텐츠 전송
        formData.append("uploadFileNames", diary.uploadFileNames.join(',')); // 파일 이름들 전송

        modifyDiary(formData).then(data => {
            console.log(data);
        });

        alert("일기 수정 완료!");
        navigate("/diary/list");
    };

    return (
        <div className="container mt-5 d-flex justify-content-center">
            <div className="card shadow-lg p-4 diary-card">
                <h3 className="text-center mb-4">수정</h3>

                <div className="mb-3">
                    <label className="form-label fw-bold">제목</label>
                    <input
                        className="form-control"
                        name="title"
                        type="text"
                        value={diary.title}
                        onChange={handleChangeDiary}
                    />
                </div>

                <div className="mb-3">
                    <label className="form-label fw-bold">내용</label>
                    <div
                        ref={contentEditableRef}
                        className="form-control diary-textarea"
                        name="content"
                        contentEditable
                        onInput={handleContentChange} // 내용 변경 시 커서 위치 저장
                        placeholder="내용을 입력하세요"
                    >
                        {diary.content}
                    </div>
                </div>

                <div className="mb-3">
                    <label className="form-label fw-bold">파일 첨부</label>
                    {/* 기존에 업로드한 파일들 표시 */}
                    <div className="mb-2">
                        {diary.uploadFileNames.length > 0 && (
                            <ul>
                                {diary.uploadFileNames.map((fileName, index) => (
                                    <li key={index}>
                                        <span>{fileName}</span>
                                        <button
                                            type="button"
                                            className="btn btn-danger btn-sm ms-2"
                                            onClick={() => handleRemoveFile(fileName)}
                                        >
                                            삭제
                                        </button>
                                    </li>
                                ))}
                            </ul>
                        )}
                    </div>

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
                        수정하기 📝
                    </button>
                </div>
            </div>
        </div>
    );
};

export default ModifyComponent;