import React from 'react';
import { Route, Routes, useLocation } from 'react-router-dom';
import Header from './components/common/appbar/ResponsiveAppBar';
import Footer from './components/common/footer/Footer';
import Joinform from './components/pages/join/Joinform';
import SignIn from './components/pages/login/SignIn';
import Items from './components/pages/item/Itmes';
import Mypage from './components/pages/Mypage/Mypage';
import PasswordValidation from './components/pages/Validation/PasswordValidation';
import UserModifyForm from './components/pages/UserInfoModify/UserModifyForm';
import PasswordModifyForm from './components/pages/UserInfoModify/PasswordModifyForm';
import Transaction from './components/pages/transaction/Transaction';
import MainPage from './components/pages/mainpage/MainPage';

function App() {
    const location = useLocation();
    
    // SignIn 페이지에서는 헤더를 숨기기 위해 경로를 체크합니다.
    const hideHeader = location.pathname === '/';

    return (
      <>
          {!hideHeader && <Header />}
          <Routes>
              <Route path='/' element={<SignIn />} />
              <Route path='/home' element={<MainPage />} />
              <Route path='/items' element={<Items />} />
              <Route path='/join' element={<Joinform />} />
              <Route path='/mypage' element={<Mypage />} />
              <Route path='/mypage/password/validation' element={<PasswordValidation />} />
              <Route path='/mypage/info/modify' element={<UserModifyForm />} />
              <Route path='/mypage/password/modify' element={<PasswordModifyForm />} />
              <Route path='/transaction' element={<Transaction />} />
          </Routes>
          <Footer />
      </>
    );
}

export default App;
