import { Components } from "components/Components";

export const DepositPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/deposit",
    method: "POST",
    detail: "입금",
    completed: false,
  };
  const requestBody = {
    AccountRequest: [
      {
        value: "method",
        type: "string",
        required: true,
      },
      {
        value: "amount",
        type: "integer",
        required: true,
      },
      {
        value: "account_from",
        type: "AccountInfo",
        required: true,
      },
      {
        value: "account_to",
        type: "AccountInfo",
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
