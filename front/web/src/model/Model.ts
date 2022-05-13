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
export const AccessToken = [
  {
    value: "accessToken",
    type: "string",
    required: true,
  },
];
export const RefreshToken = [
  {
    value: "refreshToken",
    type: "string",
    required: true,
  },
];
export const AuthToken = [...AccessToken, ...RefreshToken];

////////////////////////////////////////////////////////////
//// JwtHeader
export const JwtHeader = [...AccessToken];
//// Request
export const LoginRequest = [...LoginInfo];
export const BankConnectionRequest = [...BankInfo];
export const UserRegisterRequest = [...UserInfo];
export const UserUpdateRequest = [...UserInfo];
export const DepositRequest = [...Transaction];
export const WithdrawRequest = [...Transaction];
const [a, , b] = UserInfo;
export const PasswordResetRequest = [a, b];
export const QueryAccountListRequest = [...BankInfo];
export const QueryAccountBalanceRequest = [...AccountInfo];
export const QueryAccountHistoryRequest = [...AccountInfo];
export const ReissueTokenRequest = [
  ...RefreshToken,
  {
    value: "id",
    type: "string",
    required: true,
  },
];
export const PasswordUpdateRequest = [
  ...Password,
  {
    value: "NewPassword",
    type: "string",
    required: true,
  },
];
export const IdCheckRequest = [
  {
    value: "id",
    type: "string",
    required: true,
  },
];
//// Response
export const AuthTokenResponse = [...AuthToken];
export const AccountListQueryResultResponse = [...AccountList];
export const AccountBalanceQueryResultResponse = [...AccountBalance];
export const AccountHistoryQueryResultResponse = [...AccountHistories];
export const TransactionExecuteResultResponse = [...Transaction];
export const UserInfoQueryResultResponse = [...UserInfo];
