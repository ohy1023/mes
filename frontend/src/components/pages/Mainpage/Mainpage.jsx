import React from 'react';
import Paper from '@mui/material/Paper';
import { styled } from '@mui/material/styles';
import Grid from '@mui/material/Grid';
import mainPageBackgroundImage from '../../../assets/mainpage/background.jpg';
import styles from "./MainPage.module.css";
import QueryStatsOutlinedIcon from '@mui/icons-material/QueryStatsOutlined';
import AddCardOutlinedIcon from '@mui/icons-material/AddCardOutlined';
import ForumOutlinedIcon from '@mui/icons-material/ForumOutlined';
import { useNavigate } from 'react-router-dom';

function MainPage() {
  const navigate = useNavigate();

  const clickToMainPage = () => {
    navigate('/items');
  }

  const Item = styled(Paper)(({ theme }) => ({
    ...theme.typography.body2,
    padding: theme.spacing(0.5),
    textAlign: 'center',
    color: theme.palette.text.secondary,
    backgroundColor: '#ffffff00', // 투명한 배경 추가
    boxShadow: '0px 4px 20px rgba(0, 0, 0, 0.1)', // 그림자 효과 추가
    transition: 'transform 0.3s ease-in-out', // 호버 시 변환 효과 추가
    '&:hover': {
      transform: 'scale(1.05)', // 호버 시 확대 효과
      backgroundColor: '#ffffffcc', // 배경색 변경
    },
  }));

  return (
    <>
      <div className={styles.mainBackground}>
        <div className={styles.mainBackgroundOverlay} />
      </div>
      <div className={styles.mainTextOnImage}>
        <div className={styles.mainFont}>MES</div>
        <div className={styles.mainFont2}>
          <p id="ImageLetter">
            관리자가 품목 등록, 조회, 수정 등을 할 수 있도록 도와주는 MES 홈페이지입니다.
          </p>
        </div>
        <div className={styles.mainFont3}>
          <button className="btn-hover color-3" onClick={clickToMainPage}>
            품목 등록 바로가기
          </button>
        </div>
      </div>
      <div>
        <Grid container spacing={2} className={styles.gridContainer}>
          <Grid item xs={12} sm={6} md={4}>
            <a href="/items">
              <Item>
                <AddCardOutlinedIcon className={styles.icon} />
                <span className={styles.gridItemText}>품목 단위 정보 등록</span>
              </Item>
            </a>
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <a href="/transaction">
              <Item>
                <QueryStatsOutlinedIcon className={styles.icon} />
                <span className={styles.gridItemText}>설비 가동 현황</span>
              </Item>
            </a>
          </Grid>
          <Grid item xs={12} sm={6} md={4}>
            <a href="/community">
              <Item>
                <ForumOutlinedIcon className={styles.icon} />
                <span className={styles.gridItemText}>사원 관리</span>
              </Item>
            </a>
          </Grid>
        </Grid>
      </div>
    </>
  );
}

export default MainPage;
