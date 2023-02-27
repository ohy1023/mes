import React from 'react';
import {Route, Routes} from 'react-router-dom';
import Items from './components/pages/item/Itmes';
import Header from './components/common/appbar/AppBar';
import Footer from './components/common/footer/Footer';
import Joinform from './components/pages/join/Joinform';
import SignIn from './components/pages/login/SignIn';
import MainPage from './components/pages/Mainpage/Mainpage';


function App() {

    return (
      <>
          <Header/>
          <Routes>
              <Route path='/' element={<MainPage/>}/>
              <Route path='/items' element={<Items/>}/>
              <Route path='/join' element={<Joinform/>}/>
              <Route path='/login' element={<SignIn/>}/>
          </Routes>
          <Footer/>
      </>
    );

}




export default App;
