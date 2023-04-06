import React from 'react';
import {Route, Routes} from 'react-router-dom';
import Items from './components/pages/item/Itmes';
import Header from './components/common/appbar/AppBar';
import Footer from './components/common/footer/Footer';
import Joinform from './components/pages/join/Joinform';
import SignIn from './components/pages/login/SignIn';
import MainPage from './components/pages/Mainpage/Mainpage';
import Mypage from './components/pages/Mypage/Mypage';
import PasswordValidation from './components/pages/Validation/PasswordValidation';
import UserModifyForm from './components/pages/UserInfoModify/UserModifyForm';
import PasswordModiftForm from './components/pages/UserInfoModify/PasswordModifyForm';


function App() {

    return (
      <>
          <Header/>
          <Routes>
              <Route path='/' element={<MainPage/>}/>
              <Route path='/items' element={<Items/>}/>
              <Route path='/join' element={<Joinform/>}/>
              <Route path='/login' element={<SignIn/>}/>
              <Route path='/mypage' element={<Mypage />} />
              <Route path='/mypage/password/validation' element={<PasswordValidation />} />
              <Route path='/mypage/info/modify' element={<UserModifyForm />} />
              <Route path='/mypage/password/modify' element={<PasswordModiftForm />} />
          </Routes>
          <Footer/>
      </>
    );

}




export default App;
