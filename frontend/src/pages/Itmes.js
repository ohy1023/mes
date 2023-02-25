import React, {useEffect, useState} from 'react';
import {GridColDef, GridActionsCellItem,GridToolbarContainer,GridToolbarExport,DataGrid} from '@mui/x-data-grid';
import DeleteIcon from '@mui/icons-material/Delete';
import AutoFixHighOutlinedIcon from '@mui/icons-material/AutoFixHighOutlined';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Table, Button, Form, InputGroup} from 'react-bootstrap';
import axios from 'axios';
import MyVerticallyCenteredModal from './Modal';
import UpdateModal from './UpdateModal';
import DeleteModal from './DeleteModal';
import { Box } from '@mui/material';


function Items() {

  const [items, setItems] = useState([])

  const [companyList, setCompanyList] = useState([])

  const [companyName, setCompanyName] = useState("")
  const [itemCode, setItemCode] = useState("")
  const [itemName, setItemName] = useState("")
  const [itemType, setItemType] = useState("")
  const [itemGroup, setItemGroup] = useState("")

  const [modalShow, setModalShow] = useState(false);

  const [updatemodalShow, setUpdateModalShow] = useState(false);
  const [deletemodalShow, setDeleteModalShow] = useState(false);

  const [clickedRow, setClickedRow] = useState(0);

  const handleCompanyName = (e) => {
    setCompanyName(e.target.value);
  };
  const handleItemCode = (e) => {
    setItemCode(e.target.value);
  };
  const handleItemName = (e) => {
    setItemName(e.target.value);
  };
  const handleItemType = (e) => {
    setItemType(e.target.value);
  };
  const handleItemGroup = (e) => {
    setItemGroup(e.target.value);
  };

  const register = () => {
    axios.post('api/v1/items',{
      companyName : companyName,
      itemCode : itemCode,
      itemName : itemName,
      itemType : itemType,
      itemGroup : itemGroup,
    }).then( response => setItems(items.concat(response.data)))
    
  }

  useEffect(() => {
    axios.get('api/v1/items/company')
        .then(response => setCompanyList(response.data))
        .catch((error) => {
          console.log("error")
        })
  }, [])

  useEffect(() => {
    axios.get('api/v1/items')
        .then(response => setItems(response.data))
        .catch((error) => {
          console.log("error")
        })}, [setItems])

  function updateHandler() {
    setUpdateModalShow(true)
  }
  function deleteHandler() {
    setDeleteModalShow(true)
  }
  
  const findDetailItems = () => {
    axios.get(`api/v1/items/search/`,{
      params:{
        companyName:companyName,
        itemType:itemType
      }
    }).then(response => setItems(response.data))}

  const onButtonClick = (e, row) => {
    e.stopPropagation();
    setClickedRow(row)
  };
    
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'id', hide: true},
    { field: 'companyName', headerName: '회사', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'itemCode', headerName: '품목코드', width: 150, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'itemName', headerName: '품목명', width: 150, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'itemType', headerName: '품목유형', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'partNumber', headerName: 'P/N', width: 150, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'itemGroup', headerName: '품목군', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'standard', headerName: '규격', width: 125, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'receiptPaymentUnit', headerName: '수불단위', width: 125, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'purchaseUnit', headerName: '매입단위', width: 125, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'purchaseUnitQuantity', headerName: '매입단위수량', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'requiredUnit', headerName: '소요단위', width: 125, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'requiredUnitQuantity', headerName: '소요단위수량', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'yieldUnit', headerName: '수율단위', width: 125, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    { field: 'yieldUnitQuantity', headerName: '수율단위수량', width: 100, headerAlign: 'center',align: 'center',headerClassName: 'super-app-theme--header'},
    {
      field: 'actions',
      type: 'actions',
      headerName: '수정 삭제',
      width: 100,
      headerAlign: 'center',align: 'center',
      headerClassName: 'super-app-theme--header',
      getActions: (params) => [
        <GridActionsCellItem icon={<AutoFixHighOutlinedIcon />} onClick={(e)=>{
          onButtonClick(e,params.row)
          updateHandler()
        }} label="Delete"/>,
        <UpdateModal
          childSetItems = {setItems}
          childItems = {items}
          itemId = {clickedRow.id === 'undefined' ? 0:clickedRow.id}
          show={updatemodalShow}
          onHide={() => setUpdateModalShow(false)}
          />,
        <GridActionsCellItem icon={<DeleteIcon/>} onClick={(e)=>{
          onButtonClick(e,params.row)
          deleteHandler()
        }} label="Delete" />,
        <DeleteModal
          childSetItems = {setItems}
          childItems = {items}
          itemId = {clickedRow.id === 'undefined' ? 0:clickedRow.id}
          show={deletemodalShow}
          onHide={() => setDeleteModalShow(false)}
          />
      ]
    }
    
  ];


  return (
      <div style={{
        paddingLeft: '50px',
        paddingRight: '50px',
        height: '1400px',
        backgroundColor: 'Snow'
      }}>
        <br/>
        <h3>⚙ 품목단위정보 등록
          <Button variant="primary" onClick={findDetailItems} size='lg'
                  style={{position: 'absolute', right: 0, marginRight: '140px'}}>
            조회
          </Button>
          <Button variant="primary" onClick={register} size='lg'
                  style={{position: 'absolute', right: 0, marginRight: '60px'}}>
            저장
          </Button>
        </h3>
        <br/>

        <Table>
          <tbody>
          <tr>
            <td style={{width: '110px', fontSize: '23px'}}>회사</td>
            <td style={{width: '100px'}}>
              <Form.Select onChange={handleCompanyName} value={companyName}>
                <option value="">전체</option>
                {companyList.map((item) => (
                    <option value={item} key={item}>
                      {item}
                    </option>
                ))}
              </Form.Select>
            </td>
            <td style={{width: '110px', fontSize: '23px'}}>품목분류</td>
            <td style={{width: '100px'}}>
              <Form.Select onChange={handleItemType} value={itemType}>
                <option value="">전체</option>
                <option type="text" value="제품">제품</option>
              </Form.Select>
            </td>
            <td style={{width: '110px', fontSize: '23px'}}>품질군</td>
            <td style={{width: '100px'}}>
              <Form.Select onChange={handleItemGroup} value={itemGroup}>
                <option>전체</option>
                <option value="통합테스트">통합테스트</option>
              </Form.Select>
            </td>
            <td style={{width: '110px', fontSize: '23px'}}>거래처</td>
            <td style={{width: '200px'}}>
              <InputGroup className="mb-3">
                <Form.Control
                    placeholder="CODE"
                    aria-label="CODE"
                />
                <Button variant="primary" size='sm' onClick={() => setModalShow(true)}>
                  조회
                </Button>

                <MyVerticallyCenteredModal
                    show={modalShow}
                    onHide={() => setModalShow(false)}
                />

                <Form.Control
                    placeholder="NAME"
                    aria-label="NAME"
                />
              </InputGroup>
            </td>
            <td></td>
          </tr>
          </tbody>
        </Table>
        <Table>
          <tbody>
          <tr>
            <td style={{width: '110px', fontSize: '23px'}}>품목코드</td>
            <td style={{width: '100px'}}>
              <Form.Control onChange={handleItemCode} value={itemCode}/>
            </td>
            <td style={{width: '110px', fontSize: '23px'}}>품명</td>
            <td style={{width: '100px'}}>
              <Form.Control
                  onChange={handleItemName} value={itemName}
              />
            </td>
            <td style={{width: '110px', fontSize: '23px'}}>사용유무</td>
            <td style={{width: '100px'}}>
              <Form.Select>
                <option value="사용">사용</option>
                <option value="미사용">미사용</option>
              </Form.Select>
            </td>
            <td></td>
          </tr>
          </tbody>
        </Table>
        <br/>

        <h4>📃 품목 목록</h4>
        <div>
        <Box
          sx={{
            height: 800,
            width: '100%',
            '& .super-app-theme--header': {
              backgroundColor: 'rgba(54, 73, 81, 1)',
              color:'white',
            },
          }}>
          <DataGrid rows={items} columns={columns} initialState={{
            sorting: {
              sortModel: [{ field: 'id', sort: 'asc' }],
            },
            }} components={{
                Toolbar: CustomToolbar,
               }}/>
        </Box>
          
        </div>
      </div>
  );
  
  
}      

function CustomToolbar() {
  return (
    <GridToolbarContainer>
      <GridToolbarExport 
        csvOptions={{
        fileName: 'itemList',
        delimiter: ',',
        utf8WithBom: true,
      }}/>
    </GridToolbarContainer>
  );
}

export default Items;