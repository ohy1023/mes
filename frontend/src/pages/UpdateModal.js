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
    axios.put(`api/items/${props.itemId}`,{
      companyName : companyName,
    })
        .then((response) => {
          console.log("success")
        })
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
            {props.itemId}
        </p>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={update}>수정</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default UpdateModal;