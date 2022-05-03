import { Components } from "components/Components";
import { Transaction, Token } from "model/Model";

export const AccountDepositPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/deposit",
    method: "POST",
    detail: "입금",
    completed: false,
  };
  const requestBody = {
    Transaction,
    Token,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
