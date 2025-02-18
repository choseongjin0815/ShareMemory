import BasicLayout from "../../commonLayout/layouts/BasicLayout";
import React from 'react';
import DiarySideMenu from "./DiarySideMenu";
import { Outlet } from "react-router-dom";
const DiaryPage = () => {
    
        return (
            <BasicLayout side={<DiarySideMenu/>} main={<Outlet/>}>
                
            </BasicLayout>
        )
}

export default DiaryPage;