import { Components } from "components/Components";
import { Transaction, Token } from "model/Model";

export const AccountWithdrawPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "account/withdraw",
    method: "POST",
    detail: "결제",
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
