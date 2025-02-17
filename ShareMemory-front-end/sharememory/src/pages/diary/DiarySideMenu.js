import { Card, ListGroup } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import "../../css/DiarySideMenu.css";

const DiarySideMenu = () => {
    const navigate = useNavigate();

    return (
        <Card className="side-menu-card shadow-sm">
            <Card.Body>
                <ListGroup>
                    <ListGroup.Item 
                        action 
                        onClick={() => navigate('/diary')} 
                        className="side-menu-item"
                    >
                        Memories
                    </ListGroup.Item>
                    <ListGroup.Item action className="side-menu-item">
                        Record Memory
                    </ListGroup.Item>
                    <ListGroup.Item action className="side-menu-item">
                        작성 기록
                    </ListGroup.Item>
                </ListGroup>
            </Card.Body>
        </Card>
    );
};

export default DiarySideMenu;