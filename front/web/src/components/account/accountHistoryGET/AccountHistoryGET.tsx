import { Components } from "components/Components";
import { AccountInfo, Token } from "model/Model";

export const AccountHistoryGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/history",
    method: "GET",
    detail: "계좌 거래내역 조회",
    completed: false,
  };
  const requestBody = {
    AccountInfo,
    Token,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
