import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar,Nav} from 'react-bootstrap';
import {Route, Routes, useNavigate} from 'react-router-dom';
import Items from './pages/Itmes';


function App() {

    const navigate = useNavigate();

    return (
      <>
          <Navbar bg="dark" variant="dark">
              <Navbar.Brand style={{margin:'10px'}}><Nav.Link onClick={()=>{navigate('/')}} >MES</Nav.Link></Navbar.Brand>
              <Nav className="me-auto">
                  <Nav.Link onClick={()=>{navigate('/items')}}>품목단위정보 등록</Nav.Link>
              </Nav>
          </Navbar>
          <Routes>
              <Route path='/' element={<>메인 페이지2</>}/>
              <Route path='/items' element={<Items/>}/>
          </Routes>
      </>
    );

}



export default App;
