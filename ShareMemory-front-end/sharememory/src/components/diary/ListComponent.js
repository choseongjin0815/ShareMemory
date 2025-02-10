import {useState, useEffect} from "react"
import { getList } from "../../api/diaryApi"
const initState = {
    dtoList:[],
    pageNumList:[],
    pageRequestDTO: null,
    prev: false,
    next: false,
    totoalCount: 0,
    prevPage: 0,
    nextPage: 0,
    totalPage: 0,
    current: 0
  }

const ListComponent = () => {

    const [serverData, setServerData] = useState(initState)

    const page = 1
    const size = 10

    useEffect(() => {

        getList({page, size}).then(data => {
          console.log(data)
          setServerData(data)
        })
    
      }, [page, size])

      console.log(serverData)

      return (
        <table border = "1">
            {serverData.dtoList.map(diary =>
                <tr>
                    <td>{diary.dno}</td>
                    <td>{diary.title}</td>
                    <td>{diary.content}</td>
                    <td>{diary.regDate}</td>
                    <td>{diary.totalView}</td>
                    <td>{diary.userId}</td>
                </tr>
            )}
        </table>
      )
}

export default ListComponent;