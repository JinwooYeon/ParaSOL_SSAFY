import { Components } from "components/Components";
import { WithdrawRequest, JwtHeader } from "model/Model";

export const AccountWithdrawPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/withdraw",
    method: "POST",
    detail: "결제",
    completed: true,
  };
  const requestBody = {
    WithdrawRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
