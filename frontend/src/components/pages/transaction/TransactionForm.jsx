import React from 'react';
import { TextField, Button, Grid, Box } from '@mui/material';

const TransactionForm = ({ transaction, onChange, onUpdate }) => {
  return (
    <Box component="form" sx={{ mt: 3 }}>
      <Grid container spacing={2}>
        <Grid item xs={6}>
          <TextField
            fullWidth
            name="transactionCode"
            label="거래처코드"
            value={transaction.accountCode}
            onChange={onChange}
            disabled
          />
        </Grid>
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
        <Grid item xs={12}>
          <TextField
            fullWidth
            name="note"
            label="비고"
            value={transaction.note}
            onChange={onChange}
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
