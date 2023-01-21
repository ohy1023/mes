import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Modal,Button} from 'react-bootstrap';
import axios from 'axios';

function DeleteModal(props) {

  const deleteItem = () => {
    axios.delete(`api/items/${props.itemId}`);
  }

  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          품목 삭제 
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <p>
           정말 삭제 하시겠습니까?
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={()=>{
          deleteItem()
          props.onHide()
          }}>삭제</Button>
        <Button onClick={props.onHide}>취소</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default DeleteModal;