import { Components } from "components/Components";
import { QueryAccountListRequest, JwtHeader } from "model/Model";

export const AccountGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/account",
    method: "GET",
    detail: "계좌 목록 조회",
    completed: false,
  };
  const requestBody = {
    QueryAccountListRequest,
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
