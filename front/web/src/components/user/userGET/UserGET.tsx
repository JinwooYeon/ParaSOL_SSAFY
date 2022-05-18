import { Components } from "components/Components";
import { JwtHeader } from "model/Model";

export const UserGET = () => {
  ////////////// 입력해야하는 부분 ///////////
  const API = {
    uri: "/user",
    method: "GET",
    detail: "내 정보 조회",
    completed: true,
  };
  const requestBody = {
    JwtHeader,
  };
  ///////////////////////////////////

  return (
    <>
      <Components API={API} requestBody={requestBody} />
    </>
  );
};
