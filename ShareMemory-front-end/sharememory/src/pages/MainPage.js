import { Button, Container } from 'react-bootstrap';
import { useSelector } from 'react-redux';
import BasicLayout from '../layouts/BasicLayout';
const MainPage = () => {
    const loginState = useSelector(state => state.loginSlice)
    const state = useSelector(state => state)
    console.log("main LoginState : ", loginState)
    console.log("main state : ", state)
    return ( 
        // <Container>
        //     <h1 className="text-center">Hello, React-Bootstrap!</h1>
        //     <Button variant="success">Primary Button</Button>
        // </Container>
        <BasicLayout></BasicLayout>
    )
}

export default MainPage;