import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import Menu from '@mui/material/Menu';
import MenuIcon from '@mui/icons-material/Menu';
import Container from '@mui/material/Container';
import Button from '@mui/material/Button';
import MenuItem from '@mui/material/MenuItem';
import { useNavigate } from 'react-router-dom';
import { useRecoilValue } from 'recoil';
import { userState } from '../../../functions/GlobalState';
import AccountPopover from './AccountPopover';
import { createTheme } from '@mui/material';
import { ThemeProvider } from '@mui/material';
import Logo from '../../../assets/appBar/logo.png';

const customTheme = createTheme({
  palette: {
    secondary: {
      main: "#191970",
    }
  }
});

function ResponsiveAppBar() {

  const navigate = useNavigate();
  const user = useRecoilValue(userState);

  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElSubMenu, setAnchorElSubMenu] = React.useState(null);

  const handleOpenNavMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElNav(event.currentTarget);
  };

  const handleOpenSubMenu = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorElSubMenu(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    setAnchorElNav(null);
    setAnchorElSubMenu(null);
  };

  const handleMenuClick = (path: string) => {
    handleCloseNavMenu();
    navigate(path);
  };

  return (
    <ThemeProvider theme={customTheme}>
      <AppBar position="static" color={"secondary"}>
        <Container maxWidth="xl">
          <Toolbar disableGutters>
            <Typography
              noWrap
              component="a"
              href="/home"
              sx={{
                mr: 2,
                display: { xs: 'none', md: 'flex' },
                fontFamily: 'DoHyeon',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              <img style={{ display: { xs: 'none', md: 'flex' }, width: 100, height: 90, marginRight: 40, marginTop: -6, marginBottom: -6 }} src={Logo} alt="" />
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'flex', md: 'none' } }}>
              <IconButton
                size="large"
                aria-label="account of current user"
                aria-controls="menu-appbar"
                aria-haspopup="true"
                onClick={handleOpenNavMenu}
                color="inherit"
              >
                <MenuIcon />
              </IconButton>
              <Menu
                id="menu-appbar"
                anchorEl={anchorElNav}
                anchorOrigin={{
                  vertical: 'bottom',
                  horizontal: 'left',
                }}
                keepMounted
                transformOrigin={{
                  vertical: 'top',
                  horizontal: 'left',
                }}
                open={Boolean(anchorElNav)}
                onClose={handleCloseNavMenu}
                sx={{
                  display: { xs: 'block', md: 'none' },
                }}
              >
                <MenuItem key="기준정보 관리" onClick={handleOpenSubMenu}>
                  <Typography textAlign="center">기준정보 관리</Typography>
                </MenuItem>
                <Menu
                  id="submenu-appbar"
                  anchorEl={anchorElSubMenu}
                  open={Boolean(anchorElSubMenu)}
                  onClose={handleCloseNavMenu}
                  anchorOrigin={{
                    vertical: 'bottom', // anchor가 메뉴 아래쪽에 위치하도록 조정
                    horizontal: 'left',
                  }}
                  transformOrigin={{
                    vertical: 'top', // 메뉴가 기준 요소보다 약간 아래로 위치하도록 조정
                    horizontal: 'left',
                  }}
                >
                  <MenuItem onClick={() => handleMenuClick('/items')}>
                    <Typography textAlign="center">품목 단위 등록</Typography>
                  </MenuItem>
                  <MenuItem onClick={() => handleMenuClick('/transaction')}>
                    <Typography textAlign="center">거래처 관리</Typography>
                  </MenuItem>
                </Menu>

                <MenuItem onClick={() => handleMenuClick('/equipment')}>
                  <Typography textAlign="center">설비 현황 보기</Typography>
                </MenuItem>
                <MenuItem onClick={() => handleMenuClick('/inventory')}>
                  <Typography textAlign="center">재고 관리</Typography>
                </MenuItem>
                <MenuItem onClick={() => handleMenuClick('/orders')}>
                  <Typography textAlign="center">작업지시 메뉴</Typography>
                </MenuItem>

                {user ? (<Box></Box>) : (
                  <Box>
                    <MenuItem key='회원가입' onClick={() => handleMenuClick('/join')}>
                      <Typography textAlign="center">회원가입</Typography>
                    </MenuItem>
                    <MenuItem key='로그인' onClick={() => handleMenuClick('/login')}>
                      <Typography textAlign="center">로그인</Typography>
                    </MenuItem>
                  </Box>
                )}

                {/* 어드민일 경우에만 회원관리 메뉴 표시 */}
                {user?.role === 'admin' && (
                  <MenuItem onClick={() => handleMenuClick('/user-management')}>
                    <Typography textAlign="center">회원관리</Typography>
                  </MenuItem>
                )}
              </Menu>
            </Box>

            <Typography
              noWrap
              component="a"
              href='/'
              sx={{
                mr: 2,
                display: { xs: 'flex', md: 'none' },
                flexGrow: 1,
                fontFamily: 'monospace',
                fontWeight: 700,
                letterSpacing: '.3rem',
                color: 'inherit',
                textDecoration: 'none',
              }}
            >
              <img style={{ width: 70, height: 70 }} src={Logo} alt="" />
            </Typography>

            <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
              <Button
                key="기준정보 관리"
                onClick={handleOpenSubMenu}
                sx={{ my: 2, color: 'white', display: 'block', marginRight: 1 }}
              >
                기준정보 관리
              </Button>
              <Menu
                id="submenu-appbar"
                anchorEl={anchorElSubMenu}
                open={Boolean(anchorElSubMenu)}
                onClose={handleCloseNavMenu}
                anchorOrigin={{
                  vertical: 'bottom', // anchor가 메뉴 아래쪽에 위치하도록 조정
                  horizontal: 'left',
                }}
                transformOrigin={{
                  vertical: 'top', // 메뉴가 기준 요소보다 약간 아래로 위치하도록 조정
                  horizontal: 'left',
                }}
              >
                <MenuItem onClick={() => handleMenuClick('/items')}>
                  <Typography textAlign="center">품목 단위 등록</Typography>
                </MenuItem>
                <MenuItem onClick={() => handleMenuClick('/transaction')}>
                  <Typography textAlign="center">거래처 관리</Typography>
                </MenuItem>
              </Menu>

              <Button
                key="설비 현황 보기"
                onClick={() => handleMenuClick('/equipment')}
                sx={{ my: 2, color: 'white', display: 'block', marginRight: 1 }}
              >
                설비 현황 보기
              </Button>
              <Button
                key="재고 관리"
                onClick={() => handleMenuClick('/inventory')}
                sx={{ my: 2, color: 'white', display: 'block', marginRight: 1 }}
              >
                재고 관리
              </Button>
              <Button
                key="작업지시 메뉴"
                onClick={() => handleMenuClick('/orders')}
                sx={{ my: 2, color: 'white', display: 'block', marginRight: 1 }}
              >
                작업지시 메뉴
              </Button>
            </Box>

            <Box sx={{ flexGrow: 0 }}>
              {user ? (
                <>
                  <AccountPopover />
                </>
              ) : (
                <>
                  <Box sx={{ flexGrow: 1, display: { xs: 'none', md: 'flex' } }}>
                    <Button
                      key='join'
                      onClick={() => { navigate('/join') }}
                      sx={{ my: 2, color: 'white', display: 'block' }}
                    >
                      회원가입
                    </Button>
                    <Button
                      key='login'
                      onClick={() => { navigate('/login') }}
                      sx={{ my: 2, color: 'white', display: 'block' }}
                    >
                      로그인
                    </Button>
                  </Box>
                </>)}
              {user?.role === 'admin' && (
                <Button
                  key="회원관리"
                  onClick={() => handleMenuClick('/user-management')}
                  sx={{ my: 2, color: 'white', display: 'block', marginLeft: 1 }}
                >
                  회원관리
                </Button>
              )}
            </Box>
          </Toolbar>
        </Container>
      </AppBar>
    </ThemeProvider>
  );
}
export default ResponsiveAppBar;
