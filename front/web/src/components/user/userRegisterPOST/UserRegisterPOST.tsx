import { Components } from "components/Components";
import { UserRegisterRequest } from "model/Model";

export const UserRegisterPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/register",
    method: "POST",
    detail: "회원 등록",
    completed: true,
  };
  const requestBody = {
    UserRegisterRequest,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
