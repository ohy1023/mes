import React, {useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Modal,Button,Form} from 'react-bootstrap';
import axios from 'axios';

function UpdateModal(props) {
  const [companyName, setCompanyName] = useState("")

  const handleCompanyName = (e) => {
    setCompanyName(e.target.value);
  };

  const update = () => {
    axios.put(`api/v1/items/${props.itemId}`,{
      companyName : companyName,
    })
        .then((response) => props.childSetItems(props.childItems.map((item) => item.id === props.itemId ? {...item, companyName:companyName} : item)))
        .catch((error) => {
          console.log("error")
        })
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
          품목 수정
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h4>품목 수정</h4>
        <p>
            <Form.Control
              onChange={handleCompanyName} value={companyName}
            />
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={()=>{
          update()
          props.onHide()
          }}>수정</Button>
        <Button onClick={props.onHide}>취소</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default UpdateModal;