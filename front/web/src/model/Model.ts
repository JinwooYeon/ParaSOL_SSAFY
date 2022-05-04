export const LoginInfo = [
  {
    value: "id",
    type: "string",
    required: true,
  },
  {
    value: "password",
    type: "string",
    required: true,
  },
];
export const UserInfo = [
  ...LoginInfo,
  {
    value: "name",
    type: "string",
    required: true,
  },
];
export const Transaction = [
  {
    value: "method",
    type: "{TransactionType}",
    required: true,
  },
  {
    value: "amount",
    type: "long",
    required: true,
  },
  {
    value: "bankName",
    type: "[AccountInfo.accountFrom]",
    required: true,
  },
  {
    value: "bankAccountNumber",
    type: "[AccountInfo.accountFrom]",
    required: true,
  },
  {
    value: "bankName",
    type: "[AccountInfo.accountTo]",
    required: true,
  },
  {
    value: "bankAccountNumber",
    type: "[AccountInfo.accountTo]",
    required: true,
  },
];
export const Password = [
  {
    value: "password",
    type: "string",
    required: true,
  },
];
export const BankInfo = [
  {
    value: "bankName",
    type: "string",
    required: true,
  },
  {
    value: "id",
    type: "string",
    required: true,
  },
  {
    value: "password",
    type: "string",
    required: true,
  },
];
export const AccountInfo = [
  {
    value: "bankName",
    type: "string",
    required: true,
  },
  {
    value: "bankAccountNumber",
    type: "string",
    required: true,
  },
];
export const AccountList = [
  {
    value: "accounts",
    type: "List<AccountInfo>",
    required: true,
  },
];
export const AccountBalance = [
  ...AccountInfo,
  {
    value: "balance",
    type: "long",
    required: true,
  },
];
export const AccountHistory = [
  {
    value: "txDatetime",
    type: "localDateTime",
    required: true,
  },
  {
    value: "txMethod",
    type: "{TransactionType}",
    required: true,
  },
  {
    value: "amount",
    type: "long",
    required: true,
  },
  {
    value: "balance",
    type: "long",
    required: true,
  },
  {
    value: "accountTo",
    type: "[AccountInfo]",
    required: true,
  },
];
export const AccountHistories = [
  {
    value: "accountHistories",
    type: "List<AccountHistory>",
    required: true,
  },
];
export const Token = [
  {
    value: "JwtHeader",
    type: "string",
    required: true,
  },
];
