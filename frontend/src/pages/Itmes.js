import React, {useEffect, useState} from 'react';
import {GridColDef, GridActionsCellItem} from '@mui/x-data-grid';
import DeleteIcon from "@material-ui/icons/Delete";
import AutoFixHighOutlinedIcon from '@mui/icons-material/AutoFixHighOutlined';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Table, Button, Form, InputGroup} from 'react-bootstrap';
import axios from 'axios';
import MyVerticallyCenteredModal from './Modal';
import UpdateModal from './UpdateModal';
import DeleteModal from './DeleteModal';
import {
  DataGridPremium,
  GridToolbarContainer,
  GridToolbarExport,
} from '@mui/x-data-grid-premium';

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
    axios.post('api/items',{
      companyName : companyName,
      itemCode : itemCode,
      itemName : itemName,
      itemType : itemType,
      itemGroup : itemGroup,
    })
    axios.get('api/items')
        .then(response => setItems(response.data))
  }

  useEffect(() => {
    axios.get('api/items/company')
        .then(response => setCompanyList(response.data))
        .catch((error) => {
          console.log("error")
        })
  }, [])

  useEffect(() => {
    axios.get('api/items')
        .then(response => setItems(response.data))
        .catch((error) => {
          console.log("error")
        })}, [])

  const findDetailItems = () => {
    axios.get(`api/items/search/${companyName}/${itemType}`)
    .then(response => setItems(response.data))}

    
  const columns: GridColDef[] = [
    { field: 'id', headerName: 'NO', width: 150},
    { field: 'companyName', headerName: '회사', width: 150 },
    { field: 'itemCode', headerName: '품목코드', width: 150 },
    { field: 'itemName', headerName: '품목명', width: 150 },
    { field: 'itemType', headerName: '품목유형', width: 150 },
    { field: 'partNumber', headerName: 'P/N', width: 150 },
    { field: 'itemGroup', headerName: '품목군', width: 150 },
    { field: 'standard', headerName: '규격', width: 150 },
    { field: 'receiptPaymentUnit', headerName: '수불단위', width: 150 },
    { field: 'purchaseUnit', headerName: '매입단위', width: 150 },
    { field: 'purchaseUnitQuantity', headerName: '매입단위수량', width: 150 },
    { field: 'requiredUnit', headerName: '소요단위', width: 150 },
    { field: 'requiredUnitQuantity', headerName: '소요단위수량', width: 150 },
    { field: 'yieldUnit', headerName: '수율단위', width: 150 },
    { field: 'yieldUnitQuantity', headerName: '수율단위수량', width: 150 },
    {
      field: 'actions',
      type: 'actions',
      headerName: '수정 삭제',
      getActions: (params) => [
        <GridActionsCellItem icon={<AutoFixHighOutlinedIcon />} onClick={setUpdateModalShow} label="Delete"/>,
        <UpdateModal
            itemId = {params.id}
            show={updatemodalShow}
            onHide={() => setUpdateModalShow(false)}
          />,
        <GridActionsCellItem icon={<DeleteIcon />} onClick={setDeleteModalShow} label="Delete" />,
        <DeleteModal
            itemId = {params.id}
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
                <option>전체</option>
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
                <option>전체</option>
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
        <div style={{height: 500, width: '100%'}}>
          <DataGridPremium rows={items} columns={columns} components={{
          Toolbar: CustomToolbar,
        }}/>
        </div>
      </div>
  );
  
  
}      

function CustomToolbar() {
  return (
    <GridToolbarContainer>
      <GridToolbarExport />
    </GridToolbarContainer>
  );
}

export default Items;