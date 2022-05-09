import { Components } from "components/Components";
import { ReissueTokenRequest } from "model/Model";

export const UserTokenPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/token",
    method: "POST",
    detail: "새로운 인증 토큰 요청",
    completed: false,
  };
  const requestBody = {
    ReissueTokenRequest,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
