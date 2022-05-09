import { Components } from "components/Components";
import { IdCheckRequest } from "model/Model";

export const UserIdcheckPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/idcheck",
    method: "POST",
    detail: "아이디 중복 체크",
    completed: true,
  };
  const requestBody = {
    IdCheckRequest,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
