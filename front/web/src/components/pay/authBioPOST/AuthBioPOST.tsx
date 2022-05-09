import { Components } from "components/Components";

export const AuthBioPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/auth/bio",
    method: "POST",
    detail: "생체인증(지문) 등록 및 요청",
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
