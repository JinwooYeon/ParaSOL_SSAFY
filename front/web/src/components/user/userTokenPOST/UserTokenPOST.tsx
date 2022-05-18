import { Components } from "components/Components";
import { RefreshToken } from "model/Model";

export const UserTokenPOST = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user/token",
    method: "GET",
    detail: "새로운 인증 토큰 요청",
    completed: true,
  };
  const requestBody = {
    RefreshToken,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
