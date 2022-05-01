import { Components } from "components/Components";
import { BankInfo, Token } from "model/Model";

export const BankPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/bank",
    method: "POST",
    detail: "은행 연결",
    completed: false,
  };
  const requestBody = {
    BankInfo,
    Token,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
