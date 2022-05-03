import { Components } from "components/Components";

export const OauthPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/oauth",
    method: "POST",
    detail: "OAuth 회원 등록",
    completed: false,
  };
  const requestBody = {};
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
