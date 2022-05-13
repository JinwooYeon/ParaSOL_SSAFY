import { Components } from "components/Components";
import { DepositRequest, JwtHeader } from "model/Model";

export const AccountDepositPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account/deposit",
    method: "POST",
    detail: "입금",
    completed: true,
  };
  const requestBody = {
    DepositRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
