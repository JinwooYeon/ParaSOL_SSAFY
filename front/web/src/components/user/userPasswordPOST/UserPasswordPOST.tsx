import { Components } from "components/Components";
import { PasswordResetRequest } from "model/Model";

export const UserPasswordPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/password",
    method: "POST",
    detail: "비밀번호 재설정",
    completed: true,
  };
  const requestBody = { PasswordResetRequest };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
