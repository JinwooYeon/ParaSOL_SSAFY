import { Components } from "components/Components";
import { UserInfo } from "model/Model";

export const UserRegisterPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/register",
    method: "POST",
    detail: "회원 등록",
    completed: false,
  };
  const requestBody = {
    UserInfo,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
