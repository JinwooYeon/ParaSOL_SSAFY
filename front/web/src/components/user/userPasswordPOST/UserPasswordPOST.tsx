import { Components } from "components/Components";

export const UserPasswordPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/client/password",
    method: "POST",
    detail: "비밀번호 재설정",
    completed: false,
  };
  const requestBody = {
    Password: [
      {
        value: "password",
        type: "string",
        required: true,
      },
    ],
    token: [
      {
        value: "jwt",
        type: "string",
        required: true,
      },
    ],
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
