import { DataGrid } from '@mui/x-data-grid';
import React, {useEffect, useState} from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Modal, Button} from 'react-bootstrap';
import axios from 'axios';
import { Box } from '@mui/material';

function MyVerticallyCenteredModal(props) {

  const [accounts, setAccounts] = useState([])

  useEffect(() => {
    axios.get('api/v1/accounts')
        .then(response => setAccounts(response.data.result))
        .catch((error) => {
          console.log("error")
        })}, [setAccounts])
  
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'id', hide: true},
    { field: 'accountCode', headerName: '거래처 코드', width: 400, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'accountName', headerName: '거래처 이름', width: 400, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'}
  ]


  return (
    <Modal
      {...props}
      size="lg"
      aria-labelledby="contained-modal-title-vcenter"
      centered
    >
      <Modal.Header closeButton>
        <Modal.Title id="contained-modal-title-vcenter">
          거래처 목록 조회
        </Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <h4>거래처 목록</h4>
        <Box
          sx={{
            height: 300,
            width: '100%',
            '& .super-app-theme--header': {
              backgroundColor: 'rgba(54, 73, 81, 1)',
              color:'white',
            },
          }}>
          <DataGrid rows={accounts} columns={columns} initialState={{
            sorting: {
              sortModel: [{ field: 'id', sort: 'asc' }],
            },}} checkboxSelection/>
        </Box>
      </Modal.Body>
      <Modal.Footer>
        <Button onClick={props.onHide}>선택</Button>
        <Button onClick={props.onHide}>취소</Button>
      </Modal.Footer>
    </Modal>
  );
}

export default MyVerticallyCenteredModal;