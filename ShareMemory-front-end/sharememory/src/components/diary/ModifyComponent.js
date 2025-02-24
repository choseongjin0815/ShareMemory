import { useSelector } from "react-redux";
import { useRef, useState, useEffect } from "react";
import { modifyDiary, getDiaryDetail, API_SERVER_HOST } from "../../api/diaryApi";
import { useNavigate, useParams } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css"; 
import "../../css/WriteComponent.css"; 

const initState = {
    userId: '',
    title: '',
    content: '',
    uploadFileNames: [], // 기존 업로드된 파일 목록
};

const ModifyComponent = () => {
    const navigate = useNavigate();
    const host = API_SERVER_HOST;
    const { dno } = useParams();
    const loginState = useSelector((state) => state.loginSlice);
    const [diary, setDiary] = useState({ ...initState, userId: loginState.userId });
    const contentEditableRef = useRef();
    const uploadRef = useRef();
    const [newFiles, setNewFiles] = useState([]); // 새로 추가한 파일 목록

    // 일기 정보 가져오기
    useEffect(() => {
        getDiaryDetail(dno).then(data => {
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

    // 파일 첨부 처리 (기존 파일과 새로운 파일 구분)
    const handleFileChange = (e) => {
        const files = Array.from(e.target.files);
        setNewFiles(prevFiles => [...prevFiles, ...files]);
    };

    // 기존 파일 삭제 처리
    const handleRemoveFile = (fileName) => {
        setDiary((prevDiary) => ({
            ...prevDiary,
            uploadFileNames: prevDiary.uploadFileNames.filter(name => name !== fileName)
        }));
    };

    // 새 파일 삭제 처리
    const handleRemoveNewFile = (fileName) => {
        setNewFiles((prevFiles) => prevFiles.filter(file => file.name !== fileName));
    };

    // 이미지 삽입 후 content 상태 업데이트
    useEffect(() => {
        const existingImages = diary.uploadFileNames.map(fileName => insertImage(fileName, true)).join('');
        const newImages = newFiles.map(file => insertImage(file.name, false)).join('');
        contentEditableRef.current.innerHTML = diary.content + '<br/>' + existingImages + newImages;
    }, [diary.uploadFileNames, newFiles, diary.content]);

    // 이미지 삽입 함수
    const insertImage = (fileName, isServerFile) => {
        if (isServerFile) {
            return `<img src="${host}/api/diary/view/${fileName}" class="p-4 w-50" alt="image"/> <br/>`;
        } else {
            const uploadedFile = newFiles.find(file => file.name === fileName);
            if (uploadedFile) {
                const imageUrl = URL.createObjectURL(uploadedFile);
                return `<img src="${imageUrl}" class="p-4 w-50" alt="image-preview"/> <br/>`;
            }
        }
    };

    // 일기 수정 제출 처리
    const handleSubmit = () => {
        const formData = new FormData();

        // 새 파일들 FormData에 추가
        newFiles.forEach(file => formData.append("files", file));

        // 기존 파일 목록과 수정 데이터 추가
        formData.append("userId", diary.userId);
        formData.append("title", diary.title);
        formData.append("content", diary.content);
        formData.append("uploadFileNames", diary.uploadFileNames);

        modifyDiary(dno, formData).then(() => {
            alert("일기 수정 완료!");
            navigate(-1);
        });
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
                        suppressContentEditableWarning={true}
                        placeholder="내용을 입력하세요"
                    ></div>
                </div>

                <div className="mb-3">
                    <label className="form-label fw-bold">파일 첨부</label>
                    
                    {/* 기존 업로드된 파일 표시 */}
                    {diary.uploadFileNames.length > 0 && (
                        <ul className="list-group mb-2">
                            {diary.uploadFileNames.map((fileName, index) => (
                                <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                    {fileName}
                                    <button
                                        type="button"
                                        className="btn btn-danger btn-sm"
                                        onClick={() => handleRemoveFile(fileName)}
                                    >
                                        삭제
                                    </button>
                                </li>
                            ))}
                        </ul>
                    )}

                    {/* 새로 추가한 파일 표시 */}
                    {newFiles.length > 0 && (
                        <ul className="list-group mb-2">
                            {newFiles.map((file, index) => (
                                <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                    {file.name}
                                    <button
                                        type="button"
                                        className="btn btn-warning btn-sm"
                                        onClick={() => handleRemoveNewFile(file.name)}
                                    >
                                        삭제
                                    </button>
                                </li>
                            ))}
                        </ul>
                    )}

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