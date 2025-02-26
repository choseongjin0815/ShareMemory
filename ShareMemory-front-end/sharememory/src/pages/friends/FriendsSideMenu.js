import { Card, ListGroup } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "../../css/DiarySideMenu.css";
const FriendsSideMenu = () => {
    const navigate = useNavigate();

    return (
        <Card className="side-menu-card shadow-sm">
            <Card.Body>
                <ListGroup>
                    <ListGroup.Item 
                        action 
                        onClick={() => navigate('/friends')} 
                        className="side-menu-item"
                    >
                        유저 목록
                    </ListGroup.Item>
                    <ListGroup.Item 
                        action
                        onClick={() => navigate('/friends/friendList')}
                        className="side-menu-item">
                        친구 목록
                    </ListGroup.Item>
                    <ListGroup.Item 
                        action 
                        onClick={() => navigate('/friends/request')}
                        className="side-menu-item">
                        친구 요청 목록
                    </ListGroup.Item>
                </ListGroup>
            </Card.Body>
        </Card>
    );
}

export default FriendsSideMenu;