import BasicLayout from "../../commonLayout/layouts/BasicLayout";
import React from 'react';
import DiarySideMenu from "./DiarySideMenu";
import ListComponent from "../../components/diary/ListComponent";
import { Outlet } from "react-router-dom";
const DiaryPage = () => {
    
        return (
            <BasicLayout side={<DiarySideMenu/>} main={<ListComponent/>}>
               
            </BasicLayout>
        )
}

export default DiaryPage;