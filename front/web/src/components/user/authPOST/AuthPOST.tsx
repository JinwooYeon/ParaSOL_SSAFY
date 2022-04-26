import { Components } from "components/Components";

export const AuthPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/auth",
    method: "POST",
    detail: "로그인",
    completed: false,
  };
  const requestBody = {
    LoginInfo: [
      {
        value: "id",
        type: "string",
        required: true,
      },
      {
        value: "password",
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
