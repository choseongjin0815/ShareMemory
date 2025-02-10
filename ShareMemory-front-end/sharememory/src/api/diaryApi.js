import axios from "axios"

export const API_SERVER_HOST = 'http://localhost:8080'

const prefix = `${API_SERVER_HOST}/api/diary`

export const getList = async (pageParam) => {

    console.log(prefix)

    const {page, size} = pageParam


    console.log(pageParam)
    console.log(`${prefix}/list`)

    const res = await axios.get(`${prefix}/list`, {params : {page:page, size:size}})

    

    console.log(res.data)

    return res.data
}

