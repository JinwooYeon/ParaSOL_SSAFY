import { Components } from "components/Components";

export const AccountBalanceGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/balance",
    method: "GET",
    detail: "계좌 잔액 조회",
    completed: false,
  };
  const requestBody = {
    AuthorizedClientAccountInfo: [
      {
        value: "access_token",
        type: "string",
        required: true,
      },
      {
        value: "account_info",
        type: "string",
        required: true,
      },
    ],
    token: [
      {
        value: "jwt",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
