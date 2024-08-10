import React from 'react';
import { TextField, Button, Grid, Box } from '@mui/material';

const TransactionForm = ({ transaction, onChange, onUpdate }) => {

  const handlePostcode = () => {
    new window.daum.Postcode({
      oncomplete: function (data) {
        let roadAddr = data.roadAddress; // 도로명 주소 변수
        let extraRoadAddr = ''; // 참고 항목 변수

        // 법정동명이 있을 경우 추가한다.
        if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
          extraRoadAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== '' && data.apartment === 'Y') {
          extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
        }
        if (extraRoadAddr !== '') {
          extraRoadAddr = ' (' + extraRoadAddr + ')';
        }

        // 필드 업데이트
        onChange({ target: { name: 'postcode', value: data.zonecode } });
        onChange({ target: { name: 'roadAddress', value: roadAddr } });
        onChange({ target: { name: 'jibunAddress', value: data.jibunAddress } });
        onChange({ target: { name: 'extraAddress', value: extraRoadAddr } });
      }
    }).open();
  };

  return (
    <Box component="form" sx={{ mt: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="businessNumber"
            label="사업자번호"
            value={transaction.businessNumber}
            onChange={onChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="transactionType"
            label="거래처유형"
            value={transaction.transactionType}
            onChange={onChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="phoneNumber"
            label="전화번호"
            value={transaction.accountTel}
            onChange={onChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="transactionName"
            label="거래처명"
            value={transaction.accountName}
            onChange={onChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="representative"
            label="대표자"
            value={transaction.representativeName}
            onChange={onChange}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="postcode"
            label="우편번호"
            value={transaction.postcode || ''}
            onChange={onChange}
            placeholder="우편번호"
            InputProps={{
              endAdornment: (
                <Button variant="outlined" onClick={handlePostcode}>
                  우편번호 찾기
                </Button>
              )
            }}
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="roadAddress"
            label="도로명 주소"
            value={transaction.roadAddress || ''}
            onChange={onChange}
            placeholder="도로명주소"
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="jibunAddress"
            label="지번 주소"
            value={transaction.jibunAddress || ''}
            onChange={onChange}
            placeholder="지번주소"
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="detailAddress"
            label="상세 주소"
            value={transaction.detailAddress || ''}
            onChange={onChange}
            placeholder="상세주소"
          />
        </Grid>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="extraAddress"
            label="참고 항목"
            value={transaction.extraAddress || ''}
            onChange={onChange}
            placeholder="참고항목"
          />
        </Grid>
        <Grid item xs={12} style={{ textAlign: 'center' }}>
          <Button variant="contained" color="primary" onClick={onUpdate}>
            수정
          </Button>
        </Grid>
      </Grid>
    </Box>
  );
};

export default TransactionForm;
