import { Components } from "components/Components";

export const OauthGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/login/google",
    method: "GET",
    detail: "구글 로그인 (Oauth)",
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
