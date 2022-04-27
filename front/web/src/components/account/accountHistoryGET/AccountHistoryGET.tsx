import { Components } from "components/Components";

export const AccountHistoryGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/history",
    method: "GET",
    detail: "계좌 거래내역 조회",
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
