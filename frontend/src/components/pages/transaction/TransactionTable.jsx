import React from 'react';
import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Checkbox } from '@mui/material';

const TransactionTable = ({ transactions, onSelect, onCheckboxChange, selectedTransactions, onSelectAll }) => {
  const isAllSelected = transactions.length > 0 && selectedTransactions.length === transactions.length;

  return (
    <TableContainer>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell padding="checkbox">
              <Checkbox
                indeterminate={selectedTransactions.length > 0 && selectedTransactions.length < transactions.length}
                checked={isAllSelected}
                onChange={(e) => onSelectAll(e.target.checked)}
              />
            </TableCell>
            <TableCell>거래처코드</TableCell>
            <TableCell>거래처명</TableCell>
            <TableCell>거래처유형</TableCell>
            <TableCell>전화번호</TableCell>
            <TableCell>사업자번호</TableCell>
            <TableCell>대표자명</TableCell>
            <TableCell>비고</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {transactions.map((transaction) => (
            <TableRow
              key={transaction.id}
              onClick={() => onSelect(transaction)}
              selected={selectedTransactions.includes(transaction.id)}
            >
              <TableCell padding="checkbox">
                <Checkbox
                  checked={selectedTransactions.includes(transaction.id)}
                  onChange={() => onCheckboxChange(transaction.id)}
                />
              </TableCell>
              <TableCell>{transaction.accountCode}</TableCell>
              <TableCell>{transaction.accountName}</TableCell>
              <TableCell>{transaction.transactionType}</TableCell>
              <TableCell>{transaction.accountTel}</TableCell>
              <TableCell>{transaction.businessNumber}</TableCell>
              <TableCell>{transaction.representativeName}</TableCell>
              <TableCell>{transaction.note}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
};

export default TransactionTable;
