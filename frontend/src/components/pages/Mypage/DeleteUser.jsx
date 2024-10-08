import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import { Modal, Button } from 'react-bootstrap';
import Api from '../../../functions/customApi';
import { useNavigate } from 'react-router-dom';
import { removeCookie } from '../../../functions/cookie';
import { useSetRecoilState } from 'recoil';
import { userState } from '../../../functions/GlobalState';

function DeleteUser(props) {
  const navigate = useNavigate();
  const setUser = useSetRecoilState(userState);

  const deleteUser = async () => {
    try {
      const response = await Api.delete('/api/v1/users');
      removeCookie('access');
      removeCookie('refresh');
      localStorage.removeItem('email');
      localStorage.removeItem('userName');
      localStorage.removeItem('imageUrl');
      localStorage.removeItem('createAt');
      setUser('');
      alert(response.data.result);
      navigate('/');
    } catch (error) {
      console.error("회원 탈퇴 실패:", error);
      alert("탈퇴 실패: " + (error.response?.data?.message || "알 수 없는 오류가 발생했습니다."));
    }
  };

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          회원 탈퇴
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p>정말 탈퇴 하시겠습니까?</p>
      </Modal.Body>
      <Modal.Footer>
        <Button
          onClick={() => {
            deleteUser();
            props.onHide();
          }}
        >
          탈퇴
        </Button>
        <Button onClick={props.onHide}>취소</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default DeleteUser;
