import React, { useState, useEffect } from 'react';
import TransactionForm from './TransactionForm';
import TransactionTable from './TransactionTable';
import { Container, Button, TextField, Select, MenuItem, FormControl, InputLabel, Grid } from '@mui/material';
import Api from '../../../functions/customApi';

const Transaction = () => {
  const [transactions, setTransactions] = useState([]);
  const [selectedTransaction, setSelectedTransaction] = useState(null);
  const [selectedTransactions, setSelectedTransactions] = useState([]);
  const [filters, setFilters] = useState({
    accountCode: '',
    accountName: '',
    transactionType: '',
  });
  const [isDetailVisible, setIsDetailVisible] = useState(false);

  useEffect(() => {
    fetchTransactions();
  }, []);

  const fetchTransactions = () => {
    Api.get('/api/v1/accounts')
      .then(response => {
        if (response.data.resultCode === "SUCCESS") {
          setTransactions(response.data.result);
          if (response.data.result.length > 0) {
            setSelectedTransaction(response.data.result[0]);
          }
        }
      })
      .catch(error => {
        console.error("There was an error fetching the transactions!", error);
      });
  };

  const handleSelectTransaction = (transaction) => {
    setSelectedTransaction(transaction);
    setIsDetailVisible(true);
  };

  const handleCheckboxChange = (transactionId) => {
    setSelectedTransactions(prevSelected =>
      prevSelected.includes(transactionId)
        ? prevSelected.filter(id => id !== transactionId)
        : [...prevSelected, transactionId]
    );
  };

  const handleSelectAll = (isChecked) => {
    if (isChecked) {
      setSelectedTransactions(transactions.map(transaction => transaction.id));
    } else {
      setSelectedTransactions([]);
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setSelectedTransaction({
      ...selectedTransaction,
      [name]: value,
    });
  };

  const handleUpdate = () => {
    Api.put(`/api/v1/accounts/${selectedTransaction.id}`, selectedTransaction)
      .then(response => {
        setTransactions((prevTransactions) =>
          prevTransactions.map((transaction) =>
            transaction.id === selectedTransaction.id ? selectedTransaction : transaction
          )
        );
      })
      .catch(error => {
        console.error("There was an error updating the transaction!", error);
      });
  };

  const handleCreate = () => {
    const newTransaction = {
      accountCode: '',
      accountName: '',
      accountTel: '',
      transactionType: '',
      businessNumber: '',
      representativeName: '',
      note: '',
    };
    setSelectedTransaction(newTransaction);
    setIsDetailVisible(true);
  };

  const handleDeleteSelected = () => {
    const deletePromises = selectedTransactions.map(id => Api.delete(`/api/v1/accounts/${id}`));

    Promise.all(deletePromises)
      .then(() => {
        setTransactions(prevTransactions => prevTransactions.filter(transaction => !selectedTransactions.includes(transaction.id)));
        setSelectedTransactions([]);
        setSelectedTransaction(null);
        setIsDetailVisible(false);
      })
      .catch(error => {
        console.error("There was an error deleting the transactions!", error);
      });
  };

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFilters(prevFilters => ({ ...prevFilters, [name]: value }));
  };

  const filteredTransactions = transactions.filter(transaction =>
    transaction.accountCode.includes(filters.accountCode) &&
    transaction.accountName.includes(filters.accountName) &&
    (filters.transactionType === '' || transaction.transactionType === filters.transactionType)
  );

  return (
    <Container>
      <h1>거래처관리</h1>
      <Grid container spacing={2} alignItems="center">
        <Grid item xs={12} sm={3}>
          <TextField
            fullWidth
            label="거래처코드"
            name="accountCode"
            value={filters.accountCode}
            onChange={handleInputChange}
            variant="outlined"
          />
        </Grid>
        <Grid item xs={12} sm={3}>
          <TextField
            fullWidth
            label="거래처명"
            name="accountName"
            value={filters.accountName}
            onChange={handleInputChange}
            variant="outlined"
          />
        </Grid>
        <Grid item xs={12} sm={3}>
          <FormControl fullWidth variant="outlined">
            <InputLabel>거래처유형</InputLabel>
            <Select
              label="거래처유형"
              name="transactionType"
              value={filters.transactionType}
              onChange={handleInputChange}
            >
              <MenuItem value="">전체</MenuItem>
              <MenuItem value="입고">입고</MenuItem>
              <MenuItem value="출고">출고</MenuItem>
            </Select>
          </FormControl>
        </Grid>
        <Grid item xs={12} sm={3} container spacing={2}>
          <Grid item>
            <Button variant="contained" color="primary" onClick={fetchTransactions}>
              조회
            </Button>
          </Grid>
          <Grid item>
            <Button variant="contained" color="success" onClick={handleCreate}>
              신규
            </Button>
          </Grid>
          {selectedTransactions.length > 0 && (
            <Grid item>
              <Button variant="contained" color="error" onClick={handleDeleteSelected}>
                삭제
              </Button>
            </Grid>
          )}
        </Grid>
      </Grid>
      <TransactionTable
        transactions={filteredTransactions}
        onSelect={handleSelectTransaction}
        onCheckboxChange={handleCheckboxChange}
        selectedTransactions={selectedTransactions}
        onSelectAll={handleSelectAll}
      />
      {isDetailVisible && selectedTransaction && (
        <TransactionForm transaction={selectedTransaction} onChange={handleChange} onUpdate={handleUpdate} />
      )}
    </Container>
  );
};

export default Transaction;
