import { Components } from "components/Components";

export const OauthPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/login/google",
    method: "POST",
    detail: "OAuth 회원 등록",
    completed: true,
  };
  const requestBody = {};
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
