import { Components } from "components/Components";

export const AuthBioDELETE = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/auth/bio",
    method: "DELETE",
    detail: "생체인증(지문) 삭제",
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
