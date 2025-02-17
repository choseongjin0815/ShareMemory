import ReadComponent from "../../components/diary/ReadComponent";
import BasicLayout from "../../commonLayout/layouts/BasicLayout";
import DiarySideMenu from "./DiarySideMenu";
import { useParams } from "react-router-dom";
const DiaryReadPage = () => {
    const {dno} = useParams()
    console.log("test")
    console.log("dno", dno)
    return (
        <ReadComponent dno={dno}/>
    )
}

export default DiaryReadPage;