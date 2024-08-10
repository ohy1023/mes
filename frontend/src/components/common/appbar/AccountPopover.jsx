import { useEffect, useState } from 'react';
import { alpha } from '@mui/material/styles';
import { Box, Divider, Typography, Stack, MenuItem, Avatar, IconButton, Popover } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useSetRecoilState } from 'recoil';
import { userState } from '../../../functions/GlobalState';
import { removeCookie } from '../../../functions/cookie';
import Api from '../../../functions/customApi';
import styles from './AccountPopover.module.css'; // CSS 모듈 임포트

const MENU_OPTIONS = [
  {
    label: '내 정보',
    icon: 'eva:person-fill',
  },
];

// ----------------------------------------------------------------------

export default function AccountPopover() {
  const [open, setOpen] = useState(null);
  const navigate = useNavigate();
  const setUser = useSetRecoilState(userState);
  const email = localStorage.getItem('email');
  const userName = localStorage.getItem('userName');

  const [account, setAccount] = useState([]);

  const getInfo = async () => {
    await Api.get("/api/v1/users")
      .then(function (response) {
        setAccount(response.data.result);
      })
      .catch(function (err) {
        console.log(err);
        alert("유저 정보 조회 실패");
      });
  };

  useEffect(() => {
    getInfo();
  }, [sessionStorage.getItem("temp")]);

  const logoutUser = async () => {
    await Api.get("/api/v1/users/logout")
      .then(function (response) {
        removeCookie('access');
        removeCookie('refresh');
        localStorage.removeItem('email');
        localStorage.removeItem('userName');
        localStorage.removeItem('imageUrl');
        localStorage.removeItem('createAt');
        setUser('');
        alert("로그아웃이 완료되었습니다.");
        navigate('/');
      })
      .catch(function (err) {
        console.log(err);
        alert("로그아웃 실패!");
      });
  };

  const handleOpen = (event) => {
    setOpen(event.currentTarget);
  };

  const handleClose = () => {
    setOpen(null);
  };

  return (
    <>
      <IconButton
        onClick={handleOpen}
        className={`${styles.avatarButton} ${open ? styles.avatarButtonOpen : ''}`}
      >
        <Avatar src={account.imageUrl} />
      </IconButton>

      <Popover
        open={Boolean(open)}
        anchorEl={open}
        onClose={handleClose}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'right' }}
        transformOrigin={{ vertical: 'top', horizontal: 'right' }}
        PaperProps={{ className: styles.popoverPaper }}
      >
        <Box className={styles.userInfoBox}>
          <Typography variant="subtitle2" className={styles.userName}>
            {userName}
          </Typography>
          <Typography variant="body2" className={styles.userEmail}>
            {email}
          </Typography>
        </Box>

        <Divider className={styles.divider} />

        <Stack sx={{ p: 1 }}>
          <MenuItem
            key={MENU_OPTIONS[0].label}
            onClick={() => {
              navigate('/mypage');
              handleClose();
            }}
            className={styles.menuItem}
          >
            {MENU_OPTIONS[0].label}
          </MenuItem>
        </Stack>

        <Divider className={styles.divider} />

        <MenuItem
          onClick={() => {
            logoutUser();
            handleClose();
          }}
          className={`${styles.menuItem} ${styles.logoutMenuItem}`}
        >
          로그아웃
        </MenuItem>
      </Popover>
    </>
  );
}
