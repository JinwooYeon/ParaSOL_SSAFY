import { Components } from "components/Components";
import { QueryAccountBalanceRequest, JwtHeader } from "model/Model";

export const AccountBalanceGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/balance",
    method: "GET",
    detail: "계좌 잔액 조회",
    completed: true,
  };
  const requestBody = {
    QueryAccountBalanceRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
