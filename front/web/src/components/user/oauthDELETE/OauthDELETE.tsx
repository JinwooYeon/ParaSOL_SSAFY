import { Components } from "components/Components";

export const OauthDELETE = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/oauth",
    method: "DELETE",
    detail: "OAuth 회원 탈퇴",
    completed: false,
  };
  const requestBody = {
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
