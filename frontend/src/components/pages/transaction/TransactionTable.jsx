import React from 'react';
import { DataGrid, GridToolbarContainer, GridToolbarExport } from '@mui/x-data-grid';
import Checkbox from '@mui/material/Checkbox';

const TransactionTable = ({ transactions, onSelect, onCheckboxChange, selectedTransactions, onSelectAll }) => {
  const columns = [
    {
      field: 'select',
      headerName: '',
      width: 50,
      renderHeader: (params) => (
        <Checkbox
          indeterminate={selectedTransactions.length > 0 && selectedTransactions.length < transactions.length}
          checked={transactions.length > 0 && selectedTransactions.length === transactions.length}
          onChange={(e) => onSelectAll(e.target.checked)}
        />
      ),
      renderCell: (params) => (
        <Checkbox
          checked={selectedTransactions.includes(params.row.id)}
          onChange={() => onCheckboxChange(params.row.id)}
        />
      ),
      sortable: false,
      filterable: false,
    },
    { field: 'accountCode', headerName: '거래처코드', width: 150 },
    { field: 'accountName', headerName: '거래처명', width: 200 },
    { field: 'transactionType', headerName: '거래처 유형', width: 120 },
    { field: 'accountAddress', headerName: '주소', width: 200 },
    { field: 'accountTel', headerName: '전화번호', width: 150 },
    { field: 'businessNumber', headerName: '사업자번호', width: 150 },
    { field: 'representativeName', headerName: '대표자명', width: 150 },
    
  ];

  return (
    <div style={{ height: 400, width: '100%' }}>
      <DataGrid
        rows={transactions}
        columns={columns}
        checkboxSelection
        disableSelectionOnClick
        onRowClick={(params) => onSelect(params.row)}
        components={{
          Toolbar: CustomToolbar,
        }}
        getRowId={(row) => row.id}
      />
    </div>
  );
};

function CustomToolbar() {
  return (
    <GridToolbarContainer>
      <GridToolbarExport
        csvOptions={{
          fileName: 'transactionList',
          delimiter: ',',
          utf8WithBom: true,
        }}
      />
    </GridToolbarContainer>
  );
}

export default TransactionTable;
